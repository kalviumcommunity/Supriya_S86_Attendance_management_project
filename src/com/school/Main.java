package com.school;

import java.util.List;

public class Main {

    public static void displayDirectory(List<Person> people) {
        for (Person person : people) {
            person.displayDetails();
        }
    }

    public static void main(String[] args) {
        // Initialize services
        RegistrationService registrationService = new RegistrationService();
        AttendanceService attendanceService = new AttendanceService(registrationService);

        // Register students
        Student s1 = new Student("Alice", "10th Grade");
        Student s2 = new Student("Bob", "9th Grade");
        registrationService.registerStudent(s1);
        registrationService.registerStudent(s2);

        // Register courses
        Course c1 = new Course("Mathematics");
        Course c2 = new Course("Physics");
        registrationService.registerCourse(c1);
        registrationService.registerCourse(c2);

        // Register teachers
        Teacher t1 = new Teacher("Mr. Smith", "Mathematics");
        registrationService.registerTeacher(t1);

        // Register staff
        Staff st1 = new Staff("Jane Doe", "Secretary");
        registrationService.registerStaff(st1);

        // Mark attendance
        attendanceService.markAttendance(s1, c1, "Present");
        attendanceService.markAttendance(s2, c2, "Absent");

        // Display school directory
        System.out.println("----- School Directory -----");
        registrationService.displayDirectory();

        // Display courses
        System.out.println("\n----- Courses -----");
        registrationService.displayCourses();

        // Display all attendance records
        System.out.println("\n----- Attendance (all) -----");
        attendanceService.displayAttendanceLog();

        // Display attendance for a specific student
        System.out.println("\n----- Attendance for student Alice -----");
        attendanceService.displayAttendanceLog(s1);

        // Display attendance for a specific course
        System.out.println("\n----- Attendance for course Physics -----");
        attendanceService.displayAttendanceLog(c2);

        // Save all data to files
        System.out.println("\n----- Saving data -----");
        registrationService.saveStudents("students.txt");
        registrationService.saveTeachers("teachers.txt");
        registrationService.saveStaff("staff.txt");
        registrationService.saveCourses("courses.txt");
        attendanceService.saveAttendanceRecords("attendance_log.txt");
    }
}
