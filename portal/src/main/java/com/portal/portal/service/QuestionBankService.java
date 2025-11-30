package com.portal.portal.service;

import com.portal.portal.dsa.CustomHashMap;
import com.portal.portal.dsa.CustomLinkedList;
import com.portal.portal.model.Question;
import org.springframework.stereotype.Service;

/**
 * Question Bank Service using Custom HashMap
 * DSA Concept: Hash Map with Chaining for O(1) average access time
 */
@Service
public class QuestionBankService {
    
    // Using custom HashMap to store questions by subject
    private CustomHashMap<String, CustomLinkedList<Question>> questionBank;
    
    public QuestionBankService() {
        this.questionBank = new CustomHashMap<>();
        initializeQuestionBank();
    }
    
    /**
     * Initialize question bank with subjects and questions
     * Demonstrates: HashMap insertion, LinkedList usage
     */
    private void initializeQuestionBank() {
        // Math questions
        CustomLinkedList<Question> mathQuestions = new CustomLinkedList<>();
        mathQuestions.add(new Question("2 + 2 = ?", "3", "4", "5", "6", "4"));
        mathQuestions.add(new Question("5 * 3 = ?", "8", "10", "15", "20", "15"));
        mathQuestions.add(new Question("10 - 4 = ?", "6", "7", "5", "4", "6"));
        mathQuestions.add(new Question("Square root of 16?", "2", "4", "6", "8", "4"));
        mathQuestions.add(new Question("12 / 3 = ?", "2", "4", "5", "6", "4"));
        mathQuestions.add(new Question("Area of square with side 4?", "12", "16", "20", "24", "16"));
        mathQuestions.add(new Question("7 + 6 = ?", "12", "13", "14", "15", "13"));
        mathQuestions.add(new Question("9 * 2 = ?", "16", "18", "20", "22", "18"));
        mathQuestions.add(new Question("100 - 45 = ?", "55", "50", "60", "65", "55"));
        mathQuestions.add(new Question("6^2 = ?", "36", "30", "42", "48", "36"));
        mathQuestions.add(new Question("3 + 3 * 2 = ?", "12", "9", "6", "8", "9"));
        mathQuestions.add(new Question("15 % 4 = ?", "3", "1", "2", "4", "3"));
        mathQuestions.add(new Question("LCM of 3 and 5?", "15", "30", "10", "20", "15"));
        mathQuestions.add(new Question("Factorial of 4?", "24", "20", "16", "12", "24"));
        mathQuestions.add(new Question("Which is a prime number?", "6", "9", "11", "15", "11"));
        
        // Science questions
        CustomLinkedList<Question> scienceQuestions = new CustomLinkedList<>();
        scienceQuestions.add(new Question("Water formula?", "H2O", "CO2", "O2", "NaCl", "H2O"));
        scienceQuestions.add(new Question("Sun is a ...", "Planet", "Star", "Comet", "Gas", "Star"));
        scienceQuestions.add(new Question("Human blood color?", "Blue", "Green", "Red", "Yellow", "Red"));
        scienceQuestions.add(new Question("Gas used for breathing?", "CO2", "H2", "O2", "N2", "O2"));
        scienceQuestions.add(new Question("Boiling point of water?", "90°C", "100°C", "110°C", "80°C", "100°C"));
        scienceQuestions.add(new Question("Earth is a ...", "Star", "Satellite", "Planet", "Asteroid", "Planet"));
        scienceQuestions.add(new Question("Photosynthesis needs?", "Light", "Sound", "Heat", "Wind", "Light"));
        scienceQuestions.add(new Question("Animal that lays eggs?", "Cow", "Tiger", "Hen", "Dog", "Hen"));
        scienceQuestions.add(new Question("Smallest unit of life?", "Organ", "Cell", "Tissue", "Nucleus", "Cell"));
        scienceQuestions.add(new Question("Organ to pump blood?", "Liver", "Brain", "Heart", "Lungs", "Heart"));
        scienceQuestions.add(new Question("Newton's 3rd law relates to?", "Force", "Motion", "Action & Reaction", "Friction", "Action & Reaction"));
        scienceQuestions.add(new Question("Acid found in lemon?", "Citric", "Hydrochloric", "Sulfuric", "Lactic", "Citric"));
        scienceQuestions.add(new Question("Sound travels fastest in?", "Air", "Water", "Vacuum", "Steel", "Steel"));
        scienceQuestions.add(new Question("Which is not a metal?", "Gold", "Iron", "Copper", "Plastic", "Plastic"));
        scienceQuestions.add(new Question("Which planet has rings?", "Earth", "Mars", "Saturn", "Venus", "Saturn"));
        
        // Computer Science questions
        CustomLinkedList<Question> csQuestions = new CustomLinkedList<>();
        csQuestions.add(new Question("Full form of CPU?", "Central Processing Unit", "Central Program Unit", "Computer Personal Unit", "Central Processor Unit", "Central Processing Unit"));
        csQuestions.add(new Question("Binary of 5?", "100", "101", "110", "111", "101"));
        csQuestions.add(new Question("RAM stands for?", "Read Access Memory", "Random Access Memory", "Run Access Memory", "Rapid Access Memory", "Random Access Memory"));
        csQuestions.add(new Question("Smallest unit of data?", "Byte", "Bit", "Nibble", "KB", "Bit"));
        csQuestions.add(new Question("1 KB equals?", "1000 bytes", "1024 bytes", "100 bytes", "512 bytes", "1024 bytes"));
        csQuestions.add(new Question("HTML is a?", "Programming Language", "Markup Language", "Scripting Language", "Database", "Markup Language"));
        csQuestions.add(new Question("Which is an OS?", "Word", "Excel", "Linux", "Chrome", "Linux"));
        csQuestions.add(new Question("WWW stands for?", "World Wide Web", "World Web Wide", "Wide World Web", "Web World Wide", "World Wide Web"));
        csQuestions.add(new Question("Email protocol?", "FTP", "HTTP", "SMTP", "TCP", "SMTP"));
        csQuestions.add(new Question("Default port for HTTP?", "21", "80", "443", "8080", "80"));
        csQuestions.add(new Question("Java is?", "Compiled", "Interpreted", "Both", "None", "Both"));
        csQuestions.add(new Question("OOP stands for?", "Object Oriented Programming", "Object Operated Programming", "Oriented Object Programming", "Operated Object Programming", "Object Oriented Programming"));
        csQuestions.add(new Question("Which is not a loop?", "for", "while", "do-while", "if", "if"));
        csQuestions.add(new Question("Database query language?", "SQL", "HTML", "CSS", "XML", "SQL"));
        csQuestions.add(new Question("Time complexity of Binary Search?", "O(n)", "O(log n)", "O(n^2)", "O(1)", "O(log n)"));
        
        // Store in HashMap
        questionBank.put("Math", mathQuestions);
        questionBank.put("Science", scienceQuestions);
        questionBank.put("Computer Science", csQuestions);
    }
    
    /**
     * Get questions by subject
     * Time Complexity: O(1) average due to HashMap
     */
    public CustomLinkedList<Question> getQuestionsBySubject(String subject) {
        return questionBank.get(subject);
    }
    
    /**
     * Add new question to a subject
     * Time Complexity: O(1) for HashMap access + O(1) for LinkedList add
     */
    public void addQuestion(String subject, Question question) {
        CustomLinkedList<Question> questions = questionBank.get(subject);
        if (questions == null) {
            questions = new CustomLinkedList<>();
            questionBank.put(subject, questions);
        }
        questions.add(question);
    }
    
    /**
     * Get all available subjects
     */
    public CustomLinkedList<String> getAllSubjects() {
        return questionBank.keySet();
    }
    
    /**
     * Get total number of questions for a subject
     */
    public int getQuestionCount(String subject) {
        CustomLinkedList<Question> questions = questionBank.get(subject);
        return questions != null ? questions.size() : 0;
    }
}
