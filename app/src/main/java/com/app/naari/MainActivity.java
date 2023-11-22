package com.app.naari;
import com.google.firebase.FirebaseApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


//import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;

import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;
import com.app.naari.NotificationSender;

public class MainActivity extends AppCompatActivity {


    int count = 0;
    boolean isVolumeUpPressed = false;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keycode = event.getKeyCode();

        if (keycode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (action == KeyEvent.ACTION_DOWN) {
                isVolumeUpPressed = true;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isVolumeUpPressed && count >= 5) {
                            //The volume up button was held for 5 seconds
                            //Triggers a message/notification here

                            NotificationSender notificationSender = new NotificationSender();
                            NotificationSender.showNotificationAndSendSMS(this,"Emergency alert", "Please Help Me.");

                        }
                    }

                }, 5000); //5000 milliseconds = 5 seconds
            } else if (action == KeyEvent.ACTION_UP) {
                isVolumeUpPressed = false;
                count = 0; //Reset the count when the button is released
                handler.removeCallbacksAndMessages(null); // remove any pending callbacks
            }
        }
        return super.dispatchKeyEvent(event);

    }
//
//    private void showNotificationAndSendSMS(String title, String message) {
//        //Create and show a notification here
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "")
//    }
//


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = getIntent().getExtras();
        String V1 = extras.getString(Intent.EXTRA_TEXT);
        Log.d("NumberMainActivity", V1);

//        FirebaseApp.initializeApp(this);
    }
//    public void myProfile(View v){
//        Intent i = new Intent(getApplicationContext(), MyProfile.class);
//        startActivity(i);
//
    public void addRelative(View v){
        Intent i = new Intent(getApplicationContext(), AddRelative.class);
        startActivity(i);
    }

    public void helplineNumbers(View v){
        Intent i= new Intent(getApplicationContext(), helplinecall.class);
        startActivity(i);
    }

    public void triggers(View v){
        Intent i = new Intent(getApplicationContext(), TrigActivity.class);
        startActivity(i);
    }

    public void developedBy(View v) {
        Intent i = new Intent(getApplicationContext(), DeveloperByActivity.class);
        startActivity(i);
    }

    public void HowTo(View v) {
        Intent i = new Intent(getApplicationContext(), HowToSwipe.class);
        startActivity(i);
    }

    public void LogOut(View v){
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
    }
