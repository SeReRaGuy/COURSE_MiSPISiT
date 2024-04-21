package com.example.course.Models;

import java.util.Date;

public class Research {
    private int id;
    private int department;
    private String projectName;
    private int leadEmployee;
    private Date startDate;
    private Date endDate;
    private int fundingAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getLeadEmployee() {
        return leadEmployee;
    }

    public void setLeadEmployee(int leadEmployee) {
        this.leadEmployee = leadEmployee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(int fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public Research(int id, int department, String projectName, int leadEmployee, Date startDate, Date endDate, int fundingAmount) {
        this.id = id;
        this.department = department;
        this.projectName = projectName;
        this.leadEmployee = leadEmployee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fundingAmount = fundingAmount;
    }
}
