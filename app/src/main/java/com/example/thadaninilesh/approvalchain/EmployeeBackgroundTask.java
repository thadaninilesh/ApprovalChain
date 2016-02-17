package com.example.thadaninilesh.approvalchain;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by thadaninilesh on 14-02-2016.
 */
public class EmployeeBackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    SharedPreferences sharedPreferences;
    EmployeeBackgroundTask(Context ctx){
        this.ctx = ctx;
    }
    GlobalData globalData = new GlobalData();
    String ip = globalData.getIPData();//ctx.getResources().getString(R.string.ip_address);


    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("addtask")) {
            String addtask_address = "http://"+ip+"/ApprovalChain/php/addTask.php";
            String task_name = params[1];
            String task_description = params[2];
            String task_amount = params[3];
            String approval_status;
            int t = Integer.parseInt(task_amount);
            if (t <= 500) {
                approval_status = "2";
            } else {
                approval_status = "0";
            }
            sharedPreferences = ctx.getSharedPreferences("ApprovalChain", Context.MODE_PRIVATE);
            String email = sharedPreferences.getString("Email", "");

            try {
                URL url = new URL(addtask_address);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("tasks_name", "UTF-8") + "=" + URLEncoder.encode(task_name, "UTF-8") + "&" +
                        URLEncoder.encode("tasks_description", "UTF-8") + "=" + URLEncoder.encode(task_description, "UTF-8") + "&" +
                        URLEncoder.encode("task_amount", "UTF-8") + "=" + URLEncoder.encode(task_amount, "UTF-8") + "&" +
                        URLEncoder.encode("approval_status", "UTF-8") + "=" + URLEncoder.encode(approval_status, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String response) {
        if(response.equals("Task successfully sent for approval")){
            Toast.makeText(ctx,response,Toast.LENGTH_LONG).show();
        }
    }
}
