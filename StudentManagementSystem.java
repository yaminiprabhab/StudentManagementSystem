import java.awt.*;
import javax.swing.*;

class Student {
    int regNo;
    String name;
    String branch;
    int[] attendance = new int[5]; // Attendance in 5 subjects
    boolean[] eligibleForExam = new boolean[5]; // Eligibility for each subject
    int[] marks = new int[5]; // Marks in 5 subjects

    Student(int regNo, String name, String branch) {
        this.regNo = regNo;
        this.name = name;
        this.branch = branch;
    }

    void displayReportCard() {
        StringBuffer report = new StringBuffer();
        report.append("\n-------- REPORT CARD --------\n")
              .append(String.format("Reg No: %d\tName: %s\tBranch: %s\n", regNo, name, branch))
              .append("--------------------------------------------------\n")
              .append(String.format("%-15s %-10s\n", "Subject", "Marks"))
              .append("--------------------------------------------------\n");
        for (int i = 0; i < marks.length; i++) {
            if (eligibleForExam[i]) {
                report.append(String.format("%-15s %-10d\n", "Subject " + (i + 1), marks[i]));
            } else {
                report.append(String.format("%-15s %-10s\n", "Subject " + (i + 1), "Not Eligible"));
            }
        }
        report.append("--------------------------------------------------");
        JOptionPane.showMessageDialog(null, report.toString());
    }
}

public class StudentManagementSystem {
    static Student[] students = new Student[10]; // Store up to 10 students
    static int studentCount = 0; // Number of students added
    static String[][] calendarTasks = new String[10][2]; // Event calendar with task and deadline
    static int taskCount = 0; // Track calendar tasks

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setLayout(new FlowLayout());

            JButton addStudentButton = new JButton("Add Student");
            JButton markAttendanceButton = new JButton("Mark Attendance");
            JButton enterMarksButton = new JButton("Enter Marks");
            JButton viewReportButton = new JButton("View Report Card");
            JButton searchByBranchButton = new JButton("Search by Branch");
            JButton viewAttendanceButton = new JButton("View Attendance");
            JButton addTaskButton = new JButton("Add Task to Calendar");
            JButton viewCalendarButton = new JButton("View Calendar Tasks");
            JButton updateTaskButton = new JButton("Update Task");
            JButton accessSoftwareButton = new JButton("Access Software");
            JButton exitButton = new JButton("Exit");

            addStudentButton.addActionListener(e -> addStudent());
            markAttendanceButton.addActionListener(e -> markAttendance());
            enterMarksButton.addActionListener(e -> enterMarks());
            viewReportButton.addActionListener(e -> displayReportCard());
            searchByBranchButton.addActionListener(e -> searchByBranch());
            viewAttendanceButton.addActionListener(e -> viewAttendance());
            addTaskButton.addActionListener(e -> addToCalendar());
            viewCalendarButton.addActionListener(e -> viewCalendarTasks());
            updateTaskButton.addActionListener(e -> updateDailyTask());
            accessSoftwareButton.addActionListener(e -> accessSoftware());
            exitButton.addActionListener(e -> System.exit(0));

            frame.add(addStudentButton);
            frame.add(markAttendanceButton);
            frame.add(enterMarksButton);
            frame.add(viewReportButton);
            frame.add(searchByBranchButton);
            frame.add(viewAttendanceButton);
            frame.add(addTaskButton);
            frame.add(viewCalendarButton);
            frame.add(updateTaskButton);
            frame.add(accessSoftwareButton);
            frame.add(exitButton);

