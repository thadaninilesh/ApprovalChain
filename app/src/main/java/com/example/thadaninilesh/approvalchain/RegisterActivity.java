package com.example.thadaninilesh.approvalchain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    EditText et_name, et_email, et_password, et_phone;
    String name, email, password, phone;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText)findViewById(R.id.name);
        et_email = (EditText)findViewById(R.id.email);
        et_password = (EditText)findViewById(R.id.password);
        et_phone = (EditText)findViewById(R.id.phone);
        text = (TextView)findViewById(R.id.text);
    }

    public void empLoginInitiate(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void empRegister(View view){
        //startActivity(new Intent(this, RegisterActivity.class));
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();
        phone = et_phone.getText().toString();
        String flag = "0";
        String method = "register";
        //String to = name+" "+email+" "+password+" "+method+" "+" "+phone+" "+flag;
        BackgroundTask backgroundTask = new BackgroundTask(this);
        //Toast.makeText(this,to, Toast.LENGTH_LONG).show();
        backgroundTask.execute(method, name, email, password, phone, flag);
    }

    public void sidebar(View view){
        startActivity(new Intent(this,EmployeeActivity.class));
    }
}
