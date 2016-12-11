package com.art2cat.dev.serializableandparcelabletest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button serializable = (Button) findViewById(R.id.button2);
        final Button parcelable = (Button) findViewById(R.id.button);

        serializable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SerializableBean serializableBean = new SerializableBean();
                serializableBean.setMessage("Send via Serializable");
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("serializable", serializableBean);
                startActivity(intent);
            }
        });

        parcelable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParcelableBean parcelableBean = new ParcelableBean(Parcel.obtain());
                parcelableBean.setMessage("Send via Parcelable");
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("parcelable", parcelableBean);
                startActivity(intent);
            }
        });
    }
}
