package com.art2cat.navis.listviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by art2cat on 5/11/16.
 */
public class SimpleAdapterActivity extends AppCompatActivity {
    private ListView listView;
    private String[] strings = new String[] {
            "ItemImage","ItemTitle","ItemText",
    };
    private int[] ids = new int[] {
            R.id.item_image,R.id.item_title,R.id.item_text
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpleadapter);
        setTitle("SimpleAdapter示例");

        ListView listView = (ListView)findViewById(R.id.simpleadapter);
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        for (int i = 1; i < 11; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.meow);
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemText", "这是第" + i + "行");
            listItem.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItem,
                R.layout.simpleadapteritem,strings,ids);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int arg = position + 1;
                setTitle("你点击了第" + arg + "行");
            }
        });
    }
}
