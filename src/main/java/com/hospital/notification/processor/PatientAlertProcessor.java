package com.hospital.notification.processor;

import com.hospital.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PatientAlertProcessor {

    // Default injection: no @Qualifier -> @Primary (EmailNotificationService)
    @Autowired
    private NotificationService defaultNotificationService;

    // Specific injection: @Qualifier selects the emergency bean
    @Autowired
    @Qualifier("emergencyService")
    private NotificationService emergencyNotificationService;

    @Value("${hospital.name:General City Hospital}")
    private String hospitalName;

    @Value("${HOSPITAL_API_SECRET}")
    private String apiSecret;

    public void processRoutineAlert(String patientId, String message) {
        System.out.println("--- Routine alert at " + hospitalName + " ---");
        defaultNotificationService.sendNotification(patientId, message);
    }

    public void processCriticalAlert(String patientId, String message) {
        System.out.println("--- CRITICAL alert at " + hospitalName + " ---");
        emergencyNotificationService.sendNotification(patientId, message);
    }

    public void verifyInjection() {
        System.out.println("Default channel   : " + defaultNotificationService.getClass().getSimpleName());
        System.out.println("Emergency channel : " + emergencyNotificationService.getClass().getSimpleName());
    }

    public void printConfiguration() {
        System.out.println("Hospital name : " + hospitalName);
        System.out.println("API secret    : " + mask(apiSecret));
    }

    private String mask(String s) {
        if (s == null || s.length() < 4) return "****";
        return s.substring(0, 2) + "****" + s.substring(s.length() - 2);
    }
}
