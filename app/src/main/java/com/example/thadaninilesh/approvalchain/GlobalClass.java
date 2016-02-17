package com.example.thadaninilesh.approvalchain;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
/**
 * Created by thadaninilesh on 14-02-2016.
 */
public class GlobalClass{
    private SharedPreferences prefs;



    public GlobalClass(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setEmail(String email) {
        prefs.edit().putString("Email", email).commit();
    }

    public String getEmail() {
        String email = prefs.getString("Email","");
        return email;
    }
}
