package com.example.libraryapidevjoint.service.impl;

import com.example.libraryapidevjoint.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    @Override
    @Async
    public void sendBorrowNotification(String email, String bookTitle) {
        log.info("Email sending started for: {}", email);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info(
                "Email notification sent to {}: Book '{}' was borrowed successfully.",
                email,
                bookTitle
        );
    }
}
