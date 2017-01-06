package com.example.l12_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testF1(View v) {
        startActivity(new Intent(this,Fragment01Activity.class));
    }
    public void testF2(View v) {
        startActivity(new Intent(this,Fragment02Activity.class));

    }
}
