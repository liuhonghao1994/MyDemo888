package com.atguigu.a08;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * 作者：尚硅谷-杨光福 on 2017/1/3 14:18
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：自定义侧滑菜单
 */
public class SlideLayout extends FrameLayout {

    /**
     * 视图
     */
    private View contentView;
    private View menuView;

    /**
     * 控件的高
     */
    private int viewHeight;
    private int contentViewWidth;
    private int menuViewWidth;
    private Scroller scroller;

    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    /**
     * 当布局加载后后回调这个方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menuView = getChildAt(1);
    }

    /**
     * 计算视图的高和宽
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getMeasuredHeight();
        //一样的
//        viewHeight = contentView.getMeasuredHeight();
        contentViewWidth = contentView.getMeasuredWidth();
        menuViewWidth = menuView.getMeasuredWidth();
    }

    /**
     * 布局，指定视图在屏幕上的位置
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //指定contentView位置
        contentView.layout(0, 0, contentViewWidth, viewHeight);
        //指定menuView的位置
        menuView.layout(contentViewWidth, 0, contentViewWidth + menuViewWidth, viewHeight);

    }

    private float startX;
    private float startY;
    private float downX;
    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG","onTouchEvent--ACTION_DOWN");
                //1.记录在X轴的起始坐标
                downX =  startX = event.getX();
                downY =  startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG","onTouchEvent--ACTION_MOVE");
                //2.来到新的坐标
                float endX = event.getX();
                float endY = event.getY();
                //3.计算偏移量
                float distanceX = endX - startX;
                float dx = getScrollX() - distanceX;
                //4.屏幕非法值
                if (dx < 0) {
                    dx = 0;
                } else if (dx > menuViewWidth) {
                    dx = menuViewWidth;
                }
                //5.调用scrollTo
                scrollTo((int) dx, 0);
                //6.重新赋值
                startX = event.getX();
                startY = event.getY();

                //水平方向滑动的距离
                float distanceDX = Math.abs(endX - downX);
                float distanceDY = Math.abs(endY - downY);
                if(distanceDX > distanceDY && distanceDX >5){
                    //子类反拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }


                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG","onTouchEvent--ACTION_UP");
                //总的偏移量
                int totalScrollX = getScrollX();
                if (totalScrollX < menuViewWidth / 2) {
                    //关闭
                    closeMenu();
                } else {
                    //打开菜单
                    openMenu();
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG","onInterceptTouchEvent--ACTION_DOWN");
                //1.记录在X轴的起始坐标
                downX =  startX = event.getX();
                if(listener != null){
                    listener.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG","onInterceptTouchEvent--ACTION_MOVE");
                //2.来到新的坐标
                float endX = event.getX();

                //水平方向滑动的距离
                float distanceDX = Math.abs(endX - downX);
                if(distanceDX > 8 ){

                   return true;
                }


                break;
        }
        return false;
    }

    /**
     * --->menuWidth
     */
    public void openMenu() {
        scroller.startScroll(getScrollX(), getScrollY(), menuViewWidth - getScrollX(), getScrollY());
        //刷新
        invalidate();//onDraw 同时也会导致computeScroll

        if(listener != null){
            listener.onOpen(this);
        }
    }

    //-->0
    public void closeMenu() {
        scroller.startScroll(getScrollX(), getScrollY(), 0 - getScrollX(), getScrollY());
        //刷新
        invalidate();//onDraw 同时也会导致computeScroll
        if(listener != null){
            listener.onClose(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            //得到要移动的坐标
            float currX = scroller.getCurrX();
            //移动
            scrollTo((int) currX, 0);
            //刷新
            invalidate();
        }
    }

    /**
     * 打开，关闭，按下状态改变的监听器
     */
    public interface OnStateChangeListener{
        /**
         * 当按下的时候回调该方法
         * @param layout
         */
        public void onDown(SlideLayout layout);

        /**
         * 当打开的时候回调该方法
         * @param layout
         */
        public void onOpen(SlideLayout layout);

        /**
         * 当关闭的时候回调该方法
         * @param layout
         */
        public void onClose(SlideLayout layout);

    }

    private OnStateChangeListener listener;

    /**
     * 设置打开，关闭，按下状态改变的监听
     * @param l
     */
    public void setOnStateChangeListener(OnStateChangeListener l) {
        this.listener = l;
    }
}
