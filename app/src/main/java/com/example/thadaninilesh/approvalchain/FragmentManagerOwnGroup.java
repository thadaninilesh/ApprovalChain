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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentManagerOwnGroup extends Fragment {
    String email;
    String jsonString;
    FragmentManager fragmentManager;
    String json;
    String employeeName = "", employeeEmail = "", employeePhone = "";
    ManagerOwnGroupAdapter managerOwnGroupAdapter;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ListView managerOwnGroupList;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manager_own_group, container, false);
        managerOwnGroupList = (ListView)view.findViewById(R.id.managerOwnGroupList);
        managerOwnGroupAdapter = new ManagerOwnGroupAdapter(this.getActivity(), R.layout.row_layout_manager_own_group);
        managerOwnGroupList.setAdapter(managerOwnGroupAdapter);
        managerOwnGroupAdapter.notifyDataSetChanged();
        jsonString = GlobalData.getManagerGroupData();

        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;


            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                employeeName = JO.getString("employeeName");
                employeeEmail = JO.getString("employeeEmail");
                employeePhone = JO.getString("employeePhone");

                ManagerGroupList managerGroupList = new ManagerGroupList(employeeName, employeeEmail, employeePhone);
                managerOwnGroupAdapter.add(managerGroupList);
                count++;
            }
            if(employeeName.isEmpty() || employeeEmail.isEmpty() || employeePhone.isEmpty()){
                Fragment fragmentEditDetails = new FragmentEmployeeEditDetails();
                android.app.FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame_employee, fragmentEditDetails).commit();
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
        super.onAttach(activity);
        try {
            fragmentManager = (FragmentManager)activity;
        }
        catch (Exception e){}

    }

    public interface FragmentManager{
        public void viewList(String email);
    }


}
