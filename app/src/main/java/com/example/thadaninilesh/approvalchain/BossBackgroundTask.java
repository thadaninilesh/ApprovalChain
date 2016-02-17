package com.example.thadaninilesh.approvalchain;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
 * Created by thadaninilesh on 17-02-2016.
 */
public class BossBackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    AlertDialog alertDialog;
    BossBackgroundTask(Context ctx){
        this.ctx = ctx;
    }
    GlobalData globalData = new GlobalData();
    ProgressDialog progressDialog = null;
    String ip = globalData.getIPData();
    String method;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait, your request is being processed");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        method = params[0];
        switch(method){
            case "addemployee":{
                String reg_url = "http://"+ip+"/ApprovalChain/php/register.php";
                String employee_name, email, password, phone, flag;
                employee_name = params[1];
                email = params[2];
                password = params[3];
                phone = params[4];
                flag = "0";//params[5];

                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                    String data = URLEncoder.encode("employee_name", "UTF-8") +"="+URLEncoder.encode(employee_name,"UTF-8")+"&"+
                            URLEncoder.encode("password","UTF-8") +"="+URLEncoder.encode(password,"UTF-8")+"&"+
                            URLEncoder.encode("email","UTF-8") +"="+URLEncoder.encode(email,"UTF-8")+"&"+
                            URLEncoder.encode("phone","UTF-8") +"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                            URLEncoder.encode("flag","UTF-8") +"="+URLEncoder.encode(flag,"UTF-8");

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
                    //return "Registeration successful";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                }
            }
            case "approvedapprovals":{
                String email = params[1];
                String address = "http://"+ip+"/ApprovalChain/php/bossApprovedApprovals.php";
                try {
                    URL url = new URL(address);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                    String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

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
                    globalData.setManagerApprovedData(response);

                    return response;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                }
                break;
            }
            case "pendingapprovals":{
                String email = params[1];
                String address = "http://"+ip+"/ApprovalChain/php/approvalWaitingBoss.php";
                try {
                    URL url = new URL(address);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                    String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

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
                    globalData.setManagerToBeApprovedData(response);

                    return response;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                }
                break;
            }
        }

        return "unsuccess";
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(progressDialog!=null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            progressDialog= null;
        }
        switch (method){
            case "addemployee":{
                Toast.makeText(ctx,"Employee added successfully",Toast.LENGTH_SHORT).show();
                break;
            }
            case "approvedapprovals":{
                Toast.makeText(ctx,"Approved Approvals",Toast.LENGTH_SHORT).show();
                break;
            }
            case "pendingapprovals":{
                Toast.makeText(ctx,"Pending Approvals",Toast.LENGTH_SHORT).show();
                break;
            }
        }

    }



}
