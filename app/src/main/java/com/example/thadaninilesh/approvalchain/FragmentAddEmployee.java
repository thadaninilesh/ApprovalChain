package com.example.thadaninilesh.approvalchain;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentAddEmployee extends Fragment {
    EditText et_name, et_email, et_password, et_phone;
    String name, email, password, phone;
    Button empRegister;
    FragmentBoss fragmentBoss;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        FragmentManager fm = getFragmentManager();
        et_name = (EditText)view.findViewById(R.id.name);
        et_email = (EditText)view.findViewById(R.id.email);
        et_password = (EditText)view.findViewById(R.id.password);
        et_phone = (EditText)view.findViewById(R.id.phone);
        empRegister = (Button)view.findViewById(R.id.empRegister);
        empRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                name = et_name.getText().toString();
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                phone = et_phone.getText().toString();
                if(password.isEmpty()){
                    password = phone;
                }
                fragmentBoss.addEmployee("addemployee",name, email, password, phone);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentBoss = (FragmentBoss)activity;
        }
        catch (Exception e){}
    }
    public interface FragmentBoss{
        public void addEmployee(String method, String name, String email, String password, String phone);
    }
}
