package com.example.thadaninilesh.approvalchain;

/**
 * Created by thadaninilesh on 15-02-2016.
 */
public class EmployeeTaskStatusList {
    private String taskName, taskDescription, taskAmount, taskStatus;

    public EmployeeTaskStatusList(String taskName, String taskDescription, String taskAmount, String taskStatus){
        this.setTaskName(taskName);
        this.setTaskDescription(taskDescription);
        this.setTaskAmount(taskAmount);
        this.setTaskStatus(taskStatus);
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskAmount() {
        return taskAmount;
    }

    public void setTaskAmount(String taskAmount) {
        this.taskAmount = taskAmount;
    }

    public String getTaskStatus(){
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus){
        this.taskStatus = taskStatus;
    }
}
