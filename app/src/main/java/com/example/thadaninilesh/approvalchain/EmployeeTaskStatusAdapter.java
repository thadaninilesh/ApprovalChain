package com.example.thadaninilesh.approvalchain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thadaninilesh on 15-02-2016.
 */
public class EmployeeTaskStatusAdapter extends ArrayAdapter {
    List list = new ArrayList();
    public EmployeeTaskStatusAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(EmployeeTaskStatusList object) {
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
        EmployeeTaskHolder employeeTaskHolder;

        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            employeeTaskHolder = new EmployeeTaskHolder();
            employeeTaskHolder.tx_taskName = (TextView)row.findViewById(R.id.tx_taskName);
            employeeTaskHolder.tx_taskDescription = (TextView)row.findViewById(R.id.tx_taskDescription);
            employeeTaskHolder.tx_taskAmount = (TextView)row.findViewById(R.id.tx_taskAmount);
            employeeTaskHolder.tx_taskStatus = (TextView)row.findViewById(R.id.tx_taskStatus);
            row.setTag(employeeTaskHolder);
        }
        else{
            employeeTaskHolder = (EmployeeTaskHolder)row.getTag();
        }

        EmployeeTaskStatusList employeeTaskStatusList = (EmployeeTaskStatusList)this.getItem(position);
        employeeTaskHolder.tx_taskName.setText("Task name: "+employeeTaskStatusList.getTaskName());
        employeeTaskHolder.tx_taskDescription.setText("Task Description: "+employeeTaskStatusList.getTaskDescription());
        employeeTaskHolder.tx_taskAmount.setText("Task Amount: "+employeeTaskStatusList.getTaskAmount());
        employeeTaskHolder.tx_taskStatus.setText("Task Status: "+employeeTaskStatusList.getTaskStatus());
        return row;
    }

    static class EmployeeTaskHolder{

        TextView tx_taskName, tx_taskDescription, tx_taskAmount, tx_taskStatus;

    }


}

