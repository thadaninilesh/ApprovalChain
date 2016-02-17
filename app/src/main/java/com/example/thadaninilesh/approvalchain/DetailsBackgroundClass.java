package com.example.thadaninilesh.approvalchain;

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
 * Created by thadaninilesh on 16-02-2016.
 */
public class DetailsBackgroundClass extends AsyncTask<String,Void,String> {
    Context ctx;
    GlobalData globalData = new GlobalData();
    String ip = globalData.getIPData();
    private String email, method, address;
    public DetailsBackgroundClass(Context ctx){this.ctx = ctx;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        email = params[0];
        method = params[1];
       if(method=="show") {
           address = "http://" + ip + "/ApprovalChain/php/showDetails.php";
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
               globalData.setEditDetails(response);
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
       }
        else if(method=="edit") {
           ////
           String name, phone;
           name = params[2];
           phone = params[3];
           address = "http://" + ip + "/ApprovalChain/php/editDetails.php";

           try {
               URL url = new URL(address);
               HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
               httpURLConnection.setRequestMethod("POST");
               httpURLConnection.setDoOutput(true);
               OutputStream os = httpURLConnection.getOutputStream();
               BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

               String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                       URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                       URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");

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
               globalData.setEditDetails(response);
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
       }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String response) {
        if(method=="show")
                Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
        else if(method=="edit")
                Toast.makeText(ctx, response, Toast.LENGTH_SHORT).show();
    }
}
