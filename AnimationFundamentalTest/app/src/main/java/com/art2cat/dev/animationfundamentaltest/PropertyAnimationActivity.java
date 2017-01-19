package com.art2cat.dev.animationfundamentaltest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class PropertyAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        setTitle("属性动画");

        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentById(R.id.activity_layout_animation);
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.activity_property_animation, new PropertyFragment())
                    .commit();
        }
    }


}
