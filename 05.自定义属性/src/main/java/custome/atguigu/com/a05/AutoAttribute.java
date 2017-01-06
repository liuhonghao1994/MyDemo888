package custome.atguigu.com.a05;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 刘红豪 on 2016/12/30.
 */

public class AutoAttribute extends View {
    private int age;
    private String name;
    private Bitmap bitmap;
    public AutoAttribute(Context context, AttributeSet attrs) {
        super(context, attrs);
      /*  1.获取属性值：用命名空间；循环得到；使用系统工具获取 /1。命名空间
        String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","age");
        String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","name");
         String bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","bg");
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
           //属性的名称
             age = attrs.getAttributeName(i);
             name = attrs.getAttributeValue(i);

           Log.e("TAG", name + ",value==" + age);
        }*/
        //第三种方式，利用系统工具
        //第一个属性是属性集合
        //第二个属性是自定义属性的类
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.AutoAttribute);
        for(int i=0;i<array.getIndexCount();i++){
            //根据位置的到id
            int id = array.getIndex(i);
            switch (id){
                case R.styleable.AutoAttribute_age:
                    age = array.getInt(id, 0);
                    break;
                case R.styleable.AutoAttribute_bg:
                    Drawable drawable = array.getDrawable(id);
                    //强转为BitmapDrawable
                    BitmapDrawable drawable1 = (BitmapDrawable) drawable;
                    bitmap=drawable1.getBitmap();

                    break;
                case R.styleable.AutoAttribute_name:
                    name=array.getString(id);
                    break;

            }
        }
    }
//绘制属性的值
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawText(age+","+name,10,10,paint);
        canvas.drawBitmap(bitmap,20,20,paint);

    }
}
