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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentBossApprovedApprovals extends Fragment {
    String email;
    String jsonString;
    FragmentBoss fragmentBoss;
    String json;
    String employeeName = "", employeeEmail = "", taskName = "", taskAmount, taskDescription, taskStatus;
    ManagerApprovedApprovalsAdapter managerApprovedApprovalsAdapter;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView bossApprovedApprovals;
    FragmentBossApprovedApprovals fragmentBossApprovedApprovals;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_approved_approvals, container, false);
        bossApprovedApprovals = (ListView)view.findViewById(R.id.managerApprovedApprovals);
        managerApprovedApprovalsAdapter = new ManagerApprovedApprovalsAdapter(this.getActivity(), R.layout.row_layout_manager_approved);
        bossApprovedApprovals.setAdapter(managerApprovedApprovalsAdapter);
        managerApprovedApprovalsAdapter.notifyDataSetChanged();
        jsonString = GlobalData.getManagerApprovedData();
        //test.setText(jsonString);


        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                employeeEmail = JO.getString("employeeEmail");
                employeeName = JO.getString("employeeName");
                taskAmount = JO.getString("taskAmount");
                taskDescription = JO.getString("taskDescription");
                taskStatus = JO.getString("taskStatus");
                taskName = JO.getString("taskName");

                ManagerApprovedApprovalsList managerApprovedApprovalsList = new ManagerApprovedApprovalsList(employeeEmail, employeeName, taskAmount, taskDescription, taskStatus, taskName);
                managerApprovedApprovalsAdapter.add(managerApprovedApprovalsList);
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
        try {
            fragmentBoss = (FragmentBoss)activity;
        }
        catch (Exception e){}

    }

    public interface FragmentBoss{
        public void viewApproved(String email);
    }


}
