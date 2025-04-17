# Simple Calculator 📱🧮

A basic Android calculator app built using **Kotlin** and **Android Studio**. This app performs fundamental arithmetic operations such as **addition, subtraction, multiplication, and division**. Great for learning Android development!

---

## 🔧 Features

- ➕ Addition  
- ➖ Subtraction  
- ✖️ Multiplication  
- ➗ Division  
- 🧼 Clear input

---

## 🚀 Getting Started with Android Studio

### Prerequisites:
- [Android Studio](https://developer.android.com/studio) installed
- A physical Android device -(Mobile phone recommmended (or emulator))
- USB cable & USB debugging enabled on your phone
  
### Clone the Repository

git clone https://github.com/wasanapelawaththa/Simple-Calculator.git
cd Simple-Calculator
Open in Android Studio
Open Android Studio

Click "Open an Existing Project"

Navigate to the cloned project folder (Simple-Calculator)

Wait for Gradle to sync and build

## 📱 How to Run on a Physical Device via USB

Step 1: Enable Developer Mode on Your Phone
Go to Settings > About Phone

Tap Build Number 7 times until Developer Mode is enabled

Step 2: Enable USB Debugging
Go to Settings > Developer Options

Enable USB Debugging

Step 3: Connect Your Phone via USB
Plug your phone into your computer

Set USB mode to File Transfer (MTP)

When prompted on your phone, allow USB debugging access

Step 4: Run the App from Android Studio
Click the Run ▶️ button in Android Studio

Choose your device from the list

The app will be installed and launched on your phone

## 📦 How to Build and Manually Install APK

Step 1: Build the APK
In Android Studio:

Go to Build > Build APK(s)

After building, click "locate" to open the folder containing the APK (usually app/build/outputs/apk/debug/app-debug.apk)

Step 2: Transfer and Install APK on Your Phone
Option 1: Via USB
Copy the app-debug.apk file to your phone

On your phone, locate the APK using the File Manager

Tap to install (you may need to allow installations from unknown sources)

Option 2: Use ADB (Optional)
bash
Copy
Edit
adb install path/to/app-debug.apk

## 💡 Notes

Tested on Android 8.0+ phones

No internet connection is required after installation

For learning purposes and offline usage

## 📃 License

This project is open-source and free to use for educational and personal purposes.
