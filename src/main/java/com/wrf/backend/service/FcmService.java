package com.wrf.backend.service;

import com.google.firebase.messaging.*;
import com.wrf.backend.model.response.PushEvent;
import com.wrf.backend.model.response.PushModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FcmService {

    private final AsyncService asyncService;

    private static final Logger log = LogManager.getLogger(FcmService.class);

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

    private void sendPush(PushModel model, String token) {
        Message message = getPreconfiguredMessageBuilder(model).setToken(token).build();
        asyncService.sendPush(message, token);
    }

    private Message.Builder getPreconfiguredMessageBuilder(PushModel model) {
        AndroidConfig androidConfig = getAndroidConfig(model.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(model.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setNotification(
                        new Notification(model.getTitle(), model.getMessage()));
    }

    public void buildAndSendPush(List<String> receivers, String sender, PushEvent pushEvent, String text) {
        var pushModel = buildPushModel(sender, pushEvent, text);
        receivers.forEach(token -> sendPush(pushModel, token));

    }

    private PushModel buildPushModel(String sender, PushEvent pushEvent, String text) {
        var pushModel = new PushModel(pushEvent.getDescription(),
                pushEvent.getDescription());

        switch (pushEvent) {
            case NEW_EVENT:
                pushModel.setMessage(sender + " добавил новое событие в текущую смену. " + text);
                break;
            case WS_CLOSE:
                pushModel.setMessage(sender + " закрыл текущую смену.");
                break;
        }

        return pushModel;
    }
}

