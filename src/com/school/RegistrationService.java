package com.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RegistrationService {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Staff> staffMembers;
    private List<Course> courses;
    private FileStorageService storage;

    public RegistrationService() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.storage = new FileStorageService();
    }

    public RegistrationService(FileStorageService storage) {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.storage = storage != null ? storage : new FileStorageService();
    }

    // ==================== Student Methods ====================

    /**
     * Register a new student
     */
    public void registerStudent(Student student) {
        if (student == null) {
            System.err.println("Cannot register null student");
            return;
        }
        students.add(student);
        System.out.println("Student registered: " + student.getName());
    }

    /**
     * Get all students
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Look up a student by ID
     */
    public Optional<Student> getStudentById(int studentId) {
        return students.stream()
                .filter(s -> s.getId() == studentId)
                .findFirst();
    }

    /**
     * Save all students to file
     */
    public void saveStudents(String filename) {
        storage.saveData(new ArrayList<>(students), filename);
    }

    // ==================== Teacher Methods ====================

    /**
     * Register a new teacher
     */
    public void registerTeacher(Teacher teacher) {
        if (teacher == null) {
            System.err.println("Cannot register null teacher");
            return;
        }
        teachers.add(teacher);
        System.out.println("Teacher registered: " + teacher.getName());
    }

    /**
     * Get all teachers
     */
    public List<Teacher> getAllTeachers() {
        return new ArrayList<>(teachers);
    }

    /**
     * Look up a teacher by ID
     */
    public Optional<Teacher> getTeacherById(int teacherId) {
        return teachers.stream()
                .filter(t -> t.getId() == teacherId)
                .findFirst();
    }

    /**
     * Save all teachers to file
     */
    public void saveTeachers(String filename) {
        storage.saveData(new ArrayList<>(teachers), filename);
    }

    // ==================== Staff Methods ====================

    /**
     * Register a new staff member
     */
    public void registerStaff(Staff staff) {
        if (staff == null) {
            System.err.println("Cannot register null staff");
            return;
        }
        staffMembers.add(staff);
        System.out.println("Staff registered: " + staff.getName());
    }

    /**
     * Get all staff members
     */
    public List<Staff> getAllStaff() {
        return new ArrayList<>(staffMembers);
    }

    /**
     * Look up a staff member by ID
     */
    public Optional<Staff> getStaffById(int staffId) {
        return staffMembers.stream()
                .filter(s -> s.getId() == staffId)
                .findFirst();
    }

    /**
     * Save all staff to file
     */
    public void saveStaff(String filename) {
        storage.saveData(new ArrayList<>(staffMembers), filename);
    }

    // ==================== Course Methods ====================

    /**
     * Create and register a new course with default capacity
     */
    public Course createCourse(String courseName) {
        return createCourse(courseName, 30);
    }

    /**
     * Create and register a new course with specified capacity
     */
    public Course createCourse(String courseName, int capacity) {
        if (courseName == null || courseName.trim().isEmpty()) {
            System.err.println("Cannot create course with empty name");
            return null;
        }
        if (capacity <= 0) {
            System.err.println("Course capacity must be positive");
            return null;
        }
        Course course = new Course(courseName, capacity);
        courses.add(course);
        System.out.println("Course created: " + course.getCourseName() + " (Capacity: " + capacity + ")");
        return course;
    }

    /**
     * Register a new course
     */
    public void registerCourse(Course course) {
        if (course == null) {
            System.err.println("Cannot register null course");
            return;
        }
        courses.add(course);
        System.out.println("Course registered: " + course.getCourseName());
    }

    /**
     * Enroll a student in a course
     */
    public boolean enrollStudentInCourse(Student student, Course course) {
        if (student == null) {
            System.err.println("Cannot enroll null student");
            return false;
        }
        if (course == null) {
            System.err.println("Cannot enroll student in null course");
            return false;
        }
        
        boolean enrolled = course.addStudent(student);
        if (enrolled) {
            System.out.println("Student " + student.getName() + " successfully enrolled in " + course.getCourseName());
        } else {
            System.out.println("Failed to enroll student " + student.getName() + " in " + course.getCourseName());
        }
        return enrolled;
    }

    /**
     * Get all courses
     */
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    /**
     * Look up a course by ID
     */
    public Optional<Course> getCourseById(int courseId) {
        return courses.stream()
                .filter(c -> c.getCourseId() == courseId)
                .findFirst();
    }

    /**
     * Save all courses to file
     */
    public void saveCourses(String filename) {
        storage.saveData(new ArrayList<>(courses), filename);
    }

    // ==================== Unified Entity Retrieval ====================

    /**
     * Get all registered entities (students, teachers, staff) as Person objects
     */
    public List<Person> getAllPersons() {
        List<Person> allPersons = new ArrayList<>();
        allPersons.addAll(students);
        allPersons.addAll(teachers);
        allPersons.addAll(staffMembers);
        return allPersons;
    }

    /**
     * Display directory of all registered persons
     */
    public void displayDirectory() {
        List<Person> allPersons = getAllPersons();
        if (allPersons.isEmpty()) {
            System.out.println("No persons registered.");
            return;
        }
        for (Person person : allPersons) {
            person.displayDetails();
        }
    }

    /**
     * Display all courses
     */
    public void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }
        for (Course course : courses) {
            course.displayDetails();
        }
    }
}
