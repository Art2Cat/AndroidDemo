package com.art2cat.dev.spinnertest;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private View view;
    private TextView textView;
    private Spinner spinner;
    private List<String> dataList;
    private ArrayAdapter<String> arrayAdapter;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        //textView.setText("你选择的城市是： 北京");
        //设置数据源
        dataList = new ArrayList<String>();
        dataList.add("北京");
        dataList.add("上海");
        dataList.add("广州");
        dataList.add("深圳");

        //初始化Spinner
        spinner = (Spinner) view.findViewById(R.id.spinner);

        //新建ArrayAdapter
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataList);
        //设置下拉View的样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //spinner部署适配器
        spinner.setAdapter(arrayAdapter);

        //spinner设置事件itemSelect监听器
        spinner.setOnItemSelectedListener(this);


        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String city = arrayAdapter.getItem(position);

        textView.setText("你选择的城市是： " + city);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
