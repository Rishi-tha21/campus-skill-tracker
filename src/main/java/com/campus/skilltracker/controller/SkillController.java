package com.campus.skilltracker.controller;

import com.campus.skilltracker.entity.Skill;
import com.campus.skilltracker.service.SkillService;
import com.campus.skilltracker.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for adding skills to students.
 */
@Controller
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;
    private final StudentService studentService;

    public SkillController(SkillService skillService, StudentService studentService) {
        this.skillService = skillService;
        this.studentService = studentService;
    }

    /** GET /skills/add?studentId={id} - Show add-skill form for a student */
    @GetMapping("/add")
    public String showAddSkillForm(@RequestParam Long studentId, Model model) {
        model.addAttribute("student", studentService.getStudentById(studentId));
        model.addAttribute("skill", new Skill());
        return "add-skill";
    }

    /** POST /skills/add - Save a new skill for a student */
    @PostMapping("/add")
    public String addSkill(@RequestParam Long studentId,
                           @Valid @ModelAttribute("skill") Skill skill,
                           BindingResult bindingResult,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("student", studentService.getStudentById(studentId));
            return "add-skill";
        }
        skillService.addSkillToStudent(studentId, skill);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Skill '" + skill.getSkillName() + "' added successfully!");
        return "redirect:/students";
    }
}
