package com.campus.skilltracker.service;

import com.campus.skilltracker.entity.Skill;
import com.campus.skilltracker.entity.Student;
import com.campus.skilltracker.repository.SkillRepository;
import com.campus.skilltracker.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for SkillService using JUnit 5 + Mockito.
 */
@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private SkillService skillService;

    private Student student;
    private Skill skill;

    @BeforeEach
    void setUp() {
        student = new Student("John Doe", "john@campus.edu", "CS", 3, "555-0101");
        student.setId(1L);

        skill = new Skill();
        skill.setSkillName("Java");
        skill.setSkillLevel("Advanced");
        skill.setCertificateProvider("Oracle");
    }

    // ======================== addSkillToStudent() ========================

    @Test
    @DisplayName("Should successfully add skill to an existing student")
    void testAddSkillToStudent_Success() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        Skill result = skillService.addSkillToStudent(1L, skill);

        assertNotNull(result);
        assertEquals("Java", result.getSkillName());
        assertEquals(student, skill.getStudent()); // bidirectional check
        verify(studentRepository).findById(1L);
        verify(skillRepository).save(skill);
    }

    @Test
    @DisplayName("Should throw RuntimeException when adding skill to non-existent student")
    void testAddSkillToStudent_StudentNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                skillService.addSkillToStudent(99L, skill));

        verify(skillRepository, never()).save(any());
    }

    // ======================== countSkills() ========================

    @Test
    @DisplayName("Should return correct skill count")
    void testCountSkills() {
        when(skillRepository.count()).thenReturn(10L);

        long count = skillService.countSkills();

        assertEquals(10L, count);
        verify(skillRepository).count();
    }

    @Test
    @DisplayName("Should return zero when no skills exist")
    void testCountSkills_ZeroSkills() {
        when(skillRepository.count()).thenReturn(0L);

        long count = skillService.countSkills();

        assertEquals(0L, count);
    }
}
