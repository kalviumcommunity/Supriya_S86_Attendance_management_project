package com.school;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Alice", "10th Grade"));
        students.add(new Student("Bob", "9th Grade"));

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Mathematics"));
        courses.add(new Course("Physics"));

        ArrayList<AttendanceRecord> records = new ArrayList<>();
        records.add(new AttendanceRecord(students.get(0).getId(), courses.get(0).getCourseId(), "Present"));
        records.add(new AttendanceRecord(students.get(1).getId(), courses.get(1).getCourseId(), "Absent"));

        // Display details
        System.out.println("----- Students -----");
        for (Student s : students) s.displayDetails();

        System.out.println("----- Courses -----");
        for (Course c : courses) c.displayDetails();

        System.out.println("----- Attendance -----");
        for (AttendanceRecord r : records) r.displayRecord();

        // Save to files
        FileStorageService storage = new FileStorageService();
        storage.saveData(students, "students.txt");
        storage.saveData(courses, "courses.txt");
        storage.saveData(records, "attendance_log.txt");
    }
}
