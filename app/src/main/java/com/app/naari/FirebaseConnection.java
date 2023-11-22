package com.app.naari;

//import com.google.firebase.auth.FirebaseAuth;
import android.content.Context;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConnection {
    public static void initializeFirebase(Context context) throws IOException {
        try {
            FileInputStream serviceAccount = new FileInputStream("path/to/your/serviceAccountKey.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setDatabaseUrl("https://naari-42d27.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(context, options);

            System.out.println("Firebase has been initialized successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
