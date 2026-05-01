package com.campus.skilltracker.repository;

import com.campus.skilltracker.dto.StudentSkillDTO;
import com.campus.skilltracker.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Student entity.
 * Extends JpaRepository for standard CRUD operations.
 * Includes a custom INNER JOIN query using JPQL.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Custom INNER JOIN query using JPQL.
     * Fetches Student and Skill details together.
     * Only returns students who have at least one skill (INNER JOIN behaviour).
     */
    @Query("SELECT s.fullName AS fullName, s.department AS department, " +
           "sk.skillName AS skillName, sk.skillLevel AS skillLevel, " +
           "sk.certificateProvider AS certificateProvider " +
           "FROM Student s INNER JOIN s.skills sk " +
           "ORDER BY s.fullName")
    List<StudentSkillDTO> findAllStudentSkills();

    /**
     * Find a student by email (used for duplicate check).
     */
    Optional<Student> findByEmail(String email);
}
