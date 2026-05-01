package com.campus.skilltracker.repository;

import com.campus.skilltracker.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for Skill entity.
 * Extends JpaRepository for standard CRUD operations.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    /**
     * Find all skills belonging to a specific student.
     */
    List<Skill> findByStudentId(Long studentId);
}
