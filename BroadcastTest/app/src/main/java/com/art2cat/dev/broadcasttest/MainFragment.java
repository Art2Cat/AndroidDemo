package com.art2cat.dev.broadcasttest;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button sendNormal;
    private Button sendOrdered;
    private Button sendAsync;
    private TextView display;
    private NormalBroadcastReceiver2 nbr;
    private AsyncBroadcastReceiver abr;
    private static final String BROADCAST_FILTER = "com.art2cat.dev.broadcasttest.broadcast";
    private static final String ORDERED_BROADCAST_FILTER = "com.art2cat.dev.broadcasttest.orderedbroadcast";
    private static final String ASYNC_BROADCAST_FILTER = "com.art2cat.dev.broadcasttest.asyncbroadcast";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        sendNormal = (Button) view.findViewById(R.id.send_normal_broadcast_btn);
        sendOrdered = (Button) view.findViewById(R.id.send_ordered_broadcast_btn);
        sendAsync = (Button) view.findViewById(R.id.send_async_broadcast_btn);
        display = (TextView) view.findViewById(R.id.receiver_tv);
        sendNormal.setOnClickListener(this);
        sendOrdered.setOnClickListener(this);
        sendAsync.setOnClickListener(this);
        dynamicRegister();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_normal_broadcast_btn:
                /**
                 * 普通广播特点：
                 * 同级别接受先后是随机（无序）
                 * 级别低的后收到广播
                 * 接收器不能截断传播也不能处理广播
                 * 同级别动态注册高于静态注册
                 */
                Intent intent = new Intent();
                intent.putExtra("msg", "这是一条普通广播！");
                intent.setAction(BROADCAST_FILTER);
                //context.sendBroadcast()方法 发送广播
                getActivity().sendBroadcast(intent);
                break;
            case R.id.send_ordered_broadcast_btn:
                /**
                 * 有序广播特点：
                 * 同级别接收顺序是随机的
                 * 能截断广播的继续传播，高级别的广播接收器收到该广播后，可以决定把该广播是否截断
                 * 接收器能截断广播的继续传播，也不能处理广播
                 * 同级别动态注册高于静态注册
                 */
                Intent intent1 = new Intent();
                intent1.putExtra("msg", "这是一条有序广播！");
                intent1.setAction(ORDERED_BROADCAST_FILTER);
                getActivity().sendOrderedBroadcast(intent1, null);
                break;
            case R.id.send_async_broadcast_btn:
                /**
                 * 异步广播很少用到，现在官方已经做废弃处理
                 * 发送异步广播还需在manifest中启动android.permission.BROADCAST_STICKY的权限
                 */
                Intent intent2 = new Intent();
                intent2.putExtra("msg", "这是一条异步广播！");
                intent2.setAction(ASYNC_BROADCAST_FILTER);
                getActivity().sendStickyBroadcast(intent2);

                //首先创建intent过滤器
                IntentFilter intentFilter = new IntentFilter(ASYNC_BROADCAST_FILTER);
                //然后新建BroadcastReceiver对象
                abr = new AsyncBroadcastReceiver();
                //最后调用context.registerReceiver()方法注册
                getActivity().registerReceiver(abr, intentFilter);
                break;

        }
    }

    /**
     * 动态注册广播
     */
    private void dynamicRegister() {
        //首先创建intent过滤器
        IntentFilter intentFilter = new IntentFilter(BROADCAST_FILTER);
        //然后新建BroadcastReceiver对象
        nbr = new NormalBroadcastReceiver2();
        //最后调用context.registerReceiver()方法注册
        getActivity().registerReceiver(nbr, intentFilter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //动态注册广播接收器，当Activity或者Fragment生命周期结束的时候要对其进行取消注册
        if (nbr != null) {
            getActivity().unregisterReceiver(nbr);
        }

        if (abr != null) {
            getActivity().unregisterReceiver(abr);
        }
    }
}
