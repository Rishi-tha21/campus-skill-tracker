package com.campus.skilltracker.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the entire application.
 * Catches common exceptions and routes to user-friendly error pages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle DataIntegrityViolationException.
     * Triggered when a student with a duplicate email is saved.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolation(DataIntegrityViolationException ex, Model model) {
        model.addAttribute("errorTitle", "Data Conflict Error");
        model.addAttribute("errorMessage",
                "A student with this email address already exists. " +
                "Please use a different email and try again.");
        return "error";
    }

    /**
     * Handle general RuntimeException (e.g., student not found).
     */
    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        model.addAttribute("errorTitle", "Application Error");
        model.addAttribute("errorMessage", ex.getMessage());
        return "error";
    }

    /**
     * Catch-all handler for any unexpected exception.
     */
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorTitle", "Unexpected Error");
        model.addAttribute("errorMessage", "Something went wrong. Please try again later.");
        return "error";
    }
}
