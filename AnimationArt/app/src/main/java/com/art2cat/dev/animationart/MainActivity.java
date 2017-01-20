package com.art2cat.dev.animationart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import drawable.MainFragment;

public class MainActivity extends AppCompatActivity {

    // 确保VectorDrawable 向下兼容使用于Button控件，还要配合selector xml配置文件
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_main);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.activity_main, new MainFragment())
                    .commit();
        }
    }
}
