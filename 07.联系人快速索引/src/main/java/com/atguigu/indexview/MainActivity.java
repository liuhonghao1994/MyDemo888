package com.atguigu.indexview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    private IndexView iv_main_words;
    private TextView tv_main_word;
    private ListView listview;
    private ArrayList<Person> persons;

    private Handler handler = new Handler();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_main_words = (IndexView) findViewById(R.id.iv_main_words);
        tv_main_word = (TextView) findViewById(R.id.tv_main_word);
        listview = (ListView) findViewById(R.id.listview);
        //设置索引变化的监听
        MyOnIndexChangeListener myOnIndexChangeListener = new MyOnIndexChangeListener();
        iv_main_words.setOnIndexChangeListener(myOnIndexChangeListener);

        //设置适配器
        initData();
        adapter = new MyAdapter();
        listview.setAdapter(adapter);

//        listview.setSelection(10);
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return persons.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null){
                convertView = View.inflate(MainActivity.this,R.layout.item_index,null);
                viewHolder = new ViewHolder();
                viewHolder.tv_word = (TextView) convertView.findViewById(R.id.tv_word);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到对应的数据
            Person person = persons.get(position);
            //当前的字母
            String word = person.getPinyin().substring(0,1);
            viewHolder.tv_word.setText(word);
            viewHolder.tv_name.setText(person.getName());

            if(position ==0){
                viewHolder.tv_word.setVisibility(View.VISIBLE);
            }else{

                //上一个字母
                String preWord = persons.get(position-1).getPinyin().substring(0,1);
                if(word.equals(preWord)){
                    viewHolder.tv_word.setVisibility(View.GONE);
                }else{
                    viewHolder.tv_word.setVisibility(View.VISIBLE);
                }


            }
            return convertView;
        }
    }
    static class ViewHolder{
        TextView tv_word;
        TextView tv_name;
    }

    class MyOnIndexChangeListener implements IndexView.OnIndexChangeListener{

        @Override
        public void onIndexChange(String word) {
            updateWord(word);
            updateList(word);
        }
    }

    /**
     * 回调过来的字母
     * @param word A~Z
     */
    private void updateList(String word) {
        for (int i=0;i<persons.size();i++){
            //列表中的位置
            String listWord = persons.get(i).getPinyin().substring(0,1);//A~Z
            if(listWord.equals(word)){
                //选中对应的位置
                //Listview方法
                listview.setSelection(i);
                return;
            }
        }

    }

    private void updateWord(String word) {
        tv_main_word.setText(word);
        tv_main_word.setVisibility(View.VISIBLE);
        //清空消息
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //两秒后执行该方法
                    //主线程
                    tv_main_word.setVisibility(View.GONE);
                }
        }, 2000);
    }


    /**
     * 初始化数据
     */
    private void initData() {

        persons = new ArrayList<>();
        persons.add(new Person("张晓飞"));
        persons.add(new Person("杨光福"));
        persons.add(new Person("胡继群"));
        persons.add(new Person("刘畅"));

        persons.add(new Person("钟泽兴"));
        persons.add(new Person("尹革新"));
        persons.add(new Person("安传鑫"));
        persons.add(new Person("张骞壬"));

        persons.add(new Person("温松"));
        persons.add(new Person("李凤秋"));
        persons.add(new Person("刘甫"));
        persons.add(new Person("娄全超"));
        persons.add(new Person("张猛"));

        persons.add(new Person("王英杰"));
        persons.add(new Person("李振南"));
        persons.add(new Person("孙仁政"));
        persons.add(new Person("唐春雷"));
        persons.add(new Person("牛鹏伟"));
        persons.add(new Person("姜宇航"));

        persons.add(new Person("刘挺"));
        persons.add(new Person("张洪瑞"));
        persons.add(new Person("张建忠"));
        persons.add(new Person("侯亚帅"));
        persons.add(new Person("刘帅"));

        persons.add(new Person("乔竞飞"));
        persons.add(new Person("徐雨健"));
        persons.add(new Person("吴亮"));
        persons.add(new Person("王兆霖"));

        persons.add(new Person("阿三"));
        persons.add(new Person("李岩"));
        persons.add(new Person("王瑞"));


        //排序
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });

    }
}
