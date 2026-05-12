# Tailor Work Management System

## Project Overview
This is a professional management system for tailoring shops, featuring a Django REST API backend and a modern Android application.

## 🚀 Backend Setup (Django + MySQL)

### Prerequisites
- Python 3.10+
- MySQL Server

### Installation
1. Navigate to the `backend` folder.
2. Create a virtual environment:
   ```bash
   python -m venv venv
   source venv/bin/activate  # or venv\Scripts\activate on Windows
   ```
3. Install dependencies:
   ```bash
   pip install -r requirements.txt
   ```
4. **Configure Database**:
   - Open `tailor_backend/settings.py`.
   - Uncomment the MySQL configuration section and provide your credentials.
   - Create a database named `tailor_db` in MySQL.
5. Run migrations:
   ```bash
   python manage.py makemigrations api
   python manage.py migrate
   ```
6. Create an admin user:
   ```bash
   python manage.py createsuperuser
   ```
7. Start the server:
   ```bash
   python manage.py runserver
   ```

## 📱 Android Setup (Kotlin + MVVM)

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17

### Installation
1. Open the `android` folder in Android Studio.
2. Let Gradle sync and download dependencies.
3. **API Configuration**:
   - The app is pre-configured to connect to `http://10.0.2.2:8000/api/` (localhost for Android Emulator).
   - If testing on a physical device, update `BASE_URL` in `data/api/RetrofitClient.kt` with your machine's IP address.
4. Run the app on an emulator or physical device.

## ✨ Key Features
- **Admin & Employee Portals**: Role-based access control.
- **Order Management**: Track orders from creation to delivery.
- **Work Assignment**: Support for single and partner work with custom pay splitting.
- **Salary Tracking**: Piece-based and monthly salary calculations.
- **Dashboard**: Real-time stats and performance charts.
- **Offline Support**: Room database for data persistence.

## 📁 Project Structure
- `backend/`: Django REST API source code.
- `android/`: Kotlin Android application source code.
- `implementation_plan.md`: Detailed feature roadmap.
