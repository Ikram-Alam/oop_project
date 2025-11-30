package com.portal.portal.service;

import com.portal.portal.dsa.CustomLinkedList;
import com.portal.portal.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Exam Service - Now uses QuestionBankService with DSA HashMap implementation
 */
@Service
public class ExamService {
    
    @Autowired
    private QuestionBankService questionBankService;
    
    public List<Question> getQuestions(String subject) {
        // Use custom HashMap-based question bank
        CustomLinkedList<Question> customList = questionBankService.getQuestionsBySubject(subject);
        
        if (customList == null) {
            return new ArrayList<>();
        }
        
        // Convert CustomLinkedList to ArrayList for compatibility
        List<Question> questions = new ArrayList<>();
        Object[] questionArray = customList.toArray();
        for (Object obj : questionArray) {
            questions.add((Question) obj);
        }
        
        return questions;
    }
    
    // Legacy method - kept for backward compatibility
    public List<Question> getQuestionsLegacy(String subject) {
        List<Question> questions = new ArrayList<>();

        if (subject.equalsIgnoreCase("Math")) {
            questions.add(new Question("2 + 2 = ?", "3", "4", "5", "6", "4"));
            questions.add(new Question("5 * 3 = ?", "8", "10", "15", "20", "15"));
            questions.add(new Question("10 - 4 = ?", "6", "7", "5", "4", "6"));
            questions.add(new Question("Square root of 16?", "2", "4", "6", "8", "4"));
            questions.add(new Question("12 / 3 = ?", "2", "4", "5", "6", "4"));
            questions.add(new Question("Area of square with side 4?", "12", "16", "20", "24", "16"));
            questions.add(new Question("7 + 6 = ?", "12", "13", "14", "15", "13"));
            questions.add(new Question("9 * 2 = ?", "16", "18", "20", "22", "18"));
            questions.add(new Question("100 - 45 = ?", "55", "50", "60", "65", "55"));
            questions.add(new Question("6^2 = ?", "36", "30", "42", "48", "36"));
            questions.add(new Question("3 + 3 * 2 = ?", "12", "9", "6", "8", "9")); // BODMAS
            questions.add(new Question("15 % 4 = ?", "3", "1", "2", "4", "3"));
            questions.add(new Question("LCM of 3 and 5?", "15", "30", "10", "20", "15"));
            questions.add(new Question("Factorial of 4?", "24", "20", "16", "12", "24"));
            questions.add(new Question("Which is a prime number?", "6", "9", "11", "15", "11"));
        } 
        
        else if (subject.equalsIgnoreCase("Science")) {
            questions.add(new Question("Water formula?", "H2O", "CO2", "O2", "NaCl", "H2O"));
            questions.add(new Question("Sun is a ...", "Planet", "Star", "Comet", "Gas", "Star"));
            questions.add(new Question("Human blood color?", "Blue", "Green", "Red", "Yellow", "Red"));
            questions.add(new Question("Gas used for breathing?", "CO2", "H2", "O2", "N2", "O2"));
            questions.add(new Question("Boiling point of water?", "90°C", "100°C", "110°C", "80°C", "100°C"));
            questions.add(new Question("Earth is a ...", "Star", "Satellite", "Planet", "Asteroid", "Planet"));
            questions.add(new Question("Photosynthesis needs?", "Light", "Sound", "Heat", "Wind", "Light"));
            questions.add(new Question("Animal that lays eggs?", "Cow", "Tiger", "Hen", "Dog", "Hen"));
            questions.add(new Question("Smallest unit of life?", "Organ", "Cell", "Tissue", "Nucleus", "Cell"));
            questions.add(new Question("Organ to pump blood?", "Liver", "Brain", "Heart", "Lungs", "Heart"));
            questions.add(new Question("Newton's 3rd law relates to?", "Force", "Motion", "Action & Reaction", "Friction", "Action & Reaction"));
            questions.add(new Question("Acid found in lemon?", "Citric", "Hydrochloric", "Sulfuric", "Lactic", "Citric"));
            questions.add(new Question("Sound travels fastest in?", "Air", "Water", "Vacuum", "Steel", "Steel"));
            questions.add(new Question("Which is not a metal?", "Gold", "Iron", "Copper", "Plastic", "Plastic"));
            questions.add(new Question("Which planet has rings?", "Earth", "Mars", "Saturn", "Venus", "Saturn"));
        }

        return questions;
    }

    // public int calculateMarks(List<String> userAnswers, List<Question> questions) {
    //     int marks = 0;
    //     for (int i = 0; i < userAnswers.size(); i++) {
    //         if (questions.get(i).getAnswer().equalsIgnoreCase(userAnswers.get(i))) {
    //             marks += 5;
    //         }
    //     }
    //     return marks;
    // }
    public int calculateMarks(List<String> userAnswers, List<Question> questions) {
    int marks = 0;
    for (int i = 0; i < userAnswers.size(); i++) {
        if (questions.get(i).getAnswer().equalsIgnoreCase(userAnswers.get(i))) {
            marks += 1;  
        }
    }
    return marks;
}

    public double calculatePercentage(int marks, int totalQuestions) {
    int totalMarks = totalQuestions * 1; // since each question = 1 marks
    return (marks * 100.0) / totalMarks;
}

    public String getGrade(int marks) {
        if (marks >= 15) return "A";
        if (marks >= 10) return "B";
        if (marks >= 5) return "C";
        return "F";
    }
}
