package com.example.thadaninilesh.approvalchain;

/**
 * Created by thadaninilesh on 16-02-2016.
 */
public class EmployeeGroupStatusList {
    private String groupName, employeeName;

    public EmployeeGroupStatusList(String groupName, String employeeName){
        this.setGroupName(groupName);
        this.setEmployeeName(employeeName);

    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}

