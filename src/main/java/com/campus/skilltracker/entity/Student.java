package com.campus.skilltracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/**
 * Student entity representing a campus student.
 * One Student can have Many Skills (@OneToMany relationship).
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Email(message = "Enter a valid email")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Department is required")
    @Column(name = "department", nullable = false)
    private String department;

    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 6, message = "Year must be at most 6")
    @Column(name = "year_of_study")
    private Integer yearOfStudy;

    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * One Student has Many Skills.
     * CascadeType.ALL: deleting a student deletes their skills.
     * orphanRemoval=true: skills removed from list are deleted from DB.
     */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Skill> skills = new ArrayList<>();

    // ======================== Constructors ========================

    public Student() {}

    public Student(String fullName, String email, String department, Integer yearOfStudy, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.phoneNumber = phoneNumber;
    }

    // ======================== Helper Method ========================

    /** Bidirectional helper: links skill to this student. */
    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setStudent(this);
    }

    // ======================== Getters & Setters ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Integer getYearOfStudy() { return yearOfStudy; }
    public void setYearOfStudy(Integer yearOfStudy) { this.yearOfStudy = yearOfStudy; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }
}
