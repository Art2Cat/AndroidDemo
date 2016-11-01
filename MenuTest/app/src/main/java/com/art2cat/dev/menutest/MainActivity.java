package com.art2cat.dev.menutest;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private View view;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.activity_main, null);
        setContentView(view);
        textView = (TextView) findViewById(R.id.display);
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopMenu(v);
            }
        });
    }

    private void showPopMenu(View v) {
        //新建PopupMenu对像
        PopupMenu popupMenu = new PopupMenu(this, v);
        //生成Menu视图
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        //setOnMenuItemClickListener
        popupMenu.setOnMenuItemClickListener(this);
        //显示PopupMenu
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Snackbar.make(view, "you chose item1", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Snackbar.make(view, "you chose item2", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Snackbar.make(view, "you chose item3", Snackbar.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
