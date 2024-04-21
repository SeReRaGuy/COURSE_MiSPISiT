package com.example.course.Models;

public class Education {
    private int id;
    private int employee;
    private String degree;
    private String institution;
    private String specialization;
    private int yearOfGraduation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public Education(int id, int employee, String degree, String institution, String specialization, int yearOfGraduation) {
        this.id = id;
        this.employee = employee;
        this.degree = degree;
        this.institution = institution;
        this.specialization = specialization;
        this.yearOfGraduation = yearOfGraduation;
    }
}
