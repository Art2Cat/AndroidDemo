package com.art2cat.dev.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                首先从edittext中获取需要发送信息，
                新建Intent，添加Extra信息，通过setResult发送
                 */
                String data = editText.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("data", data);
                setResult(2, intent);

                //完成当前Activity，销毁
                finish();
            }
        });
    }
}
