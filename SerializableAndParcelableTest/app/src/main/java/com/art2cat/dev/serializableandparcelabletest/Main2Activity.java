package com.art2cat.dev.serializableandparcelabletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView textView = (TextView) findViewById(R.id.textView);
        SerializableBean serializableBean = (SerializableBean) getIntent().getSerializableExtra("serializable");
        ParcelableBean parcelableBean = getIntent().getParcelableExtra("parcelable");

        if (serializableBean!= null) {
            textView.setText(serializableBean.getMessage());
        }
        if (parcelableBean != null) {
            textView.setText(parcelableBean.getMessage());
        }
    }
}
