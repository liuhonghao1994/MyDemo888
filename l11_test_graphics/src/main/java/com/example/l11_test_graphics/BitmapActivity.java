package com.example.l11_test_graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BitmapActivity extends AppCompatActivity {

    private ImageView iv_bitmap;
    private Bitmap bitmap;
    private Matrix matrix ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        iv_bitmap = (ImageView) findViewById(R.id.iv_bitmap);
    }
    //获取图片路径
    public void read(View v){
        File file = getFilesDir();
        file = new File(file,"xiaoqiang.png");
        //读取图片文件  转化为 内存中的bitmap对象
        bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        iv_bitmap.setImageBitmap(bitmap);
    }
    public void save(View v){
        //保存bitmap对象 为图片
        File file;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            file = getExternalFilesDir(null);
        }else{
            file = getFilesDir();
        }
        File newFile = new File(file,"XXX.png");
        //使用Bitmap
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG,100,new FileOutputStream(newFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    //使用 矩阵缩放图片
    public void scale(View v){
        if (matrix==null)
        matrix = new Matrix();
        matrix.postScale(2,2);
        //矩阵设置给imageView    需要给imageView设置 scaleType属性：支持Matrix
//        iv_bitmap.setScaleType(ImageView.ScaleType.MATRIX);
        iv_bitmap.setImageMatrix(matrix);
    }
    //旋转图片
    public void rotate(View v){
        if (matrix==null)
            matrix = new Matrix();
        matrix.postRotate(30);
        iv_bitmap.setImageMatrix(matrix);
    }
    public void translate(View v){
        if (matrix==null)
            matrix = new Matrix();
        matrix.postTranslate(10,20);
        iv_bitmap.setImageMatrix(matrix);
    }
    public void reset(View v){
        if (matrix!=null)
            matrix.reset();
        iv_bitmap.setImageMatrix(matrix);
        matrix=null;
    }
}
