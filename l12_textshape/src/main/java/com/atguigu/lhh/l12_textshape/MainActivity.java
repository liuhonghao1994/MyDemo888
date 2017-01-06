package com.atguigu.lhh.l12_textshape;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    //获取图片
    private Bitmap bitmap;
    //将图片复制一份
    private Bitmap copyBit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView)findViewById(R.id.iv);
        //画笔
        Paint p=new Paint();
        p.setTextSize(20);
        //画板
        Canvas c=new Canvas(copyBit);
        c.drawBitmap(bitmap,new Matrix(),p);
        //初始化并且得到资源文件图片
        bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.f1);
        //将图片复制一份，用creatBitMap
        copyBit=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
      //给图片设置触摸事件
        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               if(motionEvent.getAction()==MotionEvent.ACTION_MOVE)
               {
                   int x= (int) motionEvent.getRawX();
                   int Y= (int) motionEvent.getRawY();
                   for(int i=-10;i<=10;i++)
                   {
                       for (int j=-10;j<=10;j++)
                       {
                           if(x+i>0 && Y+j>0 & x+i<bitmap.getWidth() && Y+j<bitmap.getHeight())
                           {

                               //在复制的图片上触摸过的地方复制指定颜色的
                               copyBit.setPixel(x+i,Y+j, Color.TRANSPARENT);
                               iv.setImageBitmap(copyBit);
                           }

                       }
                   }
               }
                return true;
            }
        });
    }
}
