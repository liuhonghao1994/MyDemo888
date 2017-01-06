package com.example.l11_;

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
    private ImageView iv_main;
    //获取上层图片
    private Bitmap bitmap;
    private Bitmap copyBit;
    // alt+enter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_main = (ImageView) findViewById(R.id.iv_main);
        //从资源文件中 获取位图对象  参数1：resourse资源文件类
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.f1);
        //在内存中复制一张位图对象   按照  上层图片的大小 复制
        copyBit = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        //画笔
        Paint p= new Paint();
        //画板   以copyBitmap做为画板
        Canvas c = new Canvas(copyBit);
        //在画板上 绘制图形   设置默认的矩阵   设置画笔
        c.drawBitmap(bitmap,new Matrix(),p);
        //iv设置 触摸监听
        iv_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //移动时修改 移动过的地方
                if(event.getAction()==MotionEvent.ACTION_MOVE){
                    //增大触摸的范围
                    //获取手指触摸的地方
                    int x = (int) event.getRawX();
                    int y = (int) event.getRawY();
                    for (int i=-10;i<=10;i++){
                        for (int j=-10;j<=10;j++){
                            if(x+i>0 && y+j>0 && x+i<bitmap.getWidth() && y+j<bitmap.getHeight() ){
                                //在复制的图片上 触摸过的地方 绘制  指定颜色
                                copyBit.setPixel(x+i,y+j, Color.TRANSPARENT);//透明色
                                //重新 给iv 设置 图片
                                iv_main.setImageBitmap(copyBit);
                            }
                        }

                    }

                }
                return true;
            }
        });

    }
}
