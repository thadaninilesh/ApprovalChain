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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by thadaninilesh on 13-02-2016.
 */
public class FragmentEmployeeTask extends Fragment {
    String email;
    String jsonString;
    FragmentEmployee fragmentEmployee;
    TextView textView;
    String json;
    String taskName = "",taskDescription = "",taskAmount = "",taskStatus = "";
    EmployeeTaskStatusAdapter employeeTaskStatusAdapter;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView listView;


    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee_task, container, false);
        textView = (TextView)view.findViewById(R.id.textView);
        listView = (ListView)view.findViewById(R.id.employeeTaskStatusList);
        employeeTaskStatusAdapter = new EmployeeTaskStatusAdapter(this.getActivity(), R.layout.row_layout);
        listView.setAdapter(employeeTaskStatusAdapter);
        employeeTaskStatusAdapter.notifyDataSetChanged();
        jsonString = GlobalData.getTaskData();
        Log.i("stringTag","jsonString");
        //textView.setText(jsonString+" This is it");
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;


            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                if(JO.getString("task_name").isEmpty()){
                    startActivity(new Intent(this.getActivity(), RegisterActivity.class));
                }
                taskName = JO.getString("task_name");
                taskDescription = JO.getString("task_description");
                taskAmount = JO.getString("task_amount");
                taskStatus = JO.getString("approval_status");
                EmployeeTaskStatusList employeeTaskStatusList = new EmployeeTaskStatusList(taskName,taskDescription,taskAmount,taskStatus);
                employeeTaskStatusAdapter.add(employeeTaskStatusList);
                count++;
            }
            if(taskName.isEmpty() || taskDescription.isEmpty() || taskAmount.isEmpty() || taskStatus.isEmpty()){
                Fragment fragmentEmployeeTask = new FragmentEmployeeTask();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_employee, fragmentEmployeeTask).commit();
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
