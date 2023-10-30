package com.app.naari;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotificationSender extends AppCompatActivity {
    public static void showNotificationAndSendSMS(Runnable runnable, String emergency_alert, String message) {
    }

    public void showNotificationAndSendSMS(Context context, String title, String message) {
//        //Create and show a notification here
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Emergency alert")
                .setSmallIcon(R.layout.activity_notification_sender)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Emergency alert", "Emergency alert", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);


    }

    notificationManager.notify(0, builder.build());

    //Send an SMS to the admin number
    String adminPhoneNumber = "9861569413";
    String smsMessage = "Emergency: " + message;

        try {
            android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
            smsManager.sendTextMessage(adminPhoneNumber, null, smsMessage, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

