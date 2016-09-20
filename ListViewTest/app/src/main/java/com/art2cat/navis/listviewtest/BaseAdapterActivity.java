package com.art2cat.navis.listviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by art2cat on 5/11/16.
 */
public class BaseAdapterActivity extends AppCompatActivity {
    private ListView listView;
    public ArrayList<HashMap<String, Object>> listitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baseadapter);
        setTitle("BaseAdapter示例与优化");

        listView = (ListView)findViewById(R.id.baseadapter);
        MyAdapter myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("MyListViewBase", "你点击了ListView条目" + position);
            }
        });

    }
    private ArrayList<HashMap<String, Object>> getDate() {
        listitem = new ArrayList<HashMap<String, Object>>();

        for (int a = 1; a < 101; a++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "第" + a + "行");
            map.put("ItemText", "这是第" + a + "行");
            map.put("ItemID", a);
            listitem.add(map);
        }
        return listitem;
    }

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MyAdapter(Context context) {
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return getDate().size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.baseadapteritem, null);
                holder = new ViewHolder();
                holder.title = (TextView)convertView.findViewById(R.id.item_title1);
                holder.text = (TextView)convertView.findViewById(R.id.item_text1);
                holder.button = (Button)convertView.findViewById(R.id.item_button);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.title.setText(getDate().get(position).get("ItemTitle").toString());
            holder.text.setText(getDate().get(position).get("ItemText").toString());
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = getDate().get(position).get("ItemID").hashCode();
                    setTitle("这是第" + i + "行");
                }
            });
            return convertView;
        }
    }

    private final class ViewHolder {
        public TextView title;
        public TextView text;
        public Button button;
    }

}
