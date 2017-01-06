package com.example.l12_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2016/12/26.
 * <p>
 * 1、继承自V4包下的Fragment
 * 2、重写  onCreateView
 * 3、创建fragment布局
 */

public class MyFragment02 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //加载布局
        //获取上下文  getActivity()
        View inflate = View.inflate(getActivity(), R.layout.fragment02_layout, null);

        
        return inflate;
    }
}
