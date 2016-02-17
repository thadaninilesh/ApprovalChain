package com.example.thadaninilesh.approvalchain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText et_email,et_password;
    String email,password,method;
    SharedPreferences sharedPreferences;
    //UserSessionManager session;
    Button login, register, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //session = new UserSessionManager(getApplicationContext());
        et_email = (EditText)findViewById(R.id.email);
        et_password = (EditText)findViewById(R.id.password);
        /*logout = (Button)findViewById(R.id.logout);
        //Toast.makeText(getApplicationContext(),"User Login Status: "+session.isUserLoggedIn(),Toast.LENGTH_LONG).show();
        if(session.checkLogin()){
            finish();
            HashMap<String,String>user = session.getUserDetails();
            String email = user.get(UserSessionManager.KEY_EMAIL);
            Intent i = new Intent();
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //this.startActivity(new Intent(this, EmployeeActivity.class));
            this.startActivity(i);*/
        }



    public void empRegisterInitiate(View view){
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void empLogin(View view){
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        method = "login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, email, password);
        //AsyncTask<String, Void, String> r =
        finish();
    }
}
