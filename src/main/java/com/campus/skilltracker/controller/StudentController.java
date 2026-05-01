package com.campus.skilltracker.controller;

import com.campus.skilltracker.entity.Student;
import com.campus.skilltracker.service.SkillService;
import com.campus.skilltracker.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Main controller for Student CRUD operations.
 * Handles: Dashboard, List All, Add, Edit, Update, and INNER JOIN view.
 */
@Controller
public class StudentController {

    private final StudentService studentService;
    private final SkillService skillService;

    public StudentController(StudentService studentService, SkillService skillService) {
        this.studentService = studentService;
        this.skillService = skillService;
    }

    // ======================== DASHBOARD ========================

    /** GET / - Show dashboard with summary stats */
    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.countStudents());
        model.addAttribute("totalSkills", skillService.countSkills());
        return "home";
    }

    // ======================== READ ========================

    /** GET /students - List all students */
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // ======================== CREATE ========================

    /** GET /students/new - Show blank add-student form */
    @GetMapping("/students/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    /** POST /students - Save new student */
    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "add-student"; // Return to form with validation errors
        }
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Student '" + student.getFullName() + "' added successfully!");
        return "redirect:/students";
    }

    // ======================== UPDATE ========================

    /** GET /students/edit/{id} - Show pre-filled edit form */
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "edit-student";
    }

    /** POST /students/update/{id} - Process update form submission */
    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            student.setId(id); // Keep the ID so the form action URL stays correct
            return "edit-student";
        }
        studentService.updateStudent(id, student);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Student updated successfully!");
        return "redirect:/students";
    }

    // ======================== INNER JOIN QUERY ========================

    /** GET /students/skills - Show INNER JOIN result (Student + Skill details) */
    @GetMapping("/students/skills")
    public String listStudentSkills(Model model) {
        model.addAttribute("studentSkills", studentService.getAllStudentSkills());
        return "student-skills";
    }
}
