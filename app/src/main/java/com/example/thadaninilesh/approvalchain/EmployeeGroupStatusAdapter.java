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
 * Created by thadaninilesh on 16-02-2016.
 */
public class EmployeeGroupStatusAdapter extends ArrayAdapter{
    List list = new ArrayList();

    public EmployeeGroupStatusAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(EmployeeGroupStatusList object) {
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
        EmployeeGroupHolder employeeGroupHolder;

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_employee_group, parent, false);
            employeeGroupHolder = new EmployeeGroupHolder();
            employeeGroupHolder.tx_groupName = (TextView) row.findViewById(R.id.tx_groupName);
            employeeGroupHolder.tx_employeeName = (TextView) row.findViewById(R.id.tx_employeeName);

            row.setTag(employeeGroupHolder);
        } else {
            employeeGroupHolder = (EmployeeGroupHolder) row.getTag();
        }

        EmployeeGroupStatusList employeeGroupStatusList = (EmployeeGroupStatusList) this.getItem(position);
        employeeGroupHolder.tx_groupName.setText("Group name: " + employeeGroupStatusList.getGroupName());
        employeeGroupHolder.tx_employeeName.setText("Employee name: " + employeeGroupStatusList.getEmployeeName());
        return row;
    }

    static class EmployeeGroupHolder {

        TextView tx_employeeName, tx_groupName;

    }

}
