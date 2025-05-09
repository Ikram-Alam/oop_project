package com.examportal.controller;

import com.examportal.model.*;
import com.examportal.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class ExamController {
    private Student currentStudent;
    private List<Question> currentQuestions;
    private String selectedSubject;

    @Autowired
    private ExamService examService;

    @GetMapping("/")
    public String showForm() {
        return "form";
    }

    @PostMapping("/submit-form")
    public String submitForm(@ModelAttribute Student student, Model model) {
        student.setRollNumber("ROLL" + new Random().nextInt(1000));
        this.currentStudent = student;
        model.addAttribute("student", student);
        return "student_card";
    }

    @GetMapping("/select-subject")
    public String selectSubject() {
        return "subject_selection";
    }

    @PostMapping("/start-exam")
    public String startExam(@RequestParam String subject, Model model) {
        this.selectedSubject = subject;
        currentQuestions = examService.getQuestions(subject);
        model.addAttribute("questions", currentQuestions);
        model.addAttribute("subject", subject);
        return "exam";
    }

    @PostMapping("/submit-exam")
    public String submitExam(@RequestParam List<String> answers, Model model) {
        int marks = examService.calculateMarks(answers, currentQuestions);
        String grade = examService.getGrade(marks);
        Result result = new Result();
        result.setStudent(currentStudent);
        result.setMarks(marks);
        result.setGrade(grade);
        model.addAttribute("result", result);
        return "result";
    }
}
