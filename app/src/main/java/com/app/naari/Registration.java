package com.app.naari;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.safetynet.SafetyNetApi.RecaptchaTokenResult;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import  com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.ProgressBar;

public class Registration  extends AppCompatActivity{
    EditText mFullName,mEmail,mPassword,mPhone, etReCaptcha;
    Button mRegisterbtn;
    TextView mCreateText;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mFullName =findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        etReCaptcha = findViewById(R.id.etReCaptcha);
        mRegisterbtn = findViewById(R.id.registerbtn);
        mCreateText = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
//                String reCaptchaToken = etReCaptcha.getText().toString();
                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }
                String reCaptchaSiteKey = "6LdXYhUpAAAAAL6sd5sxC_wBOSkKgc5XEEu1tiT7";
                String reCaptchaToken = etReCaptcha.getText().toString();

                SafetyNet.getClient(v.getContext()).verifyWithRecaptcha("6LdXYhUpAAAAAL6sd5sxC_wBOSkKgc5XEEu1tiT7")
                        .addOnSuccessListener((Activity) v.getContext(),new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                                // Handle successful reCAPTCHA verification
                                // Proceed with user registration logic
                            }
                        })
                        .addOnFailureListener((Activity) v.getContext(), new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle reCAPTCHA verification failure
                                // Display an error message or take appropriate action
                            }
                        });


                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(Registration.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    });

                    mCreateText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                    });

                }
        });
    }
}
