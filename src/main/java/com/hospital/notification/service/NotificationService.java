package com.hospital.notification.service;

public interface NotificationService {
    void sendNotification(String patientId, String message);
}
