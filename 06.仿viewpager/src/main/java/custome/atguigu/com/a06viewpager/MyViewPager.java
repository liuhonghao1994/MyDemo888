package custome.atguigu.com.a06viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 刘红豪 on 2016/12/30.
 */

public class MyViewPager extends ViewGroup{
    private GestureDetector detector;
    private MyScroller scroller;
    //手势识别器的使用，1.定义手势识别器2.初始化3,.在触摸事件中把事件传递给手势识别器进行解析，让她滑动显示
   //各个页面的下标位置
    private int index;
    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造器中初始化手势识别器
        detector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            //distancex在X轴移动的距离，distancey表示在y轴移动的距离
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distancex, float distancey) {
              //滑动的是ViewPager里面的内容，而ViewPager是不变的，两个参数是移动 的距离，是View提供的方法，用来做视图的移动
               scrollBy((int)distancex,0);
                return true;
            }


        });
        scroller=new MyScroller();
    }
//通过onlayout这个方法，我们才能够看到将ImagineView设置给自定义ViewPager的图像，在这一步图片才显示上去，然后让他滑动，设置手势监听器
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i=0;i<getChildCount();i++){
            //ImageView
            //得到每个孩子
            View childView =  getChildAt(i);
            //根据不同的下标位置，指定孩子的具体位置
            childView.layout(i*getWidth(),0,(1+i)*getWidth(),getHeight());
        }
    }
//用触摸事件返回给手势识别器进行解析
    private float  startx;
    //设置出没监听就是为了让图片回调
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startx=event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
               
                break;
            case MotionEvent.ACTION_UP:
                float endx=event.getX();
                int imeindex=index;
                if(endx-startx>0){
                    imeindex--;
                }else if(startx-endx>0){
                    imeindex++;
                }
                moveTo(imeindex);//这个方法是为了不让imeindex下标越界
                break;
        }
        return true;//后续的move和up才能给部件
    }


    private void moveTo(int imeindex) {
        if(imeindex<0){
            imeindex=0;
        }else if (imeindex>getChildCount()-1){
            imeindex=getChildCount()-1;
        }
        //真正的下标
        index=imeindex;
        int distancex=index*getWidth()-getScrollX();
        //移动到对应下标的位置图,x轴有移动，y轴没有
       // scrollTo(index,0);在这里没有用到此方法是要在下面重新写这个方法，为了能够让她平滑的移动
        
        scroller.startScroll(getScrollX(),0,distancex,0);
        invalidate();//刷新一下,调用这个方法的时候我们不仅调用了onDrawable()而且还调用了computeScroll（）方法
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            //得到当前移动的这一小段距离
            float currX = scroller.getCurrX();
            scrollTo((int)currX,0);
            invalidate();
        }

    }
}
