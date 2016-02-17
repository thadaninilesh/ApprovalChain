package com.example.thadaninilesh.approvalchain;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.JetPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEmployeeEditDetails extends Fragment {
    TextView tx_email, tx_groupName, tx_designation;
    EditText et_name, et_phone;
    String jsonString;
    Button editDetails;
    JSONObject jsonObject;
    JSONArray jsonArray;
    FragmentEmployee fragmentEmployee;
    private String email;
    public String rank;


    public FragmentEmployeeEditDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_edit_details, container, false);

        et_name = (EditText)view.findViewById(R.id.et_name);
        et_phone = (EditText)view.findViewById(R.id.et_phone);
        tx_email = (TextView)view.findViewById(R.id.tx_email);
        tx_groupName = (TextView)view.findViewById(R.id.tx_groupName);
        tx_designation = (TextView)view.findViewById(R.id.tx_designation);
        editDetails = (Button)view.findViewById(R.id.editDetails);
        GlobalData globalData = new GlobalData();
        jsonString = globalData.getEditDetails();
        String name = "", groupName, phone = "", designation = "";
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                name = JO.getString("name").toString();
                email = JO.getString("email").toString();
                phone = JO.getString("phone").toString();
                designation = JO.getString("designation").toString();
                groupName = JO.getString("group_name").toString();
                et_name.setText(name);
                et_phone.setText(phone);
                tx_designation.append(designation);
                tx_groupName.append(groupName);
                tx_email.append(email);
                count++;
            }
            final String rank = designation;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //final String rank = designation;
        rank = designation;

        editDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String phone = et_phone.getText().toString();
                String name = et_name.getText().toString();
                fragmentEmployee.editDetails(email,"edit", name, phone);


            }
        });

        if(et_name.getText().toString().isEmpty() || et_phone.getText().toString().isEmpty()){
            Fragment fragmentEditDetails = new FragmentEmployeeEditDetails();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame_employee, fragmentEditDetails).commit();
            //globalData.setBoolean(false);
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
        public void editDetails(String email, String method, String name,String phone);
    }
}
