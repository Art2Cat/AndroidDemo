package com.art2cat.dev.animationfundamentaltest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LayoutAnimationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_animation);
        setTitle("布局动画");
        initView();
    }

    private void initView() {
        ListView listView = (ListView) findViewById(R.id.activity_layout_animation);

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add("我是第： " + i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LayoutAnimationActivity.this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);


        Animation rotate = new RotateAnimation(0f, 90f);
        rotate.setDuration(500);
        rotate.setRepeatMode(Animation.REVERSE);
        rotate.setRepeatCount(1);
        LayoutAnimationController controller = new LayoutAnimationController(rotate);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        listView.setLayoutAnimation(controller);
        listView.startLayoutAnimation();
    }
}
