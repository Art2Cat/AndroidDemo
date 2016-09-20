package com.art2cat.navis.listviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by art2cat on 5/11/16.
 */
public class ArrayAdapterActivity extends AppCompatActivity {
    private static final String[] strings = new String[] {
            "first", "second", "third", "fourth", "fifth"
    };
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrayadapter);
        setTitle("ArrayAdapter示例");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strings);

        listView = (ListView)findViewById(R.id.array_adapter);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

    }
}
