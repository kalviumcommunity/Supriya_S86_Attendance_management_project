package com.school;

public class Student extends Person implements Storable {
    private String gradeLevel;

    public Student(String name, String gradeLevel) {
        super(name);
        this.gradeLevel = gradeLevel;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + id + ", Name: " + name + ", Grade: " + gradeLevel);
    }

    @Override
    public String toDataString() {
        return id + "," + name + "," + gradeLevel;
    }
}
