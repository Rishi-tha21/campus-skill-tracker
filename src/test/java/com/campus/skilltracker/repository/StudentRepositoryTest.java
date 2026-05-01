package com.campus.skilltracker.repository;

import com.campus.skilltracker.dto.StudentSkillDTO;
import com.campus.skilltracker.entity.Skill;
import com.campus.skilltracker.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Repository Integration Tests using @DataJpaTest (H2 in-memory database).
 * Tests the actual JPA queries and custom @Query method.
 */
@DataJpaTest
@TestPropertySource(properties = {
    "spring.sql.init.mode=never"  // Don't run data.sql during tests
})
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SkillRepository skillRepository;

    private Student savedStudent;

    @BeforeEach
    void setUp() {
        // Save a fresh student with a skill before each test
        Student student = new Student("Alice Test", "alice@test.edu", "CS", 2, "555-1111");
        savedStudent = studentRepository.save(student);

        Skill skill = new Skill("Java", "Advanced", "Oracle", savedStudent);
        skillRepository.save(skill);
    }

    @Test
    @DisplayName("Should find student by ID")
    void testFindById_Success() {
        Optional<Student> result = studentRepository.findById(savedStudent.getId());
        assertTrue(result.isPresent());
        assertEquals("Alice Test", result.get().getFullName());
    }

    @Test
    @DisplayName("Should return empty when student ID not found")
    void testFindById_NotFound() {
        Optional<Student> result = studentRepository.findById(999L);
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should find student by email")
    void testFindByEmail_Found() {
        Optional<Student> result = studentRepository.findByEmail("alice@test.edu");
        assertTrue(result.isPresent());
        assertEquals("Alice Test", result.get().getFullName());
    }

    @Test
    @DisplayName("Should return empty for non-existent email")
    void testFindByEmail_NotFound() {
        Optional<Student> result = studentRepository.findByEmail("nobody@nowhere.com");
        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should save student and auto-generate ID")
    void testSaveStudent() {
        Student newStudent = new Student("Bob Test", "bob@test.edu", "Math", 1, "555-2222");
        Student saved = studentRepository.save(newStudent);

        assertNotNull(saved.getId());
        assertEquals("Bob Test", saved.getFullName());
    }

    @Test
    @DisplayName("Custom INNER JOIN query should return student-skill records")
    void testFindAllStudentSkills_InnerJoin() {
        List<StudentSkillDTO> results = studentRepository.findAllStudentSkills();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals("Alice Test", results.get(0).getFullName());
        assertEquals("CS", results.get(0).getDepartment());
        assertEquals("Java", results.get(0).getSkillName());
        assertEquals("Advanced", results.get(0).getSkillLevel());
        assertEquals("Oracle", results.get(0).getCertificateProvider());
    }

    @Test
    @DisplayName("INNER JOIN query should return empty when no skills exist")
    void testFindAllStudentSkills_NoSkills_ReturnsEmpty() {
        // Save a student without any skills
        Student studentNoSkill = new Student("Solo Student", "solo@test.edu", "Bio", 1, "555-3333");
        studentRepository.save(studentNoSkill);

        // Clear existing skills
        skillRepository.deleteAll();

        List<StudentSkillDTO> results = studentRepository.findAllStudentSkills();
        assertTrue(results.isEmpty());
    }

    @Test
    @DisplayName("Should count all students correctly")
    void testCountStudents() {
        long count = studentRepository.count();
        assertEquals(1, count); // Only the student from setUp()
    }
}
