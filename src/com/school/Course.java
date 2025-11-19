package com.school;

import java.util.ArrayList;
import java.util.List;

public class Course implements Storable {
    private static int idCounter = 1;
    private int courseId;
    private String courseName;
    private int capacity;
    private List<Student> enrolledStudents;

    public Course(String courseName) {
        this.courseId = idCounter++;
        this.courseName = courseName;
        this.capacity = 30; // Default capacity
        this.enrolledStudents = new ArrayList<>();
    }

    public Course(String courseName, int capacity) {
        this.courseId = idCounter++;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getEnrolledStudents() {
        return new ArrayList<>(enrolledStudents);
    }

    public int getEnrollmentCount() {
        return enrolledStudents.size();
    }

    public boolean addStudent(Student student) {
        if (student == null) {
            System.err.println("Cannot enroll null student");
            return false;
        }
        if (enrolledStudents.contains(student)) {
            System.err.println("Student " + student.getName() + " is already enrolled in " + courseName);
            return false;
        }
        if (enrolledStudents.size() >= capacity) {
            System.err.println("Course " + courseName + " is at full capacity (" + capacity + ")");
            return false;
        }
        enrolledStudents.add(student);
        return true;
    }

    public void displayDetails() {
        System.out.println("Course ID: " + courseId + ", Name: " + courseName + 
                           ", Capacity: " + capacity + ", Enrolled: " + enrolledStudents.size());
    }

    @Override
    public String toDataString() {
        return courseId + "," + courseName + "," + capacity;
    }
}
