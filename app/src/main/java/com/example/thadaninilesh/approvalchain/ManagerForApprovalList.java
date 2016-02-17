package com.example.thadaninilesh.approvalchain;

/**
 * Created by thadaninilesh on 17-02-2016.
 */
public class ManagerForApprovalList {
    private String employeeName, employeeEmail, taskName, taskDescription, taskAmount;
    private boolean approvalSelect = false;

    public ManagerForApprovalList(String employeeEmail, String employeeName, String taskAmount, String taskDescription, String taskName) {
        this.employeeEmail = employeeEmail;
        this.employeeName = employeeName;
        this.taskAmount = taskAmount;
        this.taskDescription = taskDescription;
        this.taskName = taskName;
        this.approvalSelect = approvalSelect;
    }

    public boolean isApprovalSelect() {

        return approvalSelect;
    }

    public void setApprovalSelect(boolean approvalSelect) {
        this.approvalSelect = approvalSelect;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
