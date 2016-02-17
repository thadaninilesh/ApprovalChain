package com.example.thadaninilesh.approvalchain;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
 * Created by thadaninilesh on 13-02-2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {
    Context ctx;
    AlertDialog alertDialog;
    ProgressDialog progressDialog = null;
    String e_mail;
    public static final String MyPREFERENCES = "ApprovalChain" ;
    SharedPreferences sharedpreferences;
    //UserSessionManager session;
    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        /*progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait, your request is being processed");
        progressDialog.setCancelable(false);
        progressDialog.show();*/
    }


    @Override
    protected String doInBackground(String... params) {
        GlobalData globalData = new GlobalData();

        String ip = globalData.getIPData();
        String method = params[0];
        String reg_url = "http://"+ip+"/ApprovalChain/php/register.php";
        String login_url = "http://"+ip+"/ApprovalChain/php/login.php";

        if (method.equals("register")) {
            String employee_name, email, password, phone, flag;
            employee_name = params[1];
            email = params[2];
            password = params[3];
            phone = params[4];
            flag = params[5];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("employee_name","UTF-8") +"="+URLEncoder.encode(employee_name,"UTF-8")+"&"+
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
        } else if(method.equals("login")) {
                String employee_email = params[1];
                String employee_pass = params[2];
                e_mail = employee_email;
                try {
                    URL url1 = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("email", "UTF-8") +"="+URLEncoder.encode(employee_email, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") +"="+URLEncoder.encode(employee_pass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

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
                    sharedpreferences = ctx.getSharedPreferences(MyPREFERENCES, ctx.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Email", e_mail);
                    editor.putString("Designation", response);
                    editor.commit();
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
            } else if(method.equals("logout")){
                SharedPreferences sharedpreferences = ctx.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
            return "Logged out";
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
        /*if(progressDialog!=null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            progressDialog= null;
        }*/
        if(result.equals("Registeration successful! Login to continue")){
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            ctx.startActivity(new Intent(ctx, LoginActivity.class));
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else if(result.equals("1") || result.equals("0") || result.equals("2")){
            switch(result){
                case "0":{
                    Intent i = new Intent(ctx,EmployeeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(i);
                    Toast.makeText(ctx,"Employee Access",Toast.LENGTH_LONG).show();
                    break;
                }
                case "1":{
                    Intent i = new Intent(ctx,ManagerActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(i);
                    Toast.makeText(ctx,"Manager Access",Toast.LENGTH_LONG).show();
                    break;
                }
                case "2":{
                    Intent i = new Intent(ctx,BossActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ctx.startActivity(i);
                    Toast.makeText(ctx,"Boss Access",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        } else if(result.equals("Invalid credentials, please try again")){
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
            ctx.startActivity(new Intent(ctx, LoginActivity.class));
        }
        else if(result.equals("Logged out")){
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
            ctx.startActivity(new Intent(ctx, LoginActivity.class));
        }
        else {
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
            ctx.startActivity(new Intent(ctx, RegisterActivity.class));
        }
    }
}
