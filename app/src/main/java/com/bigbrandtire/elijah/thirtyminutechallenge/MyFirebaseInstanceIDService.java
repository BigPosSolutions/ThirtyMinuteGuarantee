package com.bigbrandtire.elijah.thirtyminutechallenge;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import android.provider.Settings.Secure;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Elija on 3/15/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    RestService restService;
    private String android_id;
    public MyFirebaseInstanceIDService(){

    }
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        CustomerInfo cus = new CustomerInfo();
        restService = new RestService();
        android_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        cus.PushKey = refreshedToken;
        cus.AppId = android_id;
        cus.Name = "DefaultEli";
        Log.d(TAG, "Refreshed token: " + cus);
        restService.getService().addCustomers(cus, new Callback<CustomerInfo>() {
            @Override
            public void success(CustomerInfo customerInfo, Response response) {
                Log.d(TAG, "Success " + refreshedToken);
            }

            @Override
            public void failure(RetrofitError error) {
            String test ="";
            }
        });
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        // sendRegistrationToServer(refreshedToken);
    }
}