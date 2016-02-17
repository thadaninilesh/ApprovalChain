package com.example.thadaninilesh.approvalchain;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class FragmentEmployeeGroup extends Fragment {
    String email;
    String jsonString;
    FragmentEmployee fragmentEmployee;
    TextView textView;
    String json;
    EmployeeGroupStatusAdapter employeeGroupStatusAdapter;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView listView;
    String groupName = "", employeeName = "";
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_group, container, false);
        //textView = (TextView)view.findViewById(R.id.textView);
        listView = (ListView)view.findViewById(R.id.employeeGroupStatusList);
        employeeGroupStatusAdapter = new EmployeeGroupStatusAdapter(this.getActivity(), R.layout.row_layout);
        listView.setAdapter(employeeGroupStatusAdapter);
        employeeGroupStatusAdapter.notifyDataSetChanged();
        jsonString = GlobalData.getGroupData();
        //textView.setText(jsonString+" This is it");
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;


            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                groupName = JO.getString("group_name");
                employeeName = JO.getString("name");
                EmployeeGroupStatusList employeeGroupStatusList = new EmployeeGroupStatusList(groupName, employeeName);
                employeeGroupStatusAdapter.add(employeeGroupStatusList);
                count++;
            }
            if(groupName.isEmpty() || employeeName.isEmpty()){
                Fragment fragmentEmployeeGroup = new FragmentEmployeeGroup();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_employee, fragmentEmployeeGroup).commit();
                //globalData.setBoolean(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentEmployee = (FragmentEmployee)activity;
        }
        catch (Exception e){}
    }

    public interface FragmentEmployee{
        public void viewList(String email);
    }




}
