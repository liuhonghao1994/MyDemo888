package com.example.l11_test_graphics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TestBitmapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bitmap);
    }
    //测试图片
    public void testBitmap(View v){
        startActivity(new Intent(this,BitmapActivity.class));
    }
    public void testGraphics(View v){

    }
    //测试  自定义shape 做button的背景
    public void testShape(View v){

    }
}
