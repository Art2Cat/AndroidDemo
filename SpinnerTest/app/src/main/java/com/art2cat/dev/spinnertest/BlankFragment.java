package com.art2cat.dev.spinnertest;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private View view;
    private TextView textView;
    private Spinner spinner;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter simpleAdapter;


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);

        textView = (TextView) view.findViewById(R.id.textView1);
        textView.setText("你选择的城市是： 北京");
        //设置数据源
        dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("image", R.drawable.ic_action_name);
        maps.put("text", "北京");
        Map<String, Object> maps1 = new HashMap<String, Object>();
        maps1.put("image", R.drawable.ic_action_name);
        maps1.put("text", "上海");
        Map<String, Object> maps2 = new HashMap<String, Object>();
        maps2.put("image", R.drawable.ic_action_name);
        maps2.put("text", "广州");
        Map<String, Object> maps3 = new HashMap<String, Object>();
        maps3.put("image", R.drawable.ic_action_name);
        maps3.put("text", "深圳");
        dataList.add(maps);
        dataList.add(maps1);
        dataList.add(maps2);
        dataList.add(maps3);

        //初始化Spinner
        spinner = (Spinner) view.findViewById(R.id.spinner1);

        //SimpleAdapter
        simpleAdapter = new SimpleAdapter(getActivity(), dataList, R.layout.item,
                new String[] {"image", "text"}, new int[] {R.id.image, R.id.text});

        simpleAdapter.setDropDownViewResource(R.layout.item);
        spinner.setAdapter(simpleAdapter);

        spinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Map<String, Object> map = dataList.get(position);
        if (map.get("text") != null) {
            Log.i("Test", "" + map.get("text"));
            textView.setText("你选择的城市是： " + map.get("text"));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
