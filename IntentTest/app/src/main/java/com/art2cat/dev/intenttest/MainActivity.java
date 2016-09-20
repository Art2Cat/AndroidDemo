package com.art2cat.dev.intenttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button first = (Button) findViewById(R.id.button_first);
        Button second = (Button) findViewById(R.id.button_second);
        textView = (TextView) findViewById(R.id.textView);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //启动Activity并附上请求代码
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * 通过startActivityForResult启动，接收数据的方法
     * @param requestCode 请求标识
     * @param resultCode 返回结果标识
     * @param data 由第二个页面回传的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //当请求标识与结果标识相同时，接收返回的数据
        if (requestCode == 1 && resultCode == 2 ) {
            String datas = data.getStringExtra("data");

            textView.setText(datas);
        }
    }
}
