package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student[] students = new Student[4];
        Course[] courses = new Course[3];

        // Create students using constructor
        students[0] = new Student("Alice");
        students[1] = new Student("Bob");
        students[2] = new Student("Charlie");
        students[3] = new Student("Diana");

        // Create courses using constructor
        courses[0] = new Course("Mathematics");
        courses[1] = new Course("Physics");
        courses[2] = new Course("Chemistry");

        System.out.println("----- Student Details -----");
        for (Student s : students) {
            s.displayDetails();
            System.out.println();
        }

        System.out.println("----- Course Details -----");
        for (Course c : courses) {
            c.displayDetails();package com.school;

public class Main {
    public static void main(String[] args) {
        // Create a student, teacher, and staff
        Student student = new Student("Alice", "10th Grade");
        Teacher teacher = new Teacher("Mr. John", "Mathematics");
        Staff staff = new Staff("Bob", "Librarian");

        System.out.println("----- School Members -----");
        student.displayDetails();
        teacher.displayDetails();
        staff.displayDetails();

        // Attendance Example (using student.getId())
        System.out.println("\n----- Attendance Record -----");
        AttendanceRecord attendance = new AttendanceRecord(student.getId(), "2025-09-22", true);
        attendance.displayRecord();
    }
}

            System.out.println();
        }

        // Attendance Recording
        List<AttendanceRecord> attendanceLog = new ArrayList<>();
        attendanceLog.add(new AttendanceRecord(students[0].getStudentId(), courses[0].getCourseId(), "Present"));
        attendanceLog.add(new AttendanceRecord(students[1].getStudentId(), courses[1].getCourseId(), "Absent"));
        attendanceLog.add(new AttendanceRecord(students[2].getStudentId(), courses[2].getCourseId(), "Late")); // Invalid status
        attendanceLog.add(new AttendanceRecord(students[3].getStudentId(), courses[0].getCourseId(), "present")); // Lowercase, should be valid

        System.out.println("----- Attendance Records -----");
        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }
    }
}
