package com.art2cat.dev.servicetest;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.display.DisplayManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private View view;
    private Intent intent;
    private Intent intent2;
    private ServiceConnection conn;
    private MyBindService myBindService;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取LayoutInflater的常规方法,context.getLayoutInflater()
        LayoutInflater inflater = getLayoutInflater();
        //获取LayoutInflater方法二，通过获取系统服务context.getSystemService(LAYOUT_INFLATER_SERVICE)
        LayoutInflater inflater1 = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater1.inflate(R.layout.activity_main, null);
        setContentView(view);

        Button start = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        Button bind = (Button) findViewById(R.id.bind);
        Button unbind = (Button) findViewById(R.id.unbind);
        Button play = (Button) findViewById(R.id.play);
        Button pause = (Button) findViewById(R.id.pause);
        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);
        Button network = (Button) findViewById(R.id.network_state_btn);
        Button battery = (Button) findViewById(R.id.battery_state_btn);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        network.setOnClickListener(this);
        battery.setOnClickListener(this);

        conn = new ServiceConnection() {
            //当启动源跟Service成功连接之后将会自动调用这个方法
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBindService = ((MyBindService.MyBinder) service).getService();
            }

            //当启动源跟Service的连接意外丢失的时候会调用这个方法
            //比如当Service崩溃了或者被强行杀死了
            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);
        }
        if (myBindService != null) {
            unbindService(conn);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start:
                //启动service 调用startService方法
                intent = new Intent(MainActivity.this, MyStartService.class);
                startService(intent);
                displaySnackBar("Start Service");
                break;
            case R.id.stop:
                //当intent不为空时停止service
                if (intent != null) {
                    stopService(intent);
                    displaySnackBar("Stop Service");
                }
                break;
            case R.id.bind:
                //使用bindService绑定service，与activity交互需要添加ServiceConnection参数
                intent2 = new Intent(MainActivity.this, MyBindService.class);
                bindService(intent2, conn, Service.BIND_AUTO_CREATE);
                displaySnackBar("Bind Service");
                break;
            case R.id.unbind:
                if (myBindService != null) {
                    unbindService(conn);
                    displaySnackBar("UnBind Service");
                    myBindService = null;
                }
                break;
            case R.id.play:
                if (myBindService != null) {
                    myBindService.play();
                } else {
                    toastShow();
                }
                break;
            case R.id.pause:
                if (myBindService != null) {
                    myBindService.pause();
                } else {
                    toastShow();
                }
                break;
            case R.id.previous:
                if (myBindService != null) {
                    myBindService.previous();
                } else {
                    toastShow();
                }
                break;
            case R.id.next:
                if (myBindService != null) {
                    myBindService.next();
                } else {
                    toastShow();
                }
                break;
            case R.id.network_state_btn:
                getNetworkState(MainActivity.this);
                break;
            case R.id.battery_state_btn:
                getBatteryInfo(MainActivity.this);
                break;
        }
    }

    private void getNetworkState(Context c) {
        if (c != null) {
            //获取网络管理器
            ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(CONNECTIVITY_SERVICE);
            //获取网络信息
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info == null) {
                displaySnackBar("网络未连接！ ");
            } else {
                //获取当前使用网络类型 getTypeName
                String netType = info.getTypeName();
                //获取子网络类型(2G,3G, LTE, etc）
                String subtype = info.getSubtypeName();
                if (subtype != null) {
                    displaySnackBar("网络已链接！ 网络类型： " + netType + "，数据类型： " + subtype);
                }
            }
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void getBatteryInfo(Context c) {
        if (c != null) {
            //获取电池管理器
            BatteryManager batteryManager = (BatteryManager) c.getSystemService(BATTERY_SERVICE);
            //检查电池是否充电
            boolean isCharging = batteryManager.isCharging();
            //获得当前电池容量
            int capacity = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            if (isCharging) {

                int usb = batteryManager.getIntProperty(BatteryManager.BATTERY_PLUGGED_USB);
                int ac = batteryManager.getIntProperty(BatteryManager.BATTERY_PLUGGED_AC);
                Log.i(TAG, "getBatteryInfo: " + usb);
                Log.i(TAG, "getBatteryInfo: " + ac);
                if (usb != 0) {
                    displaySnackBar("正在使用usb模式充电。。。" + "电池容量:" + capacity);
                } else if (ac != 0) {
                    displaySnackBar("正在使用ac模式充电。。。" + "电池容量:" + capacity);
                }
            } else {
                displaySnackBar("未充电， 电池容量: " + capacity);
            }
        }
    }

    private void toastShow() {
        Toast.makeText(getApplicationContext(), "Service未绑定！", Toast.LENGTH_SHORT).show();
    }

    private void displaySnackBar(String content) {
        Snackbar.make(view, content, Snackbar.LENGTH_SHORT).show();
    }
}
