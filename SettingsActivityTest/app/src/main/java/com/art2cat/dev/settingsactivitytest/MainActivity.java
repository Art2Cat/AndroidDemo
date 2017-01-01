package com.art2cat.dev.settingsactivitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSettingsButton;
    private TextView mDisplay;
    private LinearLayout mActivityMain;
    private Button mGetSettingsDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mSettingsButton = (Button) findViewById(R.id.settings_button);
        mGetSettingsDataButton = (Button) findViewById(R.id.get_settings_data_button);
        mDisplay = (TextView) findViewById(R.id.display);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);

        mSettingsButton.setOnClickListener(this);
        mGetSettingsDataButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_button:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.get_settings_data_button:
                break;
        }
    }
}
