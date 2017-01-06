package com.example.l11_test_graphics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testGraphics(View v) {
        startActivity(new Intent(this, TestGraphicsActivity.class));
    }

    public void testBitmap(View v) {
        startActivity(new Intent(this, TestBitmapActivity.class));


    }
}
