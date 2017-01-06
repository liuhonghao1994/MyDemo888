package custome.atguigu.com.a2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by 刘红豪 on 2017/1/5.
 */

public class SlideLayout extends FrameLayout {
    //初始化视图对象
    private View contentView;
    private View menuView;
    //控件的高
    private int viewHeight;
    private int contentViewWidth;
    private int mennuViewWidth;
    private Scroller scroller;
    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
    }
//当布局加载完成后调用这个方法，这个方法也就是获得他的两个孩子部件
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView=getChildAt(0);
        menuView=getChildAt(1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight=getMeasuredHeight();
        contentViewWidth=contentView.getMeasuredWidth();
        mennuViewWidth=menuView.getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        contentView.layout(0,0,contentViewWidth,viewHeight);
        menuView.layout(contentViewWidth,0,contentViewWidth+mennuViewWidth,viewHeight);
    }
    private float startx;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startx=event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float eventx=event.getX();
                float distancex=eventx-startx;
                //getScrollX（）是当前左上角相对于母视图左上角的偏移量x轴的
                float dx=getScrollX()-distancex;
                if(dx<0){
                    dx=0;
                }else if(dx>mennuViewWidth){
                    dx=mennuViewWidth;
                }
                scrollTo((int) dx,0);
                startx=eventx;
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }
}
