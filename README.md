# Smart Hospital Notification System

A Spring Boot application demonstrating the Spring IoC container: `@Primary`, `@Qualifier`,
`@Autowired`, and `@Value` with environment variables.

## Project structure

```
src/main/java/com/hospital/notification
├── SmartHospitalNotificationApplication.java   # entry point + demo runner
├── service
│   ├── NotificationService.java                # interface: void sendNotification(patientId, message)
│   ├── EmailNotificationService.java           # @Primary (default)
│   ├── SmsNotificationService.java             # @Component("smsService")
│   └── EmergencyUrgentNotificationService.java # @Component("emergencyService") + @Qualifier
└── processor
    └── PatientAlertProcessor.java              # @Autowired (default) + @Autowired @Qualifier (emergency)
```

## How it works

- **Default injection**: `@Autowired NotificationService` with no qualifier -> Spring picks
  `EmailNotificationService` because it is `@Primary`.
- **Specific injection**: `@Autowired @Qualifier("emergencyService")` -> `EmergencyUrgentNotificationService`.
- **Configuration**:
  - `@Value("${hospital.name:General City Hospital}")` (default used if missing)
  - `@Value("${HOSPITAL_API_SECRET}")` read from an environment variable

## Setup

Requirements: Java 17+ and Maven 3.8+.

1. Clone the repo.
2. Create your local env file from the template:
   ```bash
   cp .env.example .env
   ```
3. Edit `.env` and put your own values:
   ```
   HOSPITAL_API_SECRET=your_secret_key_here
   HOSPITAL_NAME=Your_Hospital_Name
   ```
   `.env` is listed in `.gitignore` and must **never** be committed.
4. Run the app (from the project root, so `.env` is found):
   ```bash
   mvn spring-boot:run
   ```
   Alternative: export the variables instead of using `.env`:
   - Linux/macOS: `export HOSPITAL_API_SECRET=abc123`
   - Windows PowerShell: `$env:HOSPITAL_API_SECRET="abc123"`
5. Run tests:
   ```bash
   mvn test
   ```

## Expected output

```
Hospital name : Central_Medical_Center
API secret    : su****45
Default channel   : EmailNotificationService (expected EmailNotificationService via @Primary)
Emergency channel : EmergencyUrgentNotificationService (expected EmergencyUrgentNotificationService via @Qualifier)
--- Routine alert at Central_Medical_Center ---
[EMAIL] To patient PAT-001 -> Reminder: your appointment is tomorrow at 09:00.
--- CRITICAL alert at Central_Medical_Center ---
[EMERGENCY - URGENT] Patient PAT-ICU-007 -> CRITICAL: patient vitals unstable in ICU bed 7!
```

## Security

- No secrets are hardcoded; `.env` is git-ignored; only `.env.example` is committed.
- The secret is masked when printed.
