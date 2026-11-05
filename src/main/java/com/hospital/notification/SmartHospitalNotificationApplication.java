package com.hospital.notification;

import com.hospital.notification.processor.PatientAlertProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SmartHospitalNotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHospitalNotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner demo(PatientAlertProcessor processor) {
        return args -> {
            processor.printConfiguration();
            processor.verifyInjection();
            processor.processRoutineAlert("PAT-001", "Reminder: your appointment is tomorrow at 09:00.");
            processor.processCriticalAlert("PAT-ICU-007", "CRITICAL: patient vitals unstable in ICU bed 7!");
        };
    }
}
