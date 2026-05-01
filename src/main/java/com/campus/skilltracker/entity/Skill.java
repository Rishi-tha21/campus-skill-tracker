package com.campus.skilltracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Skill entity representing a skill belonging to a Student.
 * Many Skills belong to One Student (@ManyToOne relationship).
 */
@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Skill name is required")
    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @NotBlank(message = "Skill level is required")
    @Column(name = "skill_level", nullable = false)
    private String skillLevel;

    @Column(name = "certificate_provider")
    private String certificateProvider;

    /**
     * Many Skills belong to One Student.
     * LAZY fetch for performance - only loads when accessed.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // ======================== Constructors ========================

    public Skill() {}

    public Skill(String skillName, String skillLevel, String certificateProvider, Student student) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
        this.certificateProvider = certificateProvider;
        this.student = student;
    }

    // ======================== Getters & Setters ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getSkillLevel() { return skillLevel; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }

    public String getCertificateProvider() { return certificateProvider; }
    public void setCertificateProvider(String certificateProvider) { this.certificateProvider = certificateProvider; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
}
