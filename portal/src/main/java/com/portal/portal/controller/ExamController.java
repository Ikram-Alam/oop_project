package com.portal.portal.controller;

import com.portal.portal.model.*;
import com.portal.portal.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
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

        saveStudentDataToJSON(student);  // ✅ Save student to JSON

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

        saveResultToJSON(currentStudent, selectedSubject, marks, grade, percentageStr);  // ✅ Save result to JSON

        model.addAttribute("marks", marks);
        model.addAttribute("grade", grade);
        model.addAttribute("subject", selectedSubject);
        model.addAttribute("percentage", percentageStr);
        model.addAttribute("studentName", currentStudent.getName());
        model.addAttribute("rollNo", currentStudent.getRollNumber());

        return "result";
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


// package com.portal.portal.controller;

// import com.portal.portal.model.*;
// import com.portal.portal.service.ExamService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import java.util.*;

// @Controller
// public class ExamController {
//     Student currentStudent;
//     private List<Question> currentQuestions;
//     String selectedSubject;

//     @Autowired
//     private ExamService examService;

//     @GetMapping("/")
//     public String showForm() {
//         return "form";
//     }

// @PostMapping("/submit-form")
// public String submitForm(@ModelAttribute Student student, Model model) {
//     student.setRollNumber("ROLL" + new Random().nextInt(1000));
//     this.currentStudent = student;
//     model.addAttribute("student", student);
//     return "student_card";  // this should match your HTML template name in `templates/`
// }

//     @GetMapping("/select-subject")
//     public String selectSubject() {
//         return "subject_selection";
//     }

//     @PostMapping("/start-exam")
//     public String startExam(@RequestParam String subject, Model model) {
//         this.selectedSubject = subject;
//         this.currentQuestions = examService.getQuestions(subject);
//         model.addAttribute("questions", currentQuestions);
//         model.addAttribute("subject", subject);
//         return "exam";
//     }


// @PostMapping("/submit-exam")
// public String submitExam(@RequestParam Map<String, String> allParams, Model model) {
//     List<String> userAnswers = new ArrayList<>();
//     for (int i = 0; i < currentQuestions.size(); i++) {
//         userAnswers.add(allParams.get("answers[" + i + "]"));
//     }

//     int totalQuestions = currentQuestions.size();  // dynamic & safe
//     int totalMarks = totalQuestions * 1;           // because each correct = 1 marks

//     int marks = examService.calculateMarks(userAnswers, currentQuestions);  // correct total
//     double percentage = (marks * 100.0) / totalMarks;

//     model.addAttribute("marks", marks);
//     model.addAttribute("grade", examService.getGrade(marks));
//     model.addAttribute("subject", selectedSubject);
//     model.addAttribute("percentage", String.format("%.2f", percentage));

//     model.addAttribute("studentName", currentStudent.getName());
//     model.addAttribute("rollNo", currentStudent.getRollNumber());

//     return "result";
//   }
// }


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
// @PostMapping("/submit-exam")
// public String submitExam(@RequestParam Map<String, String> allParams, Model model) {
//     List<String> userAnswers = new ArrayList<>();
//     for (int i = 0; i < currentQuestions.size(); i++) {
//         userAnswers.add(allParams.get("answers[" + i + "]"));
//     }

//     int marks = examService.calculateMarks(userAnswers, currentQuestions);
//     String grade = examService.getGrade(marks);

//     model.addAttribute("marks", marks);
//     model.addAttribute("grade", grade);
//     model.addAttribute("subject", selectedSubject);

//     // ✅ Use actual student info from currentStudent
//     model.addAttribute("studentName", currentStudent.getName());
//     model.addAttribute("rollNo", currentStudent.getRollNumber());

//     return "result";
// }

// @PostMapping("/submit-exam")
// public String submitExam(@RequestParam Map<String, String> allParams, Model model) {
//     List<String> userAnswers = new ArrayList<>();
//     for (int i = 0; i < currentQuestions.size(); i++) {
//         userAnswers.add(allParams.get("answers[" + i + "]"));
//     }

//     int totalQuestions = currentQuestions.size();  // total = 15
//     int totalMarks = totalQuestions;               // assuming 1 mark per question

//     int marks = examService.calculateMarks(userAnswers, currentQuestions);  // e.g., 12
//     double percentage = (marks * 100.0) / totalMarks;

//     model.addAttribute("marks", marks);
//     model.addAttribute("grade", examService.getGrade(marks));
//     model.addAttribute("subject", selectedSubject);
//     model.addAttribute("percentage", String.format("%.2f", percentage));

//     model.addAttribute("studentName", currentStudent.getName());
//     model.addAttribute("rollNo", currentStudent.getRollNumber());

//     return "result";
// }
