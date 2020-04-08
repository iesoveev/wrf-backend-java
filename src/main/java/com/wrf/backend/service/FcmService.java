package com.wrf.backend.service;

import com.google.firebase.messaging.*;
import com.wrf.backend.model.response.PushEvent;
import com.wrf.backend.model.response.PushModel;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.List;

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

    private void sendPush(PushModel model, String token) {
        Message message = getPreconfiguredMessageBuilder(model).setToken(token).build();
        asyncService.sendPush(message, token);
    }

    private PushModel buildPushModel(@Nullable String sender, PushEvent pushEvent, @Nullable String text) {
        var pushModel = new PushModel(pushEvent.getDescription(),
                pushEvent.getDescription());

        switch (pushEvent) {
            case NEW_EVENT:
                pushModel.setMessage(sender + " добавил новое событие в текущую смену. " + text);
                break;
            case WS_CLOSE:
                pushModel.setMessage(sender + " закрыл текущую смену.");
                break;
            case WS_OPEN:
                pushModel.setMessage(sender + " открыл смену.");
                break;
            case SAVE_IMAGE:
                pushModel.setMessage("Не удалось сохранить картинку");
        }

        return pushModel;
    }
}

