package com.portal.portal.controller;

import com.portal.portal.model.*;
import com.portal.portal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Enhanced Exam Controller with DSA Services
 */
@Controller
public class ExamController {
    Student currentStudent;
    private List<Question> currentQuestions;
    String selectedSubject;

    @Autowired
    private ExamService examService;
    
    @Autowired
    private LeaderboardService leaderboardService;
    
    @Autowired
    private StudentRankingService studentRankingService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/register")
    public String showForm() {
        return "form";
    }

    @PostMapping("/submit-form")
    public String submitForm(@ModelAttribute Student student, Model model) {
        student.setRollNumber("ROLL" + new Random().nextInt(1000));
        this.currentStudent = student;

        saveStudentDataToJSON(student);

        model.addAttribute("student", student);
        return "student_card";
    }

    @GetMapping("/select-subject")
    public String selectSubject(Model model) {
        return "subject_selection";
    }

    @PostMapping("/start-exam")
    public String startExam(@RequestParam String subject, Model model) {
        this.selectedSubject = subject;
        // Using QuestionBankService which uses HashMap (DSA: HashMap)
        this.currentQuestions = examService.getQuestions(subject);
        model.addAttribute("questions", currentQuestions);
        model.addAttribute("subject", subject);
        return "exam";
    }

    @PostMapping("/submit-exam")
    public String submitExam(@RequestParam Map<String, String> allParams, Model model) {
        List<String> userAnswers = new ArrayList<>();
        for (int i = 0; i < currentQuestions.size(); i++) {
            userAnswers.add(allParams.get("answers[" + i + "]"));
        }

        int totalQuestions = currentQuestions.size();
        int totalMarks = totalQuestions;

        int marks = examService.calculateMarks(userAnswers, currentQuestions);
        double percentage = (marks * 100.0) / totalMarks;
        String percentageStr = String.format("%.2f", percentage);
        String grade = examService.getGrade(marks);
        
        // Create result object
        Result result = new Result(currentStudent, selectedSubject, marks, grade, percentage);
        
        // Add to leaderboard (DSA: Priority Queue/Max Heap)
        leaderboardService.addResult(result);
        
        // Add to ranking tree (DSA: Binary Search Tree)
        studentRankingService.insertResult(result);

        saveResultToJSON(currentStudent, selectedSubject, marks, grade, percentageStr);

        model.addAttribute("marks", marks);
        model.addAttribute("grade", grade);
        model.addAttribute("subject", selectedSubject);
        model.addAttribute("percentage", percentageStr);
        model.addAttribute("studentName", currentStudent.getName());
        model.addAttribute("rollNo", currentStudent.getRollNumber());
        
        // Add DSA-based insights
        model.addAttribute("topPerformer", leaderboardService.getTopPerformer());
        model.addAttribute("averageMarks", String.format("%.2f", leaderboardService.getAverageMarks()));

        return "result";
    }
    
    /**
     * View Leaderboard - Uses Priority Queue (Max Heap)
     */
    @GetMapping("/leaderboard")
    public String viewLeaderboard(Model model) {
        List<Result> topPerformers = leaderboardService.getTopNPerformers(10);
        model.addAttribute("topPerformers", topPerformers);
        model.addAttribute("totalResults", leaderboardService.getTotalResults());
        model.addAttribute("averageMarks", String.format("%.2f", leaderboardService.getAverageMarks()));
        return "leaderboard";
    }
    
    /**
     * View Rankings - Uses Binary Search Tree
     */
    @GetMapping("/rankings")
    public String viewRankings(Model model) {
        Object[] rankedResults = studentRankingService.getRankedResults().toArray();
        model.addAttribute("rankedResults", rankedResults);
        model.addAttribute("totalResults", studentRankingService.getTotalResults());
        model.addAttribute("highestScore", studentRankingService.getHighestScore());
        model.addAttribute("lowestScore", studentRankingService.getLowestScore());
        return "rankings";
    }

    // ✅ Save student info as JSON
    private void saveStudentDataToJSON(Student student) {
        String json = "{\n" +
                "  \"name\": \"" + student.getName() + "\",\n" +
                "  \"rollNumber\": \"" + student.getRollNumber() + "\",\n" +
                "  \"email\": \"" + student.getEmail() + "\"\n" +
                "}\n";

        try (FileWriter writer = new FileWriter("students.json", true)) {
            writer.write(json);
            writer.write(",\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Save result info as JSON
    private void saveResultToJSON(Student student, String subject, int marks, String grade, String percentage) {
        String json = "{\n" +
                "  \"name\": \"" + student.getName() + "\",\n" +
                "  \"rollNumber\": \"" + student.getRollNumber() + "\",\n" +
                "  \"subject\": \"" + subject + "\",\n" +
                "  \"marks\": " + marks + ",\n" +
                "  \"percentage\": \"" + percentage + "\",\n" +
                "  \"grade\": \"" + grade + "\"\n" +
                "}\n";

        try (FileWriter writer = new FileWriter("results.json", true)) {
            writer.write(json);
            writer.write(",\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
