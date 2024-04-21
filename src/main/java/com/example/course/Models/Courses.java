package com.example.course.Models;

public class Courses {
    private int id;
    private String courseName;
    private int department;
    private int instructor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getInstructor() {
        return instructor;
    }

    public void setInstructor(int instructor) {
        this.instructor = instructor;
    }

    public Courses(int id, String courseName, int department, int instructor) {
        this.id = id;
        this.courseName = courseName;
        this.department = department;
        this.instructor = instructor;
    }
}
