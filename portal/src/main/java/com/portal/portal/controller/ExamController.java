package com.portal.portal.controller;

import com.portal.portal.model.*;
import com.portal.portal.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class ExamController {
    Student currentStudent;
    private List<Question> currentQuestions;
    String selectedSubject;

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
    return "student_card";  // this should match your HTML template name in `templates/`
}

    @GetMapping("/select-subject")
    public String selectSubject() {
        return "subject_selection";
    }

    @PostMapping("/start-exam")
    public String startExam(@RequestParam String subject, Model model) {
        this.selectedSubject = subject;
        this.currentQuestions = examService.getQuestions(subject);
        model.addAttribute("questions", currentQuestions);
        model.addAttribute("subject", subject);
        return "exam";
    }


// @PostMapping("/submit-exam")
// public String submitExam(@RequestParam Map<String, String> allParams, Model model,Student student) {
//     List<String> userAnswers = new ArrayList<>();
//     for (int i = 0; i < currentQuestions.size(); i++) {
//         userAnswers.add(allParams.get("answers[" + i + "]"));
//     }

//     int marks = examService.calculateMarks(userAnswers, currentQuestions);
//     String grade = examService.getGrade(marks);

//     model.addAttribute("marks", marks);
//     model.addAttribute("grade", grade);
//     model.addAttribute("subject", selectedSubject);

//     // Dummy student info (you can enhance this later)
//     model.addAttribute("studentName", student);
//     model.addAttribute("rollNo", "2025-SE-001");

//     return "result";
//     }
@PostMapping("/submit-exam")
public String submitExam(@RequestParam Map<String, String> allParams, Model model) {
    List<String> userAnswers = new ArrayList<>();
    for (int i = 0; i < currentQuestions.size(); i++) {
        userAnswers.add(allParams.get("answers[" + i + "]"));
    }

    int marks = examService.calculateMarks(userAnswers, currentQuestions);
    String grade = examService.getGrade(marks);

    model.addAttribute("marks", marks);
    model.addAttribute("grade", grade);
    model.addAttribute("subject", selectedSubject);

    // ✅ Use actual student info from currentStudent
    model.addAttribute("studentName", currentStudent.getName());
    model.addAttribute("rollNo", currentStudent.getRollNumber());

    return "result";
}
}