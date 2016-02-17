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
public class ManagerOwnGroupAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public ManagerOwnGroupAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(ManagerGroupList object) {
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
        ManagerGroupHolder managerGroupHolder = new ManagerGroupHolder();

        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout_manager_own_group, parent, false);
            managerGroupHolder = new ManagerGroupHolder();
            managerGroupHolder.tx_employeeName = (TextView) row.findViewById(R.id.tx_employeeName);
            managerGroupHolder.tx_employeeEmail = (TextView) row.findViewById(R.id.tx_employeeEmail);
            managerGroupHolder.tx_employeePhone = (TextView) row.findViewById(R.id.tx_employeePhone);
            row.setTag(managerGroupHolder);
        } else {
            managerGroupHolder = (ManagerGroupHolder) row.getTag();
        }

        ManagerGroupList managerGroupList = (ManagerGroupList) this.getItem(position);
        managerGroupHolder.tx_employeeName.setText("Name: "+managerGroupList.getEmployeeName());
        managerGroupHolder.tx_employeeEmail.setText("Email: "+managerGroupList.getEmployeeEmail());
        managerGroupHolder.tx_employeePhone.setText("Phone: "+managerGroupList.getEmployeePhone());
        if(managerGroupHolder.tx_employeeName.getText().toString().isEmpty()){
            ManagerActivity managerActivity = new ManagerActivity();
            MenuItem item = drawer.getMenu().findItem(R.id.action_something);
            item.setCheckable(true);
            item.setChecked(true);
            managerActivity.onNavigationItemSelected(MenuIte);
            //globalData.setBoolean(false);
        }
        return row;
    }



    static class ManagerGroupHolder {

        TextView tx_employeeName, tx_employeeEmail, tx_employeePhone;

    }

}
