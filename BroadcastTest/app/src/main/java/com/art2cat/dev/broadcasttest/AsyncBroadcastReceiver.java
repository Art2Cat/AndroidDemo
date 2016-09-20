package com.art2cat.dev.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by art2cat
 * on 9/14/16.
 */
public class AsyncBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "AsyncBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        Log.i(TAG, "onReceive: " + s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        //使用abortBroadcast()截断广播
        abortBroadcast();
    }
}
