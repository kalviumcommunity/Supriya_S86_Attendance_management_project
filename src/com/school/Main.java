package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void displaySchoolDirectory(List<Person> people) {
        for (Person person : people) {
            person.displayDetails();
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student("Alice", "10th Grade");
        Student s2 = new Student("Bob", "9th Grade");
        students.add(s1);
        students.add(s2);

        List<Course> courses = new ArrayList<>();
        Course c1 = new Course("Mathematics");
        Course c2 = new Course("Physics");
        courses.add(c1);
        courses.add(c2);

    // Use AttendanceService to manage attendance records
    AttendanceService attendanceService = new AttendanceService();

    // Mark attendance using object references
    attendanceService.markAttendance(s1, c1, "Present");
    attendanceService.markAttendance(s2, c2, "Absent");

        // Create some staff/teachers
        Teacher t1 = new Teacher("Mr. Smith", "Mathematics");
        Staff st1 = new Staff("Jane Doe", "Secretary");

        // Build polymorphic directory
        List<Person> schoolPeople = new ArrayList<>();
        schoolPeople.add(s1);
        schoolPeople.add(s2);
        schoolPeople.add(t1);
        schoolPeople.add(st1);

        System.out.println("----- School Directory -----");
        displaySchoolDirectory(schoolPeople);

        System.out.println("----- Courses -----");
        for (Course c : courses) c.displayDetails();

    System.out.println("----- Attendance (all) -----");
    attendanceService.displayAttendanceLog();

    System.out.println("----- Attendance for student Alice -----");
    attendanceService.displayAttendanceLog(s1);

    System.out.println("----- Attendance for course Physics -----");
    attendanceService.displayAttendanceLog(c2);

        // Save to files (filter students from schoolPeople as an example)
        FileStorageService storage = new FileStorageService();
        List<Student> studentsToSave = new ArrayList<>();
        for (Person p : schoolPeople) {
            if (p instanceof Student) {
                studentsToSave.add((Student) p);
            }
        }

    storage.saveData(studentsToSave, "students.txt");
    storage.saveData(courses, "courses.txt");
    // attendanceService already saves the attendance log when marking entries.
    }
}
