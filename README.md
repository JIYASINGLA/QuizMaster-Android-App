# QuizMaster – Android App

## Overview
**QuizMaster** is an Android application that allows users to test their knowledge in multiple subjects through interactive quizzes. The app currently supports **five subjects**:  

- **HTML**  
- **C++**  
- **React**  
- **Python**  
- **JavaScript**  

The app provides a user-friendly interface, category selection, and a leaderboard for tracking top scores. Firebase is used for data storage and real-time updates.

---

## Features

### Home Screen
- Welcomes the user by name.  
- Provides navigation buttons:  
  - Start Quiz  
  - Choose Category  
  - Leaderboard  
  - Device Info (for system diagnostics)  
  - Sensor Test  

### Quiz Functionality
- Users can select a subject and start a quiz.  
- Questions are displayed one by one with multiple-choice options.  
- Scores are calculated and displayed at the end.  

### Category Selection
- Allows users to choose a subject before starting the quiz.  
- Each subject has a unique set of questions stored in Firebase.  

### Leaderboard
- Displays top scores for each subject.  
- Real-time updates from Firebase database.  

### Device Info (MDT Integration)
- Shows device information including:  
  - Device name and model  
  - Android version  
  - CPU, RAM, Internal storage  
  - Battery status and network info  

### Sensor Test (MDT Integration)
- Tests the following sensors:  
  - Motion Sensors: Accelerometer, Gyroscope  
  - Position Sensors: Proximity, Orientation, Magnetometer  
  - Environment Sensors: Light, Temperature, Pressure, Humidity  

---

## Project Structure

| File / Folder | Description |
|---------------|-------------|
| `StartActivity.java` | Main home screen with buttons and welcome message |
| `HomeActivity.java` | Quiz start page for selected subject |
| `CategoryActivity.java` | Subject/category selection screen |
| `LeaderboardActivity.java` | Displays top scores from Firebase |
| `DeviceInfoActivity.java` | Displays mobile device information |
| `SensorTestActivity.java` | Performs sensor testing |
| `res/layout/` | XML layouts for all screens |
| `Firebase` | Stores questions, scores, and user data |

---

## Completed Parts
1. **Part-1: UI Designing**
   - Minimal, clean, and interactive design.  
   - Progressive disclosure technique applied.  
   - Fast and responsive interface.

2. **Part-2: Mobile Info & Spec**
   - Device specifications retrieved and displayed.  
   - Includes hardware and software details.

3. **Part-3: Sensors Test**
   - Motion, position, and environmental sensors tested.

---

## Pending Features (Part-4)
- Data Storage & Memory:  
  - Call logs display  
  - Internal & external memory tests  
  - Report generation for mobile status  

---

## Technology Stack
- **Android Studio** (Java/Kotlin)  
- **Firebase Realtime Database**  
- **Android SDK**  
- **Java**  
- **XML** for UI layouts  

---

## How to Run
1. Clone the repository:  
   ```bash
   git clone <repository-url>
