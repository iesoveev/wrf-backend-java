package com.wrf.backend.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.wrf.backend.db_api.repository.AndroidLogRepository;
import com.wrf.backend.entity.AndroidLog;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final AndroidLogRepository androidLogRepository;

    @Async("pushExecutor")
    public void sendPush(final Message message, final String token) {
        try {
            FirebaseMessaging.getInstance().send(message);
            androidLogRepository.save(new AndroidLog("Notification success. Device token: " + token));
        } catch (FirebaseMessagingException ex) {
            androidLogRepository.save(new AndroidLog("Notification failed. Device token: " + token));
        }
    }
}
