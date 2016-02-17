package com.example.thadaninilesh.approvalchain;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentManagerForApproval extends Fragment implements android.widget.CompoundButton.OnCheckedChangeListener{
    String email;
    String jsonString;
    FragmentManage fragmentManage;
    String json;
    String employeeName = "", employeeEmail = "", taskAmount = "", taskName, taskDescription;
    ManagerForApprovalAdapter managerForApprovalAdapter;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView managerToBeApproved;
    Button test, approve, approval;
    FragmentManagerForApproval fragmentManagerForApproval;


    public FragmentManagerForApproval() {
        // Required empty public constructor
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_for_approval, container, false);
        managerToBeApproved = (ListView)view.findViewById(R.id.managerToBeApproved);
        jsonString = GlobalData.getManagerToBeApprovedData();


        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            managerForApprovalAdapter = new ManagerForApprovalAdapter(this.getActivity(), R.layout.row_layout_manager_tobe_approved);
            managerToBeApproved.setAdapter(managerForApprovalAdapter);
            managerForApprovalAdapter.notifyDataSetChanged();


            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                employeeName = JO.getString("employee_name");
                employeeEmail = JO.getString("employee_email");
                taskName = JO.getString("task_name");
                taskDescription = JO.getString("task_description");
                taskAmount = JO.getString("task_amount");

                ManagerForApprovalList managerForApprovalList = new ManagerForApprovalList(employeeEmail, employeeName, taskAmount, taskDescription, taskName);
                managerForApprovalAdapter.add(managerForApprovalList);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        super.onAttach(activity);
        try {
            fragmentManage = (FragmentManage)activity;
        }
        catch (Exception e){}

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = managerToBeApproved.getPositionForView(buttonView);

    }

    public interface FragmentManage{
        public void pendingApprovals(String email);
    }
}