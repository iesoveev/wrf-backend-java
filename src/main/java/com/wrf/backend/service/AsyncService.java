package com.wrf.backend.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    private static final Logger log = LogManager.getLogger(AsyncService.class);

    @Async("pushExecutor")
    public void sendPush(final Message message, final String token) {
        try {
            Thread.sleep(1000);
            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException | InterruptedException ex) {
            log.warn("Notification failed. Device token: " + token);
        }
    }
}
