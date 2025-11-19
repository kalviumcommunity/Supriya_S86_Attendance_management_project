package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AttendanceService {
    private List<AttendanceRecord> attendanceLog;
    private FileStorageService storage;

    public AttendanceService() {
        this.attendanceLog = new ArrayList<>();
        this.storage = new FileStorageService();
    }

    public AttendanceService(FileStorageService storage) {
        this.attendanceLog = new ArrayList<>();
        this.storage = storage != null ? storage : new FileStorageService();
    }

    // Mark attendance using objects
    public void markAttendance(Student student, Course course, String status) {
        if (student == null || course == null || status == null) {
            System.err.println("Invalid parameters for markAttendance(Student, Course, String)");
            return;
        }
        AttendanceRecord record = new AttendanceRecord(student, course, status);
        attendanceLog.add(record);
        // Persist
        storage.saveData(attendanceLog, "attendance_log.txt");
    }

    // Mark attendance using ids; perform lookups from provided master lists
    public void markAttendance(int studentId, int courseId, String status, List<Student> allStudents, List<Course> allCourses) {
        if (status == null || allStudents == null || allCourses == null) {
            System.err.println("Invalid parameters for markAttendance(int, int, String, List<Student>, List<Course>)");
            return;
        }

        Optional<Student> sOpt = allStudents.stream().filter(s -> s.getId() == studentId).findFirst();
        Optional<Course> cOpt = allCourses.stream().filter(c -> c.getCourseId() == courseId).findFirst();

        if (!sOpt.isPresent()) {
            System.err.println("Student with ID " + studentId + " not found. Skipping attendance.");
            return;
        }

        if (!cOpt.isPresent()) {
            System.err.println("Course with ID " + courseId + " not found. Skipping attendance.");
            return;
        }

        AttendanceRecord record = new AttendanceRecord(sOpt.get(), cOpt.get(), status);
        attendanceLog.add(record);
        // Persist
        storage.saveData(attendanceLog, "attendance_log.txt");
    }

    // Display everything
    public void displayAttendanceLog() {
        if (attendanceLog.isEmpty()) {
            System.out.println("No attendance records available.");
            return;
        }
        for (AttendanceRecord r : attendanceLog) r.displayRecord();
    }

    // Display for a specific student
    public void displayAttendanceLog(Student student) {
        if (student == null) {
            System.err.println("Student is null for displayAttendanceLog(Student)");
            return;
        }
        List<AttendanceRecord> filtered = attendanceLog.stream()
                .filter(r -> r.getStudent().getId() == student.getId())
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No attendance records for student: " + student.getName());
            return;
        }
        filtered.forEach(AttendanceRecord::displayRecord);
    }

    // Display for a specific course
    public void displayAttendanceLog(Course course) {
        if (course == null) {
            System.err.println("Course is null for displayAttendanceLog(Course)");
            return;
        }
        List<AttendanceRecord> filtered = attendanceLog.stream()
                .filter(r -> r.getCourse().getCourseId() == course.getCourseId())
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("No attendance records for course: " + course.getCourseName());
            return;
        }
        filtered.forEach(AttendanceRecord::displayRecord);
    }

    // Expose the log if needed
    public List<AttendanceRecord> getAttendanceLog() {
        return attendanceLog;
    }
}
