package com.bigbrandtire.elijah.thirtyminutechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    private final String TEMP_FILE_NAME = "user_data.txt";
    File tempFile;
    String strLine="";
    StringBuilder text = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        File cDir = getBaseContext().getCacheDir();
        tempFile = new File(cDir.getPath() + "/" + TEMP_FILE_NAME) ;
   Thread myThread = new Thread(){
       @Override
       public void run() {
           FileReader fReader = null;
           long difference = -50;
           Date date1;
           Date date2;
           try {
               fReader = new FileReader(tempFile);
               BufferedReader bReader = new BufferedReader(fReader);
               /** Reading the contents of the file , line by line */
               while ((strLine = bReader.readLine()) != null) {
                   text.append(strLine);
               }
               JSONObject jsonObj = new JSONObject(String.valueOf(text));
               String str =(String) jsonObj.get("StartTime");
               if(str != "" && str != null) {
                   SimpleDateFormat datestart = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                   date1 = datestart.parse(str);
                   date2 = datestart.parse(str);
                   date2.setTime(date1.getTime() + (45 * 60000));
                   Date nd = new Date();
                   difference = date2.getTime() - nd.getTime();
               }
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } catch (JSONException e) {
               e.printStackTrace();
           } catch (ParseException e) {
               e.printStackTrace();
           }


           try {
               sleep(3000);
               Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
               Intent clockintent = new Intent(getApplicationContext(),MainActivity.class);

               if(difference > -45){
                   startActivity(clockintent);
               }else{
                   startActivity(intent);
               }

               finish();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   };
   myThread.start();
    }
}
