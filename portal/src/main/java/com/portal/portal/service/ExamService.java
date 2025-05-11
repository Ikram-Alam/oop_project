package com.portal.portal.service;

import com.portal.portal.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamService {
    public List<Question> getQuestions(String subject) {
        List<Question> questions = new ArrayList<>();

        if (subject.equalsIgnoreCase("Math")) {
            questions.add(new Question("2 + 2 = ?", "3", "4", "5", "6", "4"));
            questions.add(new Question("5 * 3 = ?", "8", "10", "15", "20", "15"));
            questions.add(new Question("10 - 4 = ?", "6", "7", "5", "4", "6"));
        } else if (subject.equalsIgnoreCase("Science")) {
            questions.add(new Question("Water formula?", "H2O", "CO2", "O2", "NaCl", "H2O"));
            questions.add(new Question("Sun is a ...", "Planet", "Star", "Comet", "Gas", "Star"));
            questions.add(new Question("Human blood color?", "Blue", "Green", "Red", "Yellow", "Red"));
        }

        return questions;
    }

    public int calculateMarks(List<String> userAnswers, List<Question> questions) {
        int marks = 0;
        for (int i = 0; i < userAnswers.size(); i++) {
            if (questions.get(i).getAnswer().equalsIgnoreCase(userAnswers.get(i))) {
                marks += 5;
            }
        }
        return marks;
    }

    public String getGrade(int marks) {
        if (marks >= 15) return "A";
        if (marks >= 10) return "B";
        if (marks >= 5) return "C";
        return "F";
    }
}
