package com.app.naari;
import android.app.Application;
import com.google.firebase.FirebaseApp;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Other initialization code if needed
    }
}
