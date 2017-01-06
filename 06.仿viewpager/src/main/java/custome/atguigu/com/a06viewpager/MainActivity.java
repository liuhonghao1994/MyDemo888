package custome.atguigu.com.a06viewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private MyViewPager myViewPager;
    private int[] ids={R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewPager= (MyViewPager) findViewById(R.id.myViewPager);
        //把图片添加到MyViewPager中
        for(int i=0;i<ids.length;i++){
            ImageView img=new ImageView(this);
            img.setBackgroundResource(ids[i]);
            //将照片添加到自定义的MyViewPager中
            myViewPager.addView(img);
        }
    }
}
