package com.portal.portal.service;

import com.portal.portal.model.Question;
import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class ExamService {
    public List<Question> getQuestions(String subject) {
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            questions.add(new Question(subject + " Question " + i, "answer" + i));
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
        if (marks >= 25) return "A";
        if (marks >= 20) return "B";
        if (marks >= 15) return "C";
        return "F";
    }
}
