package com.example.l12_app_fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends FragmentActivity {

    private LinearLayout ll_container;
    private RadioGroup rg_main;
    private RadioButton rbtn;
    //FragmentM
    private FragmentManager fm;
    private Fragment01 f01;
    private Fragment02 f02;
    private Fragment03 f03;
    private Fragment04 f04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        fm = getSupportFragmentManager();
        //给rg设置 选择监听
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               chooseFragment(checkedId);
            }
        });
        chooseFragment(R.id.rbtn_more);
        ((RadioButton)findViewById(R.id.rbtn_more)).setChecked(true);
    }

    private void chooseFragment(int checkedId) {
        FragmentTransaction ft = fm.beginTransaction();
        findViewById(R.id.rbtn_home).setBackgroundColor(Color.WHITE);
        findViewById(R.id.rbtn_more).setBackgroundColor(Color.WHITE);
        findViewById(R.id.rbtn_account).setBackgroundColor(Color.WHITE);
        findViewById(R.id.rbtn_fund).setBackgroundColor(Color.WHITE);
        findViewById(checkedId).setBackgroundColor(Color.DKGRAY);
        switch (checkedId) {
            case R.id.rbtn_home:

                //切换 fragment
                if (f01 == null) {
                    f01 = new Fragment01();
                }
                ft.replace(R.id.ll_container, f01);
                break;
            case R.id.rbtn_account:
                if (f02 == null) {
                    f02 = new Fragment02();
                }
                ft.replace(R.id.ll_container, f02);
                break;
            case R.id.rbtn_fund:
                if (f03 == null) {
                    f03 = new Fragment03();
                }
                ft.replace(R.id.ll_container, f03);

                break;
            case R.id.rbtn_more:
                if (f04 == null) {
                    f04 = new Fragment04();
                }
                ft.replace(R.id.ll_container, f04);
                break;
        }
        ft.commit();
    }
}
