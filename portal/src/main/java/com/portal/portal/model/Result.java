package com.portal.portal.model;

public class Result {
    private Student student;
    private String subject;
    private int marks;
    private String grade;
    private double percentage;
    private long timestamp;

    public Result() {
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Student student, String subject, int marks, String grade, double percentage) {
        this.student = student;
        this.subject = subject;
        this.marks = marks;
        this.grade = grade;
        this.percentage = percentage;
        this.timestamp = System.currentTimeMillis();
    }

    public Student getStudent() {
        return student;
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public double getPercentage() {
        return percentage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Result{" +
                "student=" + student.getName() +
                ", subject='" + subject + '\'' +
                ", marks=" + marks +
                ", grade='" + grade + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
