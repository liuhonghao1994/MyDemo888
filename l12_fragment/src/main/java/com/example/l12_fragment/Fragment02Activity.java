package com.example.l12_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
/*
在activity中动态加载Fragment
 */
public class Fragment02Activity extends FragmentActivity {
    private LinearLayout ll_container;
    FragmentManager manager;
    FragmentTransaction ft;

    private MyFragment01 f01 = new MyFragment01();
    private MyFragment02 f02 = new MyFragment02();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment02);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        //将MyFragment01添加到 ll_container   显示
        //1、获取 Fragment管理类
        manager = getSupportFragmentManager();

    }
    public void add(View v){
//2、通过manager 打开操作fragment的事务
        ft = manager.beginTransaction();
        //3、通过事务对象 操作fragment
        //参数1：显示Fragment的容器
        ft.add(R.id.ll_container,f01);
        //4、显示提交  commit  关闭资源
        ft.commit();
    }
    public void remove(View v){
        ft = manager.beginTransaction();
        //移除 容器中添加的fragment
        ft.remove(f01);
        ft.commit();
    }
    public void replace(View v){
        //打开 fragment事务
        ft = manager.beginTransaction();
        //在显示fragment的容器中  替换fragment
        //参数1：容器  参数2：要替换的Fragment
        ft.replace(R.id.ll_container,f02);
        ft.commit();
    }
}
