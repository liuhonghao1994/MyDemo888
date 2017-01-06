package com.example.l11_test_graphics;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class TestGraphicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_graphics);
    }
    class MyView extends View {


        public MyView(Context context) {
            super(context);
        }
    }
}
