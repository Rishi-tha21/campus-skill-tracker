package com.campus.skilltracker.service;

import com.campus.skilltracker.dto.StudentSkillDTO;
import com.campus.skilltracker.entity.Student;
import com.campus.skilltracker.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit Tests for StudentService using JUnit 5 + Mockito.
 * The repository is mocked — no database is needed.
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student("John Doe", "john.doe@campus.edu", "Computer Science", 3, "555-0101");
        student1.setId(1L);

        student2 = new Student("Jane Smith", "jane.smith@campus.edu", "Mathematics", 2, "555-0102");
        student2.setId(2L);
    }

    // ======================== getAllStudents() ========================

    @Test
    @DisplayName("Should return all students from repository")
    void testGetAllStudents_ReturnsAllStudents() {
        // Arrange
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        // Act
        List<Student> result = studentService.getAllStudents();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Jane Smith", result.get(1).getFullName());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return empty list when no students exist")
    void testGetAllStudents_ReturnsEmptyList() {
        when(studentRepository.findAll()).thenReturn(List.of());
        List<Student> result = studentService.getAllStudents();
        assertTrue(result.isEmpty());
    }

    // ======================== getStudentById() ========================

    @Test
    @DisplayName("Should return student when found by valid ID")
    void testGetStudentById_Found() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));

        Student result = studentService.getStudentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("john.doe@campus.edu", result.getEmail());
        verify(studentRepository).findById(1L);
    }

    @Test
    @DisplayName("Should throw RuntimeException when student not found")
    void testGetStudentById_NotFound_ThrowsException() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                studentService.getStudentById(99L));

        assertTrue(exception.getMessage().contains("99"));
    }

    // ======================== saveStudent() ========================

    @Test
    @DisplayName("Should save and return new student")
    void testSaveStudent_Success() {
        when(studentRepository.save(any(Student.class))).thenReturn(student1);

        Student result = studentService.saveStudent(student1);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(studentRepository, times(1)).save(student1);
    }

    // ======================== updateStudent() ========================

    @Test
    @DisplayName("Should update existing student and return updated object")
    void testUpdateStudent_Success() {
        Student updated = new Student("John Updated", "john.new@campus.edu", "Data Science", 4, "555-9999");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student1));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student result = studentService.updateStudent(1L, updated);

        assertEquals("John Updated", result.getFullName());
        assertEquals("john.new@campus.edu", result.getEmail());
        assertEquals("Data Science", result.getDepartment());
        assertEquals(4, result.getYearOfStudy());
        verify(studentRepository).findById(1L);
        verify(studentRepository).save(any(Student.class));
    }

    @Test
    @DisplayName("Should throw RuntimeException when updating non-existent student")
    void testUpdateStudent_StudentNotFound() {
        Student updated = new Student("Ghost", "ghost@campus.edu", "None", 1, "000-0000");
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> studentService.updateStudent(99L, updated));
    }

    // ======================== countStudents() ========================

    @Test
    @DisplayName("Should return correct student count")
    void testCountStudents() {
        when(studentRepository.count()).thenReturn(10L);

        long count = studentService.countStudents();

        assertEquals(10L, count);
        verify(studentRepository).count();
    }

    // ======================== getAllStudentSkills() ========================

    @Test
    @DisplayName("Should return student-skill DTO list from repository")
    void testGetAllStudentSkills_ReturnsData() {
        StudentSkillDTO mockDto = mock(StudentSkillDTO.class);
        when(mockDto.getFullName()).thenReturn("John Doe");
        when(mockDto.getSkillName()).thenReturn("Java Programming");
        when(studentRepository.findAllStudentSkills()).thenReturn(List.of(mockDto));

        List<StudentSkillDTO> result = studentService.getAllStudentSkills();

        assertFalse(result.isEmpty());
        assertEquals("John Doe", result.get(0).getFullName());
        assertEquals("Java Programming", result.get(0).getSkillName());
    }

    @Test
    @DisplayName("Should return empty list when no student-skill records exist")
    void testGetAllStudentSkills_ReturnsEmpty() {
        when(studentRepository.findAllStudentSkills()).thenReturn(List.of());

        List<StudentSkillDTO> result = studentService.getAllStudentSkills();

        assertTrue(result.isEmpty());
    }
}
