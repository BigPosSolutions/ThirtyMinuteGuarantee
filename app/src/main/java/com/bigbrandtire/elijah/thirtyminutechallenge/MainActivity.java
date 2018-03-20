package com.bigbrandtire.elijah.thirtyminutechallenge;


import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtSavings, txtHour, txtMinute, txtSecond, starttime;
    private TextView tvEventStart;
    private Handler handler;
    private Runnable runnable;
    private final String TEMP_FILE_NAME = "user_data.txt";
    File tempFile;
    String strLine="";
    StringBuilder text = new StringBuilder();
    ProgressBar mProgressBar, mProgressBackGround;
    ImageView imgview;
    private Handler myHandler = new Handler();
    LinearLayout layout;
    long difference= 0;
    Date date1;
    Date date2;
    ListView lv;
    public boolean goin = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMinute = (TextView) findViewById(R.id.txtminutes);
        txtSecond = (TextView) findViewById(R.id.txtseconds);
        txtSavings = (TextView) findViewById(R.id.textView8);
        imgview = (ImageView) findViewById(R.id.imageView);
        layout =(LinearLayout) findViewById(R.id.hiddenfordeal);
        lv = (ListView) findViewById(R.id.offerslistview);

        layout.setVisibility(View.GONE);
        RestService restService = new RestService();
        restService.getOffersServices().getOffers(new Callback<List<Offers>>() {
            @Override
            public void success(List<Offers> offers, Response response) {
                ListViewAdapter lis = new ListViewAdapter(MainActivity.this, R.layout.offerslayout, offers);
                lv.setAdapter(lis);
            }
            @Override
            public void failure(RetrofitError error) {
                String test ="";
            }
        });


            starttime = (TextView) findViewById(R.id.textView3);
            File cDir = getBaseContext().getCacheDir();
            tempFile = new File(cDir.getPath() + "/" + TEMP_FILE_NAME) ;
            try {

                FileReader fReader = new FileReader(tempFile);
                BufferedReader bReader = new BufferedReader(fReader);



                /** Reading the contents of the file , line by line */
                while ((strLine = bReader.readLine()) != null) {
                    text.append(strLine);
                }
                JSONObject jsonObj = new JSONObject(String.valueOf(text));
                String str =(String) jsonObj.get("StartTime");
            starttime.setText(str);
            SimpleDateFormat datestart = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
            date1 = datestart.parse(str);
            date2 = datestart.parse(str);
            date2.setTime(date1.getTime() + (45 * 60000));
            Date nd = new Date();
            difference = date2.getTime() - nd.getTime();
               //difference = -5;
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
                e.printStackTrace();
            }
        countDownStart(difference);
    }

    public void countDownStart(final long difference) {

        CountDownTimer mCountDownTimer;
        int startvar = (int) (2700  - (difference/1000));
        final int[] i = {startvar};
        final int[] seconds = {60};
        mProgressBar=(ProgressBar)findViewById(R.id.circle_progress_bar);
        mProgressBar.setProgress(i[0]);
        mCountDownTimer = new CountDownTimer(difference,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(CheckForNotification.keepcounting){
                    i[0]++;
                    mProgressBar.setProgress((int)( i[0] *100/(2700000 /1000)));
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                    txtMinute.setText("" + String.format("%02d", minutes));
                    seconds[0] = seconds[0] - 1;
                    if(seconds[0] == 0){
                        seconds[0] = 60;
                    }
                    txtSecond.setText(""+ String.format("%02d", seconds[0]));
                }else{
                    if(goin == true) {
                        Intent ii = new Intent(MainActivity.this, ThankYou.class);
                        startActivity(ii);
                        goin = false;
                        finish();
                    }

                }
            }

            @Override
            public void onFinish() {
                //Do what you want
                i[0]++;
                mProgressBar.setProgress(100);
                startTime = date2.getTime();
                myHandler.postDelayed(updateTimerMethod, 0);
                layout.setVisibility(View.VISIBLE);
            }
        };
        mCountDownTimer.start();

    }
    long timeInMillies = 0L;
    private long startTime = 0L;
    long timeSwap = 0L;
    long finalTime = 0L;
    int _seconds = 0;
    private Runnable updateTimerMethod = new Runnable() {

        public void run() {
            if(CheckForNotification.keepcounting) {
                Date d = new Date();
                GradientDrawable drawab = (GradientDrawable) imgview.getDrawable();
                drawab.setColor(getResources().getColor(R.color.count_up));
                timeInMillies = d.getTime() - startTime;
                finalTime = timeSwap + timeInMillies;

                int seconds = (int) (finalTime / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                _seconds += seconds;
                mProgressBar.setProgress((int)( _seconds * 100/(2700000 /1000)));
                int milliseconds = (int) (finalTime % 1000);
                if(minutes <= 45) {
                    txtMinute.setText(String.valueOf(minutes));
                    txtSecond.setText(String.valueOf(seconds));
                    myHandler.postDelayed(this, 0);
                }
                txtSavings.setText(String.valueOf(minutes));
            }else{
                Intent ii = new Intent(MainActivity.this, ThankYou.class);
                startActivity(ii);
                finish();
            }
        }
    };
}

