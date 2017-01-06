package custome.atguigu.com.a04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.endX;

/**
 * Created by 刘红豪 on 2016/12/30.
 */
//view的绘制过程
//测量measure，重写onMeasure，得到控件的大小
    //制定控件在屏幕上的位置，layout-onlayout
    //view是每个子类绘制自己的内容。viewGroup是调用各个子类的绘制方法
//自定义类继承View，
public class ToggleButton extends View implements View.OnClickListener{
    //要得到照片用到Bitmap
    private Bitmap backgroundBitmap;
    private Bitmap slideBitmap;
    //画笔
    private Paint paint;
    //设置显示的值，就是开那个字的值
    private int maginLeft;
//代码实例化该类的时候一般用一个参数的
    public ToggleButton(Context context) {
        this(context,null);
    }
//一般在布局文件中用两个参数的构造器
    public ToggleButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //在第三个构造器中进行初始化
        paint=new Paint();
        //这是抗锯齿
        paint.setAntiAlias(true);
        //h获取图片
        backgroundBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.switch_background);
        slideBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
        maginLeft = backgroundBitmap.getWidth() - slideBitmap.getWidth();
        //给该图片设置监听
        ToggleButton.this.setOnClickListener(this);

    }
//测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(backgroundBitmap.getWidth(),backgroundBitmap.getHeight());
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        canvas.drawBitmap(backgroundBitmap,0,0,paint);
        //绘制侧滑按钮
        canvas.drawBitmap(slideBitmap,slideLeft,0,paint);
    }
    //设置点击事件，定义一个变量，为了让你点击一下他变为0，显示为关闭，再次点击一下变为maginLeft,显示为开的效果
    private int slideLeft;
    //同时是否点击状态也会发生变化
    private boolean isOpen = false;//为关闭状态
    private boolean isEnableClick = true;//设置点击事件是否生效

    @Override
    public void onClick(View view) {
        if (isEnableClick) {
            isOpen = !isOpen;
            flushView();

        }
    }

//将开关状态改变提取成为一个方法
    private void flushView() {
        if(isOpen){
            slideLeft = maginLeft;//开的状态
        }else {
            slideLeft = 0;//关的状态
        }
        invalidate();//強制繪製
    }
    private float startx;
    private float lastx;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//調用父类的方法进行事件回传，不调用回传不了
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //按下获取位置
                lastx=startx=event.getX();//last这是一个不变的位置
                isEnableClick=true;
                break;
            case MotionEvent.ACTION_MOVE:
                //2.来到新的坐标
                float endx = event.getX();
                //3.计算偏移量
                float distancex = endx- startx;
                //4.加上滑动的距离
                slideLeft = (int) (slideLeft + distancex);
                //5.要屏蔽非法值
                if(slideLeft <0){
                    slideLeft = 0;
                }

                if(slideLeft > maginLeft){
                    slideLeft = maginLeft;
                }

                if(Math.abs(endX-lastx) >5){//在移动事件中如果说移动距离超过5个像素就算是触摸事件
                    //响应滑动事件
                    isEnableClick = false;
                }

                //6.刷新-onDraw
                invalidate();
                //重写赋值
                startx = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if(!isEnableClick) {//当手指离开控件时将条件变为true才能执行括号里面的代码
                    if (slideLeft > maginLeft / 2) {

                        isOpen = true;

                    } else if (slideLeft <= maginLeft / 2) {

                        isOpen = false;
                    }
                    flushView();
                }
                    break;

        }
        return true;

    }
}
