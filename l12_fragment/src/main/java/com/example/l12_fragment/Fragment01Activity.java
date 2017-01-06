package com.example.l12_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/*
测试静态加载Fragment   在布局文件中加载
    创建Fragment的步骤[]
    静态加载Fragment[让fragment依附于Activity]
    activity继承自 v4下的FragmentActivity
 */
public class Fragment01Activity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment01);
    }
}
