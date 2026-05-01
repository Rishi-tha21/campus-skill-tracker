package com.campus.skilltracker.dto;

/**
 * Projection interface for the INNER JOIN custom query result.
 * Spring Data JPA maps each column alias to a getter method.
 * Used by StudentRepository.findAllStudentSkills().
 */
public interface StudentSkillDTO {
    String getFullName();
    String getDepartment();
    String getSkillName();
    String getSkillLevel();
    String getCertificateProvider();
}
