package com.art2cat.dev.shortcutstest;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mIds;
    private List<ShortcutInfo> shortcutInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mIds = new ArrayList<String>();
        shortcutInfos = new ArrayList<ShortcutInfo>();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://art2cat.com"));
        for (int i = 0; i < 5; i++) {
            String id = "id" + i;
            String shortlabel = "short label " + i;
            String longlabel = "long label " + i;
            mIds.add(id);
            shortcutInfos.add(ShortcutsUtils.createShortcut(this, id, shortlabel,
                    longlabel, R.drawable.ic_action_test, intent));
        }
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                ShortcutsUtils.setShortcuts(MainActivity.this, shortcutInfos);
                break;
            case R.id.button2:
                ShortcutsUtils.deleteShortcuts(MainActivity.this, mIds);
                break;
            case R.id.button3:
                ShortcutsUtils.disableShortcuts(MainActivity.this, mIds);
                break;
            case R.id.button4:
                ShortcutsUtils.enableShortcuts(MainActivity.this, mIds);
                break;
        }
    }
}
