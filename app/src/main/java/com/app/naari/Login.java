package com.app.naari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText mEMAIL, mPassword;

    Button mLoginBtn;

    TextView mCreateBtn;
    // Firebase fAuth;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        setContentView(R.layout.activity_login);
        mEMAIL = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);
        //fAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                String email = mEMAIL.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    mEMAIL.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password Must be >= 6 Character");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
            }

        });
        mCreateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }

        });
    }
}

