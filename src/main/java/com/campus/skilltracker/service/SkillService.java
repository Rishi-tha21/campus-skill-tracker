package com.campus.skilltracker.service;

import com.campus.skilltracker.entity.Skill;
import com.campus.skilltracker.entity.Student;
import com.campus.skilltracker.repository.SkillRepository;
import com.campus.skilltracker.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service layer for Skill business logic.
 * Handles adding skills to students and retrieving skill data.
 */
@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final StudentRepository studentRepository;

    public SkillService(SkillRepository skillRepository, StudentRepository studentRepository) {
        this.skillRepository = skillRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * Add a new skill to a student.
     * Fetches the student, links the skill, and persists.
     */
    @Transactional
    public Skill addSkillToStudent(Long studentId, Skill skill) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        student.addSkill(skill); // bidirectional binding
        return skillRepository.save(skill);
    }

    /**
     * Get all skills for a specific student.
     */
    public List<Skill> getSkillsByStudentId(Long studentId) {
        return skillRepository.findByStudentId(studentId);
    }

    /**
     * Count total skills (used for dashboard stats).
     */
    public long countSkills() {
        return skillRepository.count();
    }
}
