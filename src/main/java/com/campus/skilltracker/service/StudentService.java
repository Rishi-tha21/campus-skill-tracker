package com.campus.skilltracker.service;

import com.campus.skilltracker.dto.StudentSkillDTO;
import com.campus.skilltracker.entity.Student;
import com.campus.skilltracker.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service layer for Student business logic.
 * Handles CRUD operations and delegates to StudentRepository.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Fetch all students from the database.
     * Used by the READ operation (students list page).
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Fetch a single student by their ID.
     * Throws NoSuchElementException if not found (handled by GlobalExceptionHandler).
     */
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    /**
     * Save a new student to the database.
     * Throws DataIntegrityViolationException if email already exists.
     */
    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Update an existing student's details.
     * Fetches the existing record and updates only the relevant fields.
     */
    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existing = getStudentById(id);
        existing.setFullName(updatedStudent.getFullName());
        existing.setEmail(updatedStudent.getEmail());
        existing.setDepartment(updatedStudent.getDepartment());
        existing.setYearOfStudy(updatedStudent.getYearOfStudy());
        existing.setPhoneNumber(updatedStudent.getPhoneNumber());
        return studentRepository.save(existing);
    }

    /**
     * Custom INNER JOIN query result.
     * Returns students who have at least one skill.
     */
    public List<StudentSkillDTO> getAllStudentSkills() {
        return studentRepository.findAllStudentSkills();
    }

    /**
     * Count total students (used for dashboard stats).
     */
    public long countStudents() {
        return studentRepository.count();
    }
}
