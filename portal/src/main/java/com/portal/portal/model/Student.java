package com.portal.portal.model;

public class Student {
    private String name;
    private String email;
    private String contact;
    private String department;
    private String gender;
    private String rollNumber;

    public Student() {}

    public Student(String name, String email, String contact, String department, String gender, String rollNumber) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.department = department;
        this.gender = gender;
        this.rollNumber = rollNumber;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getDepartment() { return department; }
    public String getGender() { return gender; }
    public String getRollNumber() { return rollNumber; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setContact(String contact) { this.contact = contact; }
    public void setDepartment(String department) { this.department = department; }
    public void setGender(String gender) { this.gender = gender; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
}
