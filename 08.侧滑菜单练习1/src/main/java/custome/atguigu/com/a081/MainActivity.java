package custome.atguigu.com.a081;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listview);
        //准备数据
        iniData();
        MyAdapter adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }
    private void iniData() {
        data=new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add("Content"+i);
        }
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
             ViewHolder viewholder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.item_main,null);
                viewholder=new ViewHolder();
                viewholder.item_content= (TextView) convertView.findViewById(R.id.item_content);
                viewholder.item_menu= (TextView) convertView.findViewById(R.id.item_menu);
                convertView.setTag(viewholder);
            }else{
                viewholder= (ViewHolder) convertView.getTag();
            }
            viewholder.item_content.setText(data.get(i));
            //给content设置点击监听
            //首先设置tag
            viewholder.item_content.setTag(i);
            viewholder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i= (int) view.getTag();
                    Toast.makeText(MainActivity.this,""+data.get(i),Toast.LENGTH_SHORT).show();
                }
            });
            viewholder.item_menu.setTag(i);
            viewholder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int i= (int) view.getTag();
                    data.remove(i);
                    notifyDataSetChanged();
                    SlideLayout slideLayout = (SlideLayout) view.getParent();
                    slideLayout.closemenu();

                }
            });
         SlideLayout ss= (SlideLayout) convertView;
            MyOnStateChangeListener myOnStateChangeListener = new MyOnStateChangeListener();
            ss.setOnStateChangedListener(myOnStateChangeListener);
            return convertView;
        }
    }
    static class ViewHolder{
        TextView item_content;
        TextView item_menu;
    }
   private SlideLayout templayout;
    class MyOnStateChangeListener implements SlideLayout.OnStateChangedListener{

        @Override
        public void onDown(SlideLayout layout) {
            if(templayout!=null && layout!=templayout){
                //把之前的关掉
                templayout.closemenu();
            }
        }

        @Override
        public void onOpen(SlideLayout layout) {
            templayout=layout;

        }

        @Override
        public void onClose(SlideLayout layout) {
            //释放资源
            if(layout==templayout) {
                templayout = null;
            }
        }
    }
}
