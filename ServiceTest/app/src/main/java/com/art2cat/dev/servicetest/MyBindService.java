package com.art2cat.dev.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MyBindService extends Service {
    private static final String TAG = "MyBindService";
    public MyBindService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(TAG, "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return new MyBinder();
    }

    /**
     * 通过继承系统提供的Binder类，来返回Service对象，也可以用来传输Service中运行计算的结果
     */
    public class MyBinder extends Binder {
        //获取Service对象
        public MyBindService getService() {
            return MyBindService.this;
        }
    }

    public void play() {
        toastShow("播放");
    }

    public void pause() {
        toastShow("暂停");
    }

    public void previous() {
        toastShow("上一曲");
    }

    public void next() {
        toastShow("下一曲");
    }

    private void toastShow(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }
}
