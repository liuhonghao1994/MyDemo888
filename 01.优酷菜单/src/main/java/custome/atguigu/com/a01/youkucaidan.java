package custome.atguigu.com.a01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class youkucaidan extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout level3;
    private RelativeLayout level2;
    private ImageView icon_menu;
    private RelativeLayout leve1;
    private ImageView icon_home;
    private boolean isLevel3Show = true;
    private boolean isLevel2Show = true;
    private boolean isLevelShow = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youkucaidan);
        level2 = (RelativeLayout)findViewById( R.id.level2 );
        icon_menu = (ImageView)findViewById( R.id.icon_menu );
        leve1 = (RelativeLayout)findViewById( R.id.leve1 );
        icon_home = (ImageView)findViewById( R.id.icon_home );
        level3 = (RelativeLayout)findViewById( R.id.level3 );
        //设置点击监听
        level2.setOnClickListener(this);
        icon_menu.setOnClickListener(this);
        leve1.setOnClickListener(this);
        icon_home.setOnClickListener(this);
        level3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.leve1:
                    Toast.makeText(getApplicationContext(),"leve1",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.level2:
                    Toast.makeText(getApplicationContext(),"level2",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.level3:
                    Toast.makeText(getApplicationContext(),"level3",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.icon_menu:
                    if(isLevel3Show)
                    {
                        isLevel3Show=false;
                        Tootls.hideView(level3);
                    }else{
                        isLevel3Show=true;
                        Tootls.showView(level3);
                    }
                    break;
                case R.id.icon_home:
                    //二级菜单和三级菜单显示就隐藏
                    if(isLevel2Show){
                        isLevel2Show=false;
                        Tootls.hideView(level2);
                        if(isLevel3Show){
                            isLevel3Show=false;
                            Tootls.hideView(level3,200);
                        }

                    }else{
                        isLevel2Show=true;
                        Tootls.showView(level2);
                    }
                break;


            }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_MENU){
            if(isLevelShow){
                isLevelShow=false;
                Tootls.hideView(level3);
                if(isLevel2Show){
                    isLevel2Show=false;
                    Tootls.hideView(level2);
                    if(isLevel3Show){
                        isLevel3Show=false;
                        Tootls.hideView(level3);
                    }
                }
            }else{
                //一级菜单没显示，二级菜隐藏就显示

                isLevelShow = true;
                //显示一级菜单
                Tootls.showView(leve1);

                isLevel2Show = true;
                //显示二级菜单
                Tootls.showView(level2,200);

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
