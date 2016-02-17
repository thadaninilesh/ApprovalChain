package com.example.thadaninilesh.approvalchain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivitySplash extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private static int SPLASH_TIME_OUT = 1000;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        textView = (TextView)findViewById(R.id.tt);
        /*======================================================================*/
        sharedPreferences = getSharedPreferences("ApprovalChain", Context.MODE_PRIVATE);
        final String designation = sharedPreferences.getString("Designation", "");
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                //textView.setText(email);
                if(designation.isEmpty()){
                    startActivity(new Intent(MainActivitySplash.this,LoginActivity.class));
                }
                else if(designation.equals("0")){
                    startActivity(new Intent(MainActivitySplash.this,EmployeeActivity.class));
                }
                else if(designation.equals("1")){
                    startActivity(new Intent(MainActivitySplash.this,ManagerActivity.class));
                }
                else if(designation.equals("2")){
                    startActivity(new Intent(MainActivitySplash.this,BossActivity.class));
                }
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
