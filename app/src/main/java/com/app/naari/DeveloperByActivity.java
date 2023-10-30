package com.app.naari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class DeveloperByActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_by);
    }

    public void Sonika(View v){
        Intent implicit = new Intent(Intent.ACTION_VIEW);
        startActivity(implicit);
    }
}
