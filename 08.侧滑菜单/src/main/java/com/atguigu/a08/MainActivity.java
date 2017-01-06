package com.atguigu.a08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    /**
     * 数据集合
     */
    private ArrayList<String> msg;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //准备数据
        initData();
        //设置适配器
        adapter  = new MyAdapter();
        listView.setAdapter(adapter);

    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return msg.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = View.inflate(MainActivity.this,R.layout.item_main,null);
                viewHolder = new ViewHolder();
                viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
                viewHolder.item_menu = (TextView) convertView.findViewById(R.id.item_menu);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置获取数据并且设置
            viewHolder.item_content.setText(msg.get(position));

            //设置tag
            viewHolder.item_content.setTag(position);
            //设置点击事件
            viewHolder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    Toast.makeText(MainActivity.this, ""+msg.get(position), Toast.LENGTH_SHORT).show();

                }
            });

            viewHolder.item_menu.setTag(position);
            viewHolder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    //1.移除数据
                    msg.remove(position);
                    //2.刷新
                    notifyDataSetChanged();
                    //3.把侧滑菜单关闭
                    SlideLayout slideLayout = (SlideLayout) v.getParent();
                    slideLayout.closeMenu();

                }
            });

            SlideLayout slideLayout = (SlideLayout) convertView;
            //设置状态变化的监听
            MyOnStateChangeListener myOnStateChangeListener = new MyOnStateChangeListener();
            slideLayout.setOnStateChangeListener(myOnStateChangeListener);

            return convertView;
        }
    }
    private SlideLayout tempLayout;

    class MyOnStateChangeListener implements SlideLayout.OnStateChangeListener{

        @Override
        public void onDown(SlideLayout layout) {
            //缓存是否为空，判断是否同一个
            if(tempLayout != null && layout != tempLayout){
                //把之前打开的关闭
                tempLayout.closeMenu();
            }

        }

        @Override
        public void onOpen(SlideLayout layout) {
            //1.记录打开的SlideLayout对象
            tempLayout = layout;

        }

        @Override
        public void onClose(SlideLayout layout) {
            if(layout == tempLayout){
                //释放资源
                tempLayout = null;
            }

        }
    }

    static class ViewHolder{
        TextView item_content;
        TextView item_menu;
    }

    private void initData() {
        msg = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            msg.add("Content_" + i);
        }
    }
}
