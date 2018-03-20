package com.bigbrandtire.elijah.thirtyminutechallenge;

/**
 * Created by Elija on 3/15/2018.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Elijah on 3/14/2018.
 */

public class HandlePush extends AppCompatActivity {
    private String message;
    public HandlePush(String push){
        message = push;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.activity_main, null);
//        TextView tv = (TextView) (TextView)findViewById(R.id.textViewheader2);
//        tv.setText(message);
    }
}