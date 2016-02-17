package com.example.thadaninilesh.approvalchain;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thadaninilesh on 17-02-2016.
 */
public class ManagerApprovedApprovalsAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ManagerApprovedApprovalsAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(ManagerApprovedApprovalsList object) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        ManagerApprovedHolder managerGroupHolder = new ManagerApprovedHolder();

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_manager_approved, parent, false);
            managerGroupHolder = new ManagerApprovedHolder();
            managerGroupHolder.tx_employeeName = (TextView) row.findViewById(R.id.tx_employeeName);
            managerGroupHolder.tx_employeeEmail = (TextView) row.findViewById(R.id.tx_employeeEmail);
            managerGroupHolder.tx_taskName = (TextView) row.findViewById(R.id.tx_taskName);
            managerGroupHolder.tx_taskDescription = (TextView) row.findViewById(R.id.tx_taskDescription);
            managerGroupHolder.tx_taskAmount = (TextView) row.findViewById(R.id.tx_taskAmount);
            managerGroupHolder.tx_taskStatus = (TextView) row.findViewById(R.id.tx_taskStatus);
            row.setTag(managerGroupHolder);
        } else {
            managerGroupHolder = (ManagerApprovedHolder) row.getTag();
        }

        ManagerApprovedApprovalsList managerApprovedApprovalsList = (ManagerApprovedApprovalsList) this.getItem(position);
        managerGroupHolder.tx_employeeName.setText("Name: "+managerApprovedApprovalsList.getEmployeeName());
        managerGroupHolder.tx_employeeEmail.setText("Email: "+managerApprovedApprovalsList.getEmployeeEmail());
        managerGroupHolder.tx_taskName.setText("Task: "+managerApprovedApprovalsList.getTaskName());
        managerGroupHolder.tx_taskDescription.setText("Description: "+managerApprovedApprovalsList.getTaskDescription());
        managerGroupHolder.tx_taskAmount.setText("Task amount: "+managerApprovedApprovalsList.getTaskAmount());
        managerGroupHolder.tx_taskAmount.setText("Approval Status: "+managerApprovedApprovalsList.getTaskStatus());

        return row;
    }



    static class ManagerApprovedHolder {

        TextView tx_employeeName, tx_employeeEmail, tx_taskName, tx_taskDescription, tx_taskAmount, tx_taskStatus;

    }

}