            frame.setVisible(true);
        });
    }

    static void addStudent() {
        JTextField regNoField = new JTextField(5);
        JTextField nameField = new JTextField(10);
        JTextField branchField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Registration Number:"));
        panel.add(regNoField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Branch:"));
        panel.add(branchField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (studentCount < students.length) {
                int regNo = Integer.parseInt(regNoField.getText());
                String name = nameField.getText();
                String branch = branchField.getText();
                students[studentCount++] = new Student(regNo, name, branch);
                JOptionPane.showMessageDialog(null, "Student added successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Cannot add more students. Maximum capacity reached!");
            }
        }
    }

    static void markAttendance() {
        JTextField regNoField = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Registration Number:"));
        panel.add(regNoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Mark Attendance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int regNo = Integer.parseInt(regNoField.getText());
            boolean found = false;

            for (int i = 0; i < studentCount; i++) {
                if (students[i].regNo == regNo) {
                    found = true;
                    for (int j = 0; j < 5; j++) {
                        JTextField totalField = new JTextField(5);
                        JTextField attendedField = new JTextField(5);
                        JPanel attendancePanel = new JPanel();
                        attendancePanel.add(new JLabel("Subject " + (j + 1) + " - Total Classes:"));
                        attendancePanel.add(totalField);
                        attendancePanel.add(new JLabel("Classes Attended:"));
                        attendancePanel.add(attendedField);

                        JOptionPane.showConfirmDialog(null, attendancePanel, "Enter Attendance", JOptionPane.OK_CANCEL_OPTION);
                        int total = Integer.parseInt(totalField.getText());
                        int attended = Integer.parseInt(attendedField.getText());
                        students[i].attendance[j] = (attended * 100) / total;
                        students[i].eligibleForExam[j] = students[i].attendance[j] >= 75;
                    }
                    JOptionPane.showMessageDialog(null, "Attendance marked successfully!");
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }

    static void enterMarks() {
        JTextField regNoField = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Registration Number:"));
        panel.add(regNoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Marks", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int regNo = Integer.parseInt(regNoField.getText());
            boolean found = false;

            for (int i = 0; i < studentCount; i++) {
                if (students[i].regNo == regNo) {
                    found = true;
                    for (int j = 0; j < 5; j++) {
                        if (students[i].eligibleForExam[j]) {
                            JTextField marksField = new JTextField(5);
                            JPanel marksPanel = new JPanel();
                            marksPanel.add(new JLabel("Enter marks for Subject " + (j + 1) + ":"));
                            marksPanel.add(marksField);
                            JOptionPane.showConfirmDialog(null, marksPanel, "Enter Marks", JOptionPane.OK_CANCEL_OPTION);
                            students[i].marks[j] = Integer.parseInt(marksField.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot enter marks for Subject " + (j + 1) + " (Not Eligible).");
                        }
                    }
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }

    static void displayReportCard() {
        JTextField regNoField = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Registration Number:"));
        panel.add(regNoField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "View Report Card", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int regNo = Integer.parseInt(regNoField.getText());
            boolean found = false;
    
            for (int i = 0; i < studentCount; i++) {
                if (students[i].regNo == regNo) {
                    StringBuffer report = new StringBuffer();
                    report.append("\n-------- REPORT CARD --------\n")
                          .append(String.format("Reg No: %d    Name: %s    Branch: %s\n", 
                          regNo, students[i].name, students[i].branch))
                          .append("--------------------------------------------------\n")
                          .append(String.format("%-15s %-10s\n", "Subject", "Marks"))
                          .append("--------------------------------------------------\n");
                    
                    for (int j = 0; j < students[i].marks.length; j++) {
                        if (students[i].eligibleForExam[j]) {
                            report.append(String.format("%-15s %-10d\n", "Subject " + (j + 1), students[i].marks[j]));
                        } else {
                            report.append(String.format("%-15s %-10s\n", "Subject " + (j + 1), "Not Eligible"));
                        }
                    }
                    report.append("--------------------------------------------------");
                    JOptionPane.showMessageDialog(null, report.toString());
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }
    

    static void searchByBranch() {
        JTextField branchField = new JTextField(10);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Branch:"));
        panel.add(branchField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Search by Branch", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String branch = branchField.getText();
            StringBuffer studentsInBranch = new StringBuffer("Students in " + branch + " branch:\n");
            boolean found = false;

            for (int i = 0; i < studentCount; i++) {
                if (students[i].branch.equalsIgnoreCase(branch)) {
                    studentsInBranch.append("Reg No: ").append(students[i].regNo)
                                    .append(", Name: ").append(students[i].name).append("\n");
                    found = true;
                }
            }
            if (!found) {
                studentsInBranch.append("No students found in this branch.");
            }
            JOptionPane.showMessageDialog(null, studentsInBranch.toString());
        }
    }

    static void viewAttendance() {
        JTextField regNoField = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Registration Number:"));
        panel.add(regNoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "View Attendance", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int regNo = Integer.parseInt(regNoField.getText());
            boolean found = false;

            for (int i = 0; i < studentCount; i++) {
                if (students[i].regNo == regNo) {
                    StringBuffer attendanceReport = new StringBuffer("Attendance for " + students[i].name + ":\n");
                    for (int j = 0; j < 5; j++) {
                        attendanceReport.append("Subject ").append(j + 1)
                                        .append(": ").append(students[i].attendance[j]).append("% - ")
                                        .append(students[i].eligibleForExam[j] ? "Eligible" : "Not Eligible").append("\n");
                    }
                    JOptionPane.showMessageDialog(null, attendanceReport.toString());
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }

    static void addToCalendar() {
        JTextField taskField = new JTextField(20);
        JTextField dateField = new JTextField(10);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Task:"));
        panel.add(taskField);
        panel.add(new JLabel("Deadline (dd-mm-yyyy):"));
        panel.add(dateField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Task to Calendar", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (taskCount < calendarTasks.length) {
                calendarTasks[taskCount][0] = taskField.getText();
                calendarTasks[taskCount][1] = dateField.getText();
                taskCount++;
                JOptionPane.showMessageDialog(null, "Task added to calendar!");
            } else {
                JOptionPane.showMessageDialog(null, "Cannot add more tasks. Maximum capacity reached!");
            }
        }
    }

    static void viewCalendarTasks() {
        StringBuffer tasks = new StringBuffer("Calendar Tasks:\n");
        if (taskCount == 0) {
            tasks.append("No Tasks Found!");
        } else {
            for (int i = 0; i < taskCount; i++) {
                tasks.append((i + 1) + ". " + calendarTasks[i][0] + " - " + calendarTasks[i][1] + "\n");
            }
        }
        JOptionPane.showMessageDialog(null, tasks.toString());
    }

    static void updateDailyTask() {
        JTextField taskField = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter completed task:"));
        panel.add(taskField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update Task", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String task = taskField.getText();
            boolean removed = false;

            for (int i = 0; i < taskCount; i++) {
                if (calendarTasks[i][0].equals(task)) {
                    calendarTasks[i] = calendarTasks[--taskCount]; // Shift tasks
                    removed = true;
                    break;
                }
            }
            if (removed) {
                JOptionPane.showMessageDialog(null, "Task removed from calendar and marked as done!");
            } else {
                JOptionPane.showMessageDialog(null, "Task not found in calendar!");
            }
        }
    }

    static void accessSoftware() {
        JTextField regNoField = new JTextField(5);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Registration Number:"));
        panel.add(regNoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Access Software", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int regNo = Integer.parseInt(regNoField.getText());
            boolean found = false;

            for (int i = 0; i < studentCount; i++) {
                if (students[i].regNo == regNo) {
                    String branch = students[i].branch;
                    StringBuffer softwareList = new StringBuffer("Software accessible for branch: " + branch + "\n");

                    switch (branch.toLowerCase()) {
                        case "cse":
                        case "cce":
                        case "it":
                        case "aiml":
                            softwareList.append("1. IntelliJ IDEA\n2. GitHub\n3. Visual Studio Code");
                            break;
                        case "ece":
                        case "eee":
                            softwareList.append("1. MATLAB\n2. LTSpice\n3. Xilinx");
                            break;
                        case "mech":
                            softwareList.append("1. SolidWorks\n2. AutoCAD\n3. ANSYS");
                            break;
                        default:
                            softwareList.append("No specific software available.");
                    }
                    JOptionPane.showMessageDialog(null, softwareList.toString());
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found!");
            }
        }
    }
}