package com.atguigu.indexview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作者：尚硅谷-杨光福 on 2017/1/3 09:51
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class IndexView extends View {
    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 整个视图的宽和高
     */
    private int viewWidth;
    private int viewHeight;

    //某条的宽和高
    private int itemWidth;
    private int itemHeight;

    private Paint paint;
    /**
     * 当前的索引
     */
    private int currentIndex = -1;

    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        //设置白色
        paint.setColor(Color.WHITE);
        //设置抗锯齿
        paint.setAntiAlias(true);
        //设置粗体字
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    /**
     * 测量-控件的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();

        //每一个item的宽和高
        itemWidth = viewWidth;
        itemHeight = viewHeight / words.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < words.length; i++) {

            //设置不同的演示
            if (i == currentIndex) {
                //灰色
                paint.setColor(Color.GRAY);
            } else {
                //白色
                paint.setColor(Color.WHITE);
            }
            //1.得到字母
            String word = words[i];//A~Z
            //2.计算字母宽和高
            Rect rect = new Rect();
            //画笔的方法
            paint.getTextBounds(word, 0, 1, rect);
            //字母的宽度和高
            int wordwidth = rect.width();
            int wordHeight = rect.height();


            //3.根据宽和高计算字母的坐标
            float wordX = itemWidth / 2 - wordwidth / 2;
            float wordY = itemHeight / 2 + wordHeight / 2 + i * itemHeight;


            //4.绘制字母
            canvas.drawText(word, wordX, wordY, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //索引
                int index = (int) (y / itemHeight);
                if (currentIndex != index) {
                    currentIndex = index;
                    //MyOnIndexChangeListener
                    if(listener != null && words!= null &&  currentIndex <words.length){
                        //MyOnIndexChangeListener
                        listener.onIndexChange(words[currentIndex]);
                    }
                    //把画笔设置灰色
                    invalidate();//会导致onDraw();
                }
                break;
            case MotionEvent.ACTION_UP:
                currentIndex = -1;
                invalidate();//会导致onDraw();
                break;
        }
        return true;
    }

    /**
     * 索引变化的监听器
     */
    public interface OnIndexChangeListener {
        /**
         * 当索引变化的时候回调对应的字母
         *
         * @param word
         */
        public void onIndexChange(String word);
    }
    //MyOnIndexChangeListener
    private OnIndexChangeListener listener;

    /**
     * 设置索引改变的监听
     * @param l : MyOnIndexChangeListener
     */
    public void setOnIndexChangeListener(OnIndexChangeListener l) {
        this.listener = l;
    }
}
