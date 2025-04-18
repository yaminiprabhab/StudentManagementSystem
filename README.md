# Student Management System

## About the Project

The Student Management System is a simple Java desktop application built using Java AWT and Swing.

It helps manage student records, including:
- Attendance
- Exam eligibility
- Marks entry
- Event calendar tasks

The system provides an easy-to-use graphical interface, making it a lightweight yet functional solution for small-scale educational environments.

## Features

### Add Students
- Add a new student by entering their registration number, name, and branch.

### Mark Attendance
- Enter total and attended classes for five subjects.
- Automatically calculates attendance percentage and determines exam eligibility (>= 75%).

### Enter Marks
- Enter subject-wise marks for students who are eligible based on attendance.

### Generate and View Report Card
- View a detailed report card showing subject marks or "Not Eligible" for each subject.

### Search Students by Branch
- Search and list students from a particular branch.

### View Attendance
- View the attendance percentage for each subject for any student.

### Manage Calendar Tasks
- Add Daily Tasks: Add tasks along with deadlines.
- View Calendar Tasks: View all scheduled tasks.
- Update Daily Tasks: Update or modify any existing task.

### Software Access Simulation
- A simple placeholder for software access functionality (could be expanded).

### Exit Application
- Safely close the program.

## 🔧 Components

### Class `Student`
- Stores student's registration number, name, branch, attendance records, eligibility status, and subject marks.
- Has a `displayReportCard()` method to show a student's report.

### Class `StudentManagementSystem`
- Contains an array of Student objects (up to 10 students).
- An array for calendar tasks.
- Functions for all major operations: add student, mark attendance, enter marks, display reports, search by branch, view attendance, manage calendar.

### GUI (Swing & AWT)
- `JFrame`, `JButton`, `JPanel`, `JLabel`, `JTextField`, and `JOptionPane` are used to build the interactive GUI.
- Button-based navigation with easy dialogs for user interaction.

## 🌐 Technologies Used
- Java
- Java Swing and AWT (for GUI)

## 🔄 How to Run

1. Install Java JDK.
2. Copy the source code into a file named `StudentManagementSystem.java`.

### Compile using:
```bash
javac StudentManagementSystem.java
```

### Run the application:
```bash
java StudentManagementSystem
