package com.example.libraryapidevjoint.service;

public interface NotificationService {
    void sendBorrowNotification(String email, String bookTitle);
}
