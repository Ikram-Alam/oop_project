package com.examportal.model;

public class Student {
    private String name, email, contact, department, rollNo;

    public Student(String name, String email, String contact, String department, String rollNo) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.department = department;
        this.rollNo = rollNo;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getDepartment() { return department; }
    public String getRollNo() { return rollNo; }
}
