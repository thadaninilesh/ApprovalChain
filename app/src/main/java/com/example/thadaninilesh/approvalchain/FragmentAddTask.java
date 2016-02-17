package com.example.thadaninilesh.approvalchain;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thadaninilesh.approvalchain.R;

/**
 * Created by thadaninilesh on 13-02-2016.
 */
public class FragmentAddTask extends Fragment {

    EditText et_name, et_description, et_amount;
    String name, description, amount;
    Button saveTask;
    FragmentEmployee fragmentEmployee;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addtask, container, false);
        FragmentManager fm = getFragmentManager();
        et_name = (EditText)view.findViewById(R.id.task_name);
        et_description = (EditText)view.findViewById(R.id.task_description);
        et_amount = (EditText)view.findViewById(R.id.task_amount);
        saveTask = (Button)view.findViewById(R.id.add_task);
        saveTask.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                name = et_name.getText().toString();
                description = et_description.getText().toString();
                amount = et_amount.getText().toString();
                fragmentEmployee.addTask(name, description, amount);
            }
        });
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
        public void addTask(String name,String description, String amount);
    }

}
