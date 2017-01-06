package com.example.l11_pw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    View view ;
    private PopupWindow pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = View.inflate(this, R.layout.layout_popupwindow, null);
        view.findViewById(R.id.ll_xiezai).setOnClickListener(this);
        view.findViewById(R.id.ll_yunxing).setOnClickListener(this);
        view.findViewById(R.id.ll_fenxiang).setOnClickListener(this);

    }
    public void show(View v){
        if(pw!=null && pw.isShowing()){
            //隐藏popup
            pw.dismiss();
            pw = null;
        }else if(pw==null){
            //创建  popupwindow对象  此时决定popupwindw的大小 和内容
            pw = new PopupWindow(view,150,65);
            //pw会在  依附的view下面显示
            pw.showAsDropDown(v,0,-v.getHeight()+20);
            //再次点击  隐藏popupwindow
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_xiezai:
                Toast.makeText(this,"卸载",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_yunxing:
                Toast.makeText(this,"运行",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_fenxiang:
                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
