package com.example.l12_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/12/26.
 * <p>
 * 1、继承自V4包下的Fragment
 * 2、重写  onCreateView
 * 3、创建fragment布局
 *
 * 生命周期方法：
 *  添加Fragment对象显示
 *      E/TAG: onAttach
        E/TAG: onCreate
        E/TAG: onCreateView
        E/TAG: onActivityCreated
        E/TAG: onStart
        E/TAG: onResume
 home到桌面：
        E/TAG: onPause
        E/TAG: onStop
 回到应用E/TAG: onStart
        E/TAG: onResume
 replace为其它Fragment
 *       E/TAG: onPause
        E/TAG: onStop
        E/TAG: onDestroyView
         E/TAG: onDestroy
        E/TAG: onDetach
 返回到本身的Fragment[]
 退出应用[销毁]
 */

public class MyFragment01 extends Fragment {
    //关联布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.e("TAG","onCreateView");
        //加载布局
        //获取上下文  getActivity()
        View inflate = View.inflate(getActivity(), R.layout.fragment01_layout, null);


        return inflate;
    }
    //与activity建立关联
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG","onAttach");
    }
    //创建Fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("TAG","onCreate");
        super.onCreate(savedInstanceState);
    }
    //

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG","onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG","onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG","onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG","onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG","onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG","onDetach");
    }
}
