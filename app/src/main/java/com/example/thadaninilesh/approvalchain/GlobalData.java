package com.example.thadaninilesh.approvalchain;

/**
 * Created by thadaninilesh on 14-02-2016.
 */
public class GlobalData {
    public static String groupData = new String();
    public static String taskData = new String();
    public static String editData = new String();
    public static String managerGroupData = new String();
    public static String managerApprovedData = new String();

    public static String getManagerApprovedData() {
        return managerApprovedData;
    }

    public static void setManagerApprovedData(String managerApprovedData) {
        GlobalData.managerApprovedData = managerApprovedData;
    }

    public static String getEditData() {
        return editData;
    }

    public static void setEditData(String editData) {
        GlobalData.editData = editData;
    }

    public static String getManagerGroupData() {
        return managerGroupData;
    }

    public static void setManagerGroupData(String managerGroupData) {
        GlobalData.managerGroupData = managerGroupData;
    }

    public String getIPData(){
        return "192.168.0.2";
    }

    public static String getTaskData() { return taskData; }

    public void setTaskData(String data) {
        this.taskData = data;
    }

    public static String getGroupData() { return groupData; }

    public void setGroupData(String data) {
        this.groupData = data;
    }

    public void setEditDetails(String data) { this.editData = data; }

    public String getEditDetails(){ return editData; }

}
