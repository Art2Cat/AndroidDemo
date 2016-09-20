package com.art2cat.dev.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by art2cat
 * on 9/14/16.
 */
public class NormalBroadcastReceiver extends BroadcastReceiver {
    private String receiverMsg;
    private static final String TAG = "NormalBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        receiverMsg = intent.getStringExtra("msg");
        Log.i(TAG, "onReceive: " + receiverMsg);
        Toast.makeText(context, receiverMsg, Toast.LENGTH_SHORT).show();
    }
}
