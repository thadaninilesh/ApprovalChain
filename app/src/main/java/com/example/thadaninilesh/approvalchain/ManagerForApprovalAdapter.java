package com.example.thadaninilesh.approvalchain;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by thadaninilesh on 17-02-2016.
 */
public class ManagerForApprovalAdapter extends ArrayAdapter{
    SparseBooleanArray approvals;
    private Activity context;
    private int id;
    List list = new ArrayList();

    public ManagerForApprovalAdapter(Activity context, int resource) {
        super(context, resource);
    }


    public void add(ManagerForApprovalList object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ManagerForApprovalHolder managerForApprovalHolder = new ManagerForApprovalHolder();

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_manager_tobe_approved, parent, false);

            //managerGroupHolder = new ManagerGroupHolder();
            managerForApprovalHolder.employeeName = (TextView) row.findViewById(R.id.employeeName);
            managerForApprovalHolder.employeeEmail = (TextView) row.findViewById(R.id.employeeEmail);
            managerForApprovalHolder.taskName = (TextView) row.findViewById(R.id.taskName);
            managerForApprovalHolder.taskDescription = (TextView) row.findViewById(R.id.taskDescription);
            managerForApprovalHolder.taskAmount = (TextView) row.findViewById(R.id.taskAmount);
            managerForApprovalHolder.approval = (Button)row.findViewById(R.id.approval);
            managerForApprovalHolder.approval.setTag(position);
            //managerForApprovalHolder.cb_approvalSelect = (CheckBox)row.findViewById(R.id.approvalSelect);
            row.setTag(managerForApprovalHolder);
        } else {
            managerForApprovalHolder = (ManagerForApprovalHolder) row.getTag();
        }

        ManagerForApprovalList managerForApprovalList = (ManagerForApprovalList) this.getItem(position);
        managerForApprovalHolder.employeeName.setText("Name: "+managerForApprovalList.getEmployeeName());
        managerForApprovalHolder.employeeEmail.setText("Email: "+managerForApprovalList.getEmployeeEmail());
        managerForApprovalHolder.taskName.setText("Task: " + managerForApprovalList.getTaskName());
        managerForApprovalHolder.taskDescription.setText("Description: " + managerForApprovalList.getTaskDescription());
        managerForApprovalHolder.taskAmount.setText("Amount: " + managerForApprovalList.getTaskAmount());







        return row;
    }



    class ManagerForApprovalHolder {

        TextView employeeName, employeeEmail, taskName, taskDescription, taskAmount;
        CheckBox cb_approvalSelect;
        Button approval;

    }
}
