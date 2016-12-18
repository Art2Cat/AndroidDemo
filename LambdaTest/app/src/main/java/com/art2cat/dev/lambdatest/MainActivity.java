package com.art2cat.dev.lambdatest;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainFragment.OnTextChangeListener{
private FrameLayout mFrameLayout;
    private String string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = (FrameLayout) findViewById(R.id.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_main);
        if (fragment == null) {
            fragmentManager.beginTransaction()
                    .add(R.id.activity_main, new MainFragment())
                    .commit();
        }

    }


    @Override
    public void onChange(String text) {
        Snackbar.make(mFrameLayout, text, Snackbar.LENGTH_LONG).show();
        Handler handler = new Handler();
        Runnable runnable = ()-> {
            receive((a)-> {
                Toast.makeText(this, a, Toast.LENGTH_LONG).show();
            });
        };
        handler.postDelayed(runnable, 1000);
    }

    private void receive(MainFragment.OnTextChangeListener onTextChangeListener) {
        onTextChangeListener.onChange("hello");
    }
}
