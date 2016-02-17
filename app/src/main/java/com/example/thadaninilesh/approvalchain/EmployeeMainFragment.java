package com.example.thadaninilesh.approvalchain;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by thadaninilesh on 13-02-2016.
 */
public class EmployeeMainFragment extends Fragment {
    EditText et_email,et_password;
    String email,password,method;
    Button empLogin;
    FragmentEmployee fragmentLogin;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_employee,container,false);
        FragmentManager fm = getFragmentManager();
        et_email = (EditText)view.findViewById(R.id.email);
        et_password = (EditText)view.findViewById(R.id.password);

        empLogin = (Button)view.findViewById(R.id.empLogin);
        empLogin.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
               email = et_email.getText().toString();
               password = et_password.getText().toString();
                fragmentLogin.login(email,password);
           }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            fragmentLogin = (FragmentEmployee)activity;
        }
        catch (Exception e){}
    }

    public interface FragmentEmployee{
        public void login(String email,String password);
    }




}
