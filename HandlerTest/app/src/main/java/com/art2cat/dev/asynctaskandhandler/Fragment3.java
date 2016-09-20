package com.art2cat.dev.asynctaskandhandler;


import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";
    private TextView mTextView;
    private Button button;
    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getActivity(), "这是主线程：" + Thread.currentThread(), Toast.LENGTH_SHORT).show();
            Log.d(TAG, "这是主线程：" + Thread.currentThread());
            //向子线程发送消息
            mSubHandler.sendEmptyMessageDelayed(0, 1000);
            super.handleMessage(msg);
        }
    };

    private Handler mSubHandler;

    private HandlerThread handlerThread;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, null);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mTextView.setText("HandlerThread,用于解决多线程并中空指针的问题，以实现异步操作，" +
                "这里使用Handler演示主线程与子线程之间交互发送消息");
        mTextView.setTextSize(20);
        button = (Button) view.findViewById(R.id.start);

        //创建一个HandlerThread对象
        handlerThread = new HandlerThread("handlerThread");
        //启动HandlerThread
        handlerThread.start();

        //创建一个作用于子线程的Handler
        mSubHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(getActivity(), "这是子线程：" + Thread.currentThread(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "这是子线程：" + Thread.currentThread());
                //向主线程发送消息
                mMainHandler.sendEmptyMessageDelayed(0, 1000);
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向子线程发送消息
                mSubHandler.sendEmptyMessageDelayed(0, 1000);
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        //防止应程序报错IllegalStateException，调用quitSafely(),停止子线程
        handlerThread.quitSafely();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
