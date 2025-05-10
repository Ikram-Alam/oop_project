package com.portal.portal.model;

public class Result {
    private Student student;
    private int marks;
    private String grade;

    public Result() {}

    public Student getStudent() {
        return student;
    }

    public int getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
