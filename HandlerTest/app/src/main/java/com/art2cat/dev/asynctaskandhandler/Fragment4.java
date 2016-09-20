package com.art2cat.dev.asynctaskandhandler;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {

    private View mView;
    private TextView mTextView;
    private Handler.Callback trueCallback;
    private Handler.Callback falseCallback;

    //向Handler中传入一个参数为CallBack的对象，然后重载一个返回值为boolean的handleMessage（）的方法
    //如果返回值为false，将先执行这个方法，再执行Handler中void的handleMessage（）方法
    //如果返回值为true,只执行这个方法
    private Handler mHandler;

    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_4, null);

        trueCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Toast.makeText(getActivity(), "截获消息，Callback中handleMessage方法", Toast.LENGTH_SHORT).show();
                Snackbar.make(mView, "这是只执行了Callback中handleMessage方法", Snackbar.LENGTH_SHORT).show();
                return true;
            }
        };

        falseCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Toast.makeText(getActivity(), "截获消息，Callback中handleMessage方法", Toast.LENGTH_SHORT).show();
                return false;
            }
        };

        mTextView = (TextView) mView.findViewById(R.id.tv);
        Button btn = (Button) mView.findViewById(R.id.btn);
        Button btn1 = (Button) mView.findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler = new Handler(falseCallback) {
                    @Override
                    public void handleMessage(Message msg) {
                        Toast.makeText(getActivity(), "然而并没有截获消息，这是Handler中handleMessage方法", Toast.LENGTH_LONG).show();
                        Snackbar.make(mView, "这是执行两种handleMessage方法", Snackbar.LENGTH_LONG).show();
                        super.handleMessage(msg);
                    }
                };
                mHandler.sendEmptyMessage(0);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler = new Handler(trueCallback) {
                    @Override
                    public void handleMessage(Message msg) {
                        Toast.makeText(getActivity(), "我只是在这里卖个萌，并不会显示喵～", Toast.LENGTH_LONG).show();
                        super.handleMessage(msg);
                    }
                };
                mHandler.sendEmptyMessage(0);
            }
        });
        return mView;
    }

}
