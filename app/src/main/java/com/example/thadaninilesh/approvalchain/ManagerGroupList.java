package com.example.thadaninilesh.approvalchain;

/**
 * Created by thadaninilesh on 17-02-2016.
 */
public class ManagerGroupList {
    private String employeeName, employeeEmail, employeePhone;

    public ManagerGroupList(String employeeName, String employeeEmail, String employeePhone) {
        this.setEmployeeName(employeeName);
        this.setEmployeeEmail(employeeEmail);
        this.setEmployeePhone(employeePhone);
    }

    public String getEmployeeEmail() { return employeeEmail; }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }
}
