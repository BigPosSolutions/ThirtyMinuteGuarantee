package com.bigbrandtire.elijah.thirtyminutechallenge;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInActivity extends AppCompatActivity {
    RestService restService;
    private final String TEMP_FILE_NAME = "user_data.txt";
    File tempFile;
    private String android_id;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final CustomerInfo cus = new CustomerInfo();
        File cDir = getBaseContext().getCacheDir();
        tempFile = new File(cDir.getPath() + "/" + TEMP_FILE_NAME) ;
        restService = new RestService();

        setContentView(R.layout.activity_sign_in);
        final Button btn = (Button) findViewById(R.id.button2);
        final EditText txtName = (EditText) findViewById(R.id.editText2);
        final EditText txtPhone = (EditText) findViewById(R.id.editText3);
        final EditText txtInvoice = (EditText) findViewById(R.id.editText5);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            if(tempFile.exists()) {
                txtName.setVisibility(View.GONE);
                txtPhone.setVisibility(View.GONE);

            }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileWriter writer=null;

                if(!String.valueOf(txtInvoice.getText()).isEmpty()){
                    spinner.setVisibility(View.VISIBLE);
                    btn.setEnabled(false);
                    cus.Name = String.valueOf(txtName.getText());
                    cus.PhoneNumber = String.valueOf(txtPhone.getText());
                    cus.pushdetail.AppId = android_id;
                    cus.pushdetail.InvoiceNumber = String.valueOf(txtInvoice.getText());

                        try {
                            Date currentTime = new Date();
                            writer = new FileWriter(tempFile,false);
                            writer.write("{'appid':'" + android_id + "','StartTime':'" + currentTime+"'}");
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    restService.getService().addCustomers(cus, new Callback<CustomerInfo>() {
                        @Override
                        public void success(CustomerInfo customerInfo, Response response) {
                            Intent i = new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            String test ="";
                        }
                    });
                }else{
                    Toast.makeText(getBaseContext(), "Please Enter All Information", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
