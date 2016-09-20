package com.art2cat.dev.asynctaskandhandler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        int mFlag = getIntent().getIntExtra("flag", 0);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.second_container);
        if (fragment == null) {
            if (mFlag != 0) {
                switch (mFlag) {
                    case 1:
                        fragment = new Fragment1();
                        fm.beginTransaction().add(R.id.second_container, fragment).commit();
                        break;
                    case 2:
                        fragment = new Fragment2();
                        fm.beginTransaction().add(R.id.second_container, fragment).commit();
                        break;
                    case 3:
                        fragment = new Fragment3();
                        fm.beginTransaction().add(R.id.second_container, fragment).commit();
                        break;
                    case 4:
                        fragment = new Fragment4();
                        fm.beginTransaction().add(R.id.second_container, fragment).commit();
                        break;
                    case 5:
                        fragment = new Fragment5();
                        fm.beginTransaction().add(R.id.second_container, fragment).commit();
                        break;
                }
            }
        }
    }
}
