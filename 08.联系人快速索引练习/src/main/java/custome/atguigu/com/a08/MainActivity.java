package custome.atguigu.com.a08;

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
   private IndexView iv_lianxi;
    private TextView tv_lianxi;
    private ListView lv_lianxi;
    private Handler handler = new Handler();
    private ArrayList<Persons> persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_lianxi= (IndexView) findViewById(R.id.iv_lianxi);
        tv_lianxi= (TextView) findViewById(R.id.tv_lianxi);
        MyOnIndexChangeListener myOnIndexChangeListener=new MyOnIndexChangeListener();
        iv_lianxi.setOnIndexChangeListener(myOnIndexChangeListener);
        lv_lianxi= (ListView) findViewById(R.id.lv_lianxi);
        initData();
        MyAdapter adapter=new MyAdapter();
        lv_lianxi.setAdapter(adapter);
    }



    class MyOnIndexChangeListener implements IndexView.OnIndexChangeListener{

        @Override
        public void onIndexChange(String words) {
                updateword(words);
            updatelist(words);
        }
    }


    private void updatelist(String words){
        for(int i=0;i<persons.size();i++){
            String listWord = persons.get(i).getPinyin().substring(0,1);
            if(listWord.equals(words)){
                lv_lianxi.setSelection(i);
                return;
            }
        }
    }

    private void updateword(String words) {
        tv_lianxi.setText(words);
        tv_lianxi.setVisibility(View.VISIBLE);
        //清空消息
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_lianxi.setVisibility(View.GONE);
            }
        },2000);

    }
    private void initData() {

        persons = new ArrayList<>();
        persons.add(new Persons("张晓飞"));
        persons.add(new Persons("杨光福"));
        persons.add(new Persons("胡继群"));
        persons.add(new Persons("刘畅"));

        persons.add(new Persons("钟泽兴"));
        persons.add(new Persons("尹革新"));
        persons.add(new Persons("安传鑫"));
        persons.add(new Persons("张骞壬"));

        persons.add(new Persons("温松"));
        persons.add(new Persons("李凤秋"));
        persons.add(new Persons("刘甫"));
        persons.add(new Persons("娄全超"));
        persons.add(new Persons("张猛"));

        persons.add(new Persons("王英杰"));
        persons.add(new Persons("李振南"));
        persons.add(new Persons("孙仁政"));
        persons.add(new Persons("唐春雷"));
        persons.add(new Persons("牛鹏伟"));
        persons.add(new Persons("姜宇航"));

        persons.add(new Persons("刘挺"));
        persons.add(new Persons("张洪瑞"));
        persons.add(new Persons("张建忠"));
        persons.add(new Persons("侯亚帅"));
        persons.add(new Persons("刘帅"));

        persons.add(new Persons("乔竞飞"));
        persons.add(new Persons("徐雨健"));
        persons.add(new Persons("吴亮"));
        persons.add(new Persons("王兆霖"));

        persons.add(new Persons("阿三"));
        persons.add(new Persons("李岩"));
        persons.add(new Persons("王瑞"));


        //排序
        Collections.sort(persons, new Comparator<Persons>() {
            @Override
            public int compare(Persons lhs, Persons rhs) {
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });

    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int i) {
            return getItemId(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if(convertView==null){
                viewHolder=new ViewHolder();
                convertView=View.inflate(MainActivity.this,R.layout.item_index,null);
                viewHolder.tv_word= (TextView) convertView.findViewById(R.id.tv_word);
                viewHolder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(viewHolder);
            }else{
                viewHolder= (ViewHolder) convertView.getTag();
            }
             Persons person=persons.get(i);
            String word = person.getPinyin().substring(0, 1);
            viewHolder.tv_word.setText(word);
            viewHolder.tv_name.setText(person.getName());
            if(i==0){
                viewHolder.tv_word.setVisibility(View.VISIBLE);
            }else{
                String preword = persons.get(i-1).getPinyin().substring(0, 1);
                if(word.equals(preword)){
                    viewHolder.tv_word.setVisibility(View.GONE);
                }else{
                    viewHolder.tv_word.setVisibility(View.VISIBLE);
                }
            }
            return convertView;
        }

    }
    static  class ViewHolder{
        private TextView tv_word;
        private TextView tv_name;
    }
}
