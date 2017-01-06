package custome.atguigu.com.a081;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by 刘红豪 on 2017/1/3.
 */

public class SlideLayout extends FrameLayout {
    //视图
    private View contentView;
    private View menuView;
    //控件的高
    private int viewHeight;
    private int contentViewwidth;
    private int menuViewwidth;
    private Scroller scroller;
    public SlideLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
    }
    //当布局加载后调用这个方法

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView=getChildAt(0);
        menuView=getChildAt(1);
    }
//ViewGroup中要绘画，所以要先绘出大小才能决定其轮廓
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight=getMeasuredHeight();
        contentViewwidth=contentView.getMeasuredWidth();
        menuViewwidth=menuView.getMeasuredWidth();
    }
//布局，指定视图在屏幕上的位置
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        contentView.layout(0,0,contentViewwidth,viewHeight);
        menuView.layout(contentViewwidth,0,contentViewwidth+menuViewwidth,viewHeight);
    }
    private float startx;
    private float starty;
    private float downx;
    private float downy;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
               downx=startx=event.getX();
                downy=starty=event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                 float endX=event.getX();
                float endY = event.getY();
                //偏移量
                float distancex=endX-startx;
                //要偏移的距离
                float dx=getScrollX()-distancex;
                //屏蔽非法值
                if(dx<0){
                    dx=0;
                }else if(dx>menuViewwidth){
                    dx=menuViewwidth;
                }
                //调用ScroTo方法
                scrollTo((int) dx,0);
                //重新赋值
                startx=event.getX();
                starty = event.getY();

                //水平方向滑动的距离
                float distanceDX = Math.abs(endX - downx);
                float distanceDY = Math.abs(endY - downy);
                //此时就认为他是水平的往外滑，不让他往上滑，所以不让父类得到消息，这样的话，就拦截了父类，
                if(distanceDX > distanceDY && distanceDX >5){
                    //子类反拦截
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                //能够偏移以后我们就要看是让他回调
                int totalscrollx =getScrollX();
                if(totalscrollx>menuViewwidth/2){
                    //打开
                    openMenu();
                }else{
                    //关闭
                    closemenu();
                }
                break;
        }
                return true;
    }
    //此时当给content设置了点击事件以后，其将事件消费了，所以menu滑不出来，所以要对她进行拦截

//因为我什么时候点击这是个不确定的时间，但是当我点击了以后就会回调方法，这时就用接口；
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
         super.onInterceptTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downx=startx=event.getX();
                if(listener!=null){
                    listener.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float endX=event.getX();
                //水平方向滑动的距离
                float distanceDX = Math.abs(endX - downx);
    //当距离超过8像素就视为不是点击，是滑动，就不让子类contentView得到消息，这样就拦截，由SlideLayout进行拦截，返回true
                if(distanceDX > 8){
                   return true;
                }
                break;

        }
        return false;

    }

    public void closemenu(){
        scroller.startScroll(getScrollX(),getScrollY(),0-getScrollX(),getScrollY());
        //每次移动完了以后要进行刷新
        invalidate();
        if(listener!=null){
            listener.onClose(this);
        }


    }

    public void openMenu(){
        scroller.startScroll(getScrollX(),getScrollY(),menuViewwidth-getScrollX(),getScrollY());
        invalidate();
       if(listener!=null){
            listener.onOpen(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            float currx=scroller.getCurrX();
            //移动
            scrollTo((int) currx,0);
            invalidate();
        }
    }
    public interface  OnStateChangedListener{
        //当按下时的回调方法
        public void onDown(SlideLayout layout);
        //当打开的时候回调的方法
        public void onOpen(SlideLayout layout);
        //当关闭时候回调该方法
        public void onClose(SlideLayout layout);
    }
    //字段
    private OnStateChangedListener listener;
    //设置打开，关闭，按下状态的监听
    public void setOnStateChangedListener(OnStateChangedListener l){
        this.listener=l;
    }
}
