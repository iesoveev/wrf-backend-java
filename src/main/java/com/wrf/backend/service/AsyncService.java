package com.wrf.backend.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AsyncService {

    final FcmService fcmService;

    public AsyncService(FcmService fcmService) {
        this.fcmService = fcmService;
    }

    @Async
    public void sendNotify(List<String> receivers, String message) {
        fcmService.sendBroadcastNotify(receivers, message);
    }
}
