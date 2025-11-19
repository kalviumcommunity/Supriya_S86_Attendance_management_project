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
        Student s3 = new Student("Charlie", "10th Grade");
        Student s4 = new Student("Diana", "9th Grade");
        registrationService.registerStudent(s1);
        registrationService.registerStudent(s2);
        registrationService.registerStudent(s3);
        registrationService.registerStudent(s4);

        // Create courses with capacity
        System.out.println("\n----- Creating Courses with Capacity -----");
        Course c1 = registrationService.createCourse("Mathematics", 3);
        Course c2 = registrationService.createCourse("Physics", 2);

        // Register teachers
        Teacher t1 = new Teacher("Mr. Smith", "Mathematics");
        Teacher t2 = new Teacher("Ms. Johnson", "Physics");
        registrationService.registerTeacher(t1);
        registrationService.registerTeacher(t2);

        // Register staff
        Staff st1 = new Staff("Jane Doe", "Secretary");
        registrationService.registerStaff(st1);

        // Enroll students in courses
        System.out.println("\n----- Enrolling Students in Courses -----");
        registrationService.enrollStudentInCourse(s1, c1);
        registrationService.enrollStudentInCourse(s2, c1);
        registrationService.enrollStudentInCourse(s3, c1);
        registrationService.enrollStudentInCourse(s4, c1); // Should fail - exceeds capacity
        
        System.out.println();
        registrationService.enrollStudentInCourse(s1, c2);
        registrationService.enrollStudentInCourse(s2, c2);
        registrationService.enrollStudentInCourse(s3, c2); // Should fail - exceeds capacity

        // Display school directory
        System.out.println("\n----- School Directory -----");
        registrationService.displayDirectory();

        // Display courses with updated capacity and enrollment info
        System.out.println("\n----- Courses (with Capacity and Enrollment) -----");
        registrationService.displayCourses();

        // Mark attendance
        System.out.println("\n----- Marking Attendance -----");
        attendanceService.markAttendance(s1, c1, "Present");
        attendanceService.markAttendance(s2, c2, "Absent");
        attendanceService.markAttendance(s3, c1, "Present");

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
        
        System.out.println("\n----- Part 10: Capacity Management & SOLID Principles Reflection -----");
        System.out.println("✓ Added capacity feature to Course class with internal enrolledStudents list");
        System.out.println("✓ Updated Course.displayDetails() to show capacity and enrollment count");
        System.out.println("✓ Updated Course.toDataString() to save capacity information");
        System.out.println("✓ Modified RegistrationService.createCourse() to accept capacity parameter");
        System.out.println("✓ Added RegistrationService.enrollStudentInCourse() for enrollment logic");
        System.out.println("✓ Demonstrated course creation with capacity and student enrollment attempts");
        System.out.println("✓ Demonstrated handling of enrollment exceeding capacity");
        System.out.println("\nSOLID Principles Applied:");
        System.out.println("• Single Responsibility: Each class has one reason to change");
        System.out.println("• Open/Closed: Classes open for extension (e.g., Course capacity feature)");
        System.out.println("• Liskov Substitution: All Person subclasses are substitutable");
        System.out.println("• Interface Segregation: Storable interface is focused and specific");
        System.out.println("• Dependency Inversion: Services depend on abstractions, not concrete classes");
    }
}
