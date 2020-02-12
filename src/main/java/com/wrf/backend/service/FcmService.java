package com.wrf.backend.service;

import com.google.firebase.messaging.*;
import com.wrf.backend.model.response.PushNotificationModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FcmService {

    private static final Logger log = LogManager.getLogger(FcmService.class);

    final PushNotificationModel notifyModel;

    public FcmService(PushNotificationModel notifyModel) {
        this.notifyModel = notifyModel;
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    private Message getMsg(PushNotificationModel model, String token) {
        return getPreconfiguredMessageBuilder(model).setToken(token)
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationModel model) {
        AndroidConfig androidConfig = getAndroidConfig(model.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(model.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(model.getTitle(), model.getMessage()));
    }

    public void sendBroadcastNotify(List<String> receivers, String text) {
        notifyModel.setMessage(text);
        List<Message> messages = receivers.stream()
                .map(token -> getMsg(notifyModel, token))
                .collect(Collectors.toUnmodifiableList());

        BatchResponse response;
        try {
            response = FirebaseMessaging.getInstance().sendAll(messages);
        } catch (FirebaseMessagingException ex) {
            log.warn(ex.getLocalizedMessage());
            return;
        }
        response.getResponses().forEach(row -> {
            if (!row.isSuccessful())
                log.warn(row.getException().getLocalizedMessage());
        });
    }
}

