package com.example.thadaninilesh.approvalchain;

/**
 * Created by thadaninilesh on 17-02-2016.
 */
public class ManagerApprovedApprovalsList {
    private String employeeName, employeeEmail, taskName, taskDescription, taskAmount, taskStatus;

    public ManagerApprovedApprovalsList(String employeeEmail, String employeeName, String taskAmount, String taskDescription, String taskStatus, String taskName) {
        this.setEmployeeEmail(employeeEmail);
        this.setEmployeeName(employeeName);
        this.setTaskAmount(taskAmount);
        this.setTaskDescription(taskDescription);
        this.setTaskStatus(taskStatus);
        this.setTaskName(taskName);
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(String taskAmount) {
        this.taskAmount = taskAmount;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
