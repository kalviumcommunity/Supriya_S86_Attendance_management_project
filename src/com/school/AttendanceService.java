package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AttendanceService {
    private List<AttendanceRecord> attendanceLog;
    private FileStorageService storage;
    private RegistrationService registrationService;

    public AttendanceService(RegistrationService registrationService) {
        if (registrationService == null) {
            throw new IllegalArgumentException("RegistrationService cannot be null");
        }
        this.attendanceLog = new ArrayList<>();
        this.storage = new FileStorageService();
        this.registrationService = registrationService;
    }

    public AttendanceService(RegistrationService registrationService, FileStorageService storage) {
        if (registrationService == null) {
            throw new IllegalArgumentException("RegistrationService cannot be null");
        }
        this.attendanceLog = new ArrayList<>();
        this.storage = storage != null ? storage : new FileStorageService();
        this.registrationService = registrationService;
    }

    /**
     * Mark attendance using student and course objects
     */
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

    /**
     * Mark attendance using student ID and course ID
     * Delegates to RegistrationService for lookups
     */
    public void markAttendance(int studentId, int courseId, String status) {
        if (status == null) {
            System.err.println("Invalid status for markAttendance(int, int, String)");
            return;
        }

        Optional<Student> sOpt = registrationService.getStudentById(studentId);
        Optional<Course> cOpt = registrationService.getCourseById(courseId);

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

    /**
     * Display all attendance records
     */
    public void displayAttendanceLog() {
        if (attendanceLog.isEmpty()) {
            System.out.println("No attendance records available.");
            return;
        }
        for (AttendanceRecord r : attendanceLog) {
            r.displayRecord();
        }
    }

    /**
     * Display attendance records for a specific student
     */
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

    /**
     * Display attendance records for a specific course
     */
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

    /**
     * Get the full attendance log
     */
    public List<AttendanceRecord> getAttendanceLog() {
        return new ArrayList<>(attendanceLog);
    }

    /**
     * Save attendance records to file
     */
    public void saveAttendanceRecords(String filename) {
        storage.saveData(attendanceLog, filename);
    }
}

