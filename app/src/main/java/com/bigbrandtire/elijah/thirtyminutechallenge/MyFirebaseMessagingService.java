package com.bigbrandtire.elijah.thirtyminutechallenge;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        // new HandlePush("test");
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            // new HandlePush(remoteMessage.getNotification().getBody());

            if (/* Check if data needs to be processed by long running job */ false) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow(remoteMessage.getData().get("details"),remoteMessage.getData().get("title"));
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }
    }

    // Also if you intend on generating your own notifications as a result of a received FCM
    // message, here is where that should be initiated. See sendNotification method below.
//    private void scheduleJob() {
//        // [START dispatch_job]
//        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
//        Job myJob = dispatcher.newJobBuilder()
//                .setService(MyJobService.class)
//                .setTag("my-job-tag")
//                .build();
//        dispatcher.schedule(myJob);
//        // [END dispatch_job]
//    }


    private void handleNow(String message, String title) {
        Handler mainHandler = new Handler(Looper.getMainLooper());
        final String msg = message;
        Runnable myRunnable = new Runnable() {
            @Override
            public void run() { new HandlePush(msg);
            } // This is your code
        };
        mainHandler.post(myRunnable);

        sendNotification(message,title);
    }

    private void sendNotification(String messageBody,String title) {
        CheckForNotification.keepcounting = false;
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = "MY_CHANNEL";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.favicon16)
                        .setContentTitle(title)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        File cDir = getBaseContext().getCacheDir();
        File tempFile = new File(cDir.getPath() + "/user_data.txt") ;
        FileWriter writer = null;
        try {
            writer = new FileWriter(tempFile,false);
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        RestService  restService = new RestService();
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        restService.getDetailsServices().getOffersById(android_id, new Callback<BBTInvoiceDetails>() {

            @Override
            public void success(BBTInvoiceDetails bbtInvoiceDetails, Response response) {
                String test ="";
            }

            @Override
            public void failure(RetrofitError error) {

                String test ="";
            }
        });

    }
}
