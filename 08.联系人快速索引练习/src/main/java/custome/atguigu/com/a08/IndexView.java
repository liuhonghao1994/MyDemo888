package custome.atguigu.com.a08;

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
 * Created by 刘红豪 on 2017/1/5.
 */

public class IndexView extends View {
    private String[] word = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private int viewWidth;
    private int viewHeight;
    private int itemWidht;
    private int itemHeight;
    private Paint paint;
    private int currentindex=-1;
    public IndexView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewWidth=getMeasuredWidth();
        viewHeight=getMeasuredHeight();
        itemWidht=viewWidth;
        itemHeight=viewHeight/word.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<word.length;i++){
            String words = word[i];
            if(i==currentindex){
                paint.setColor(Color.GRAY);
            }else{
                paint.setColor(Color.WHITE);
            }
            //计算字母的宽和高
            Rect rect=new Rect();
            //画笔的方法
            paint.getTextBounds(words,0,1,rect);
            //利用画笔能够得到字母的宽和高
            int width = rect.width();
            int height = rect.height();
            //能够通过子母的宽和高得出子母的坐标

            float wordsx=itemWidht/2-width/2;
            float wordsy=itemHeight/2+height/2+i*itemHeight;
            canvas.drawText(words,wordsx,wordsy,paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
                super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int index = (int) (event.getY() / itemHeight);
                if(currentindex!=index){
                    currentindex=index;
                    if(listener!=null && word!=null && currentindex<word.length){
                        listener.onIndexChange(word[currentindex]);
                    }
                    invalidate();//此时调用onDrawn（）方法，所以更改颜色应该在onDrawn（）中

                }
                break;
            case MotionEvent.ACTION_UP:
                currentindex=-1;
                invalidate();
                break;
        }
                    return true;
    }
    public interface  OnIndexChangeListener{
        public void onIndexChange(String words);
    }
    private OnIndexChangeListener listener;
    public void setOnIndexChangeListener(OnIndexChangeListener l){
        this.listener=l;
    }
}
