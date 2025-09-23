package com.school;

public class Student implements Storable {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String gradeLevel;

    public Student(String name, String gradeLevel) {
        this.id = idCounter++;
        this.name = name;
        this.gradeLevel = gradeLevel;
    }

    public int getId() {
        return id;
    }

    public void displayDetails() {
        System.out.println("Student ID: " + id + ", Name: " + name + ", Grade: " + gradeLevel);
    }

    @Override
    public String toDataString() {
        return id + "," + name + "," + gradeLevel;
    }
}
