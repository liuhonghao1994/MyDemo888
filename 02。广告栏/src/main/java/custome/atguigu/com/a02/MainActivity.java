package custome.atguigu.com.a02;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static custome.atguigu.com.a02.R.drawable.e;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private TextView tvMsg;
    private LinearLayout llGroupPoint;
    private int preposition=0;
    private final String[] imageDescriptions = {"尚硅谷薄荷争霸赛", "凝聚你我，放飞梦想", "抱歉没作为了", "七月就业名单全部曝光", "平均起薪11357元"};
    int[] ids = new int[]{R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, e,};


    private void findViews() {
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
        llGroupPoint = (LinearLayout) findViewById(R.id.ll_group_point);
        //给第一张图片设置文字
        tvMsg.setText(imageDescriptions[preposition]);
    }
    //利用handler将图片设置为自动转换
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //循环播放
            //当前位置
            int item=viewpager.getCurrentItem()+1;
            viewpager.setCurrentItem(item);
            handler.sendEmptyMessageDelayed(0,3000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        viewpager.setAdapter(new MypageAdapter());
        //设置页面监听器
        viewpager.addOnPageChangeListener(new MyPageChangeListener());
        for (int i = 0; i < ids.length; i++) {
            //设置红色按钮
            ImageView imagepoint = new ImageView(this);
            imagepoint.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(10,10);
            //每个之间的间距
            if(i!=0) {
                params.leftMargin = 10;
                imagepoint.setEnabled(false);
            }else{
                imagepoint.setEnabled(true);
            }
            //设置按钮之间的布局
            imagepoint.setLayoutParams(params);
            llGroupPoint.addView(imagepoint);
        }
            //定位到中间位置，左右都能滑动
        int item=Integer.MAX_VALUE/2-Integer.MAX_VALUE%ids.length;
        //设置当前图片
        viewpager.setCurrentItem(item);
        //利用handler来进行图片的循环自动播放
        handler.sendEmptyMessageDelayed(0,3000);
    }



    class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            //返回改图片上对应的文字
            int relposition=position%ids.length;
            tvMsg.setText(imageDescriptions[relposition]);
            //把之前的设置为false，
            llGroupPoint.getChildAt(preposition).setEnabled(false);
            // 把当前的设置为true
            llGroupPoint.getChildAt(relposition).setEnabled(true);
            preposition=relposition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state==ViewPager.SCROLL_STATE_DRAGGING){
                handler.removeCallbacksAndMessages(null);
            }else if(state==ViewPager.SCROLL_STATE_IDLE){
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,300);
            }

        }
    }
//适配器和BaseAdapter不一样，是PagerAdapter
    class MypageAdapter extends PagerAdapter {
        //此方法是得到总共有几张图片，最后我们为了让他是无限次显示，我们给了他最大值
    @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
    //这个放法相当于BaseAdapter中第三个放法，得到图片
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            //给viewpager设置图片
            ImageView ima = new ImageView(MainActivity.this);
            ima.setBackgroundResource(ids[position%ids.length]);
            //要用到container，他是ViewGroup的对象，但实质上是ViewPager的对象
            container.addView(ima);
            //然后给图片设置触摸监听
            ima.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                   switch (motionEvent.getAction()){
                       case MotionEvent.ACTION_DOWN:
                           handler.removeCallbacksAndMessages(null);
                           break;
                       case MotionEvent.ACTION_UP:
                           handler.sendEmptyMessageDelayed(0,3000);
                           break;
                   }
                    return false;
                }
            });
            ima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this,"title"+imageDescriptions[position%ids.length],Toast.LENGTH_SHORT).show();
                }
            });
            return ima;
        }
//ViewPager最多能够存三张，第四章的时候就会第一张给消灭然后给他
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
