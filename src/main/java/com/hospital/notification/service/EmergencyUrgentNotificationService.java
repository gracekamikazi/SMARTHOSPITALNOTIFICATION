package com.hospital.notification.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("emergencyService")
@Qualifier("emergencyService")
public class EmergencyUrgentNotificationService implements NotificationService {
    @Override
    public void sendNotification(String patientId, String message) {
        System.out.println("[EMERGENCY - URGENT] Patient " + patientId + " -> " + message);
    }
}
