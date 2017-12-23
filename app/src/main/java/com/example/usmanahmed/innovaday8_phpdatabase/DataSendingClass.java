package com.example.usmanahmed.innovaday8_phpdatabase;

import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Usman Ahmed on 02/11/2017.
 */


public class DataSendingClass extends AsyncTask<String,Void,String> {


    ProgressDialog progressDialog;
    Context context;
    String response="";

    public DataSendingClass(Context context)
    {
        this.context=context;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog=new ProgressDialog(context);
        progressDialog.setMessage("Signing you up, please hold on");
        progressDialog.setTitle("Please wait");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... voids) {

        String name,username,password;
        name=voids[0];
        username=voids[1];
        password=voids[2];

        URL url=null;
        BufferedReader reader=null;

        String link="http://192.168.10.37/innova/insert.php";

        try
        {
            url=new URL(link);

            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15*1000);
            connection.setReadTimeout(15*1000);

            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");

            String fileinput= URLEncoder.encode("sendname","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
            fileinput+= "&"+URLEncoder.encode("sendusername","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
            fileinput+= "&"+URLEncoder.encode("sendpassword","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

            connection.connect();

            OutputStreamWriter writer=new OutputStreamWriter(connection.getOutputStream());

            writer.write(fileinput);
            writer.flush();
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line=reader.readLine())!=null)
            {
                response+= line;

            }// end of while loop




        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        progressDialog.dismiss();

        Toast.makeText(context, "Response "+result, Toast.LENGTH_SHORT).show();
    }
}// class end
