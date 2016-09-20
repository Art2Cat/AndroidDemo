package com.art2cat.dev.asynctaskandhandler;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment implements View.OnClickListener{

    private View view;
    private Button mSendM;
    private Button mSendT;
    private TextView textView;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.arg1 == 1) {
                textView.setText(msg.obj + "\n" + "arg1=" + msg.arg1 + "\n" + "arg2=" + msg.arg2);
            } else if (msg.arg1 == 2) {
                Bundle bundle1 = msg.getData();
                String text1 = bundle1.getString("text");
                textView.setText(text1 + "\n" + "{" + msg.obj + "}");
            }

            super.handleMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessage_btn:
                new Thread(){
                    @Override
                    public void run() {
                        //创建一个Message对象
                        Message msg = new Message();
                        //创建一个Bundle对象
                        Bundle bundle = new Bundle();
                        //在bundle中存放信息
                        bundle.putString("text", "在子线程中得到信息然后调用sendMessage()方法发送消息至主线程，" +
                                "通过回调Handler.handleMessage方法获取消息，最后更新textview的内容");
                        //将bundle存放入Message中
                        msg.setData(bundle);
                        msg.arg1 = 2;
                        //创建一个Object对象
                        Person person = new Person();
                        person.name = "pig";
                        person.age = 10;
                        //在Message 中存放一个Object对象
                        msg.obj = person;
                        //Handler.sendMessage()发送消息
                        mHandler.sendMessage(msg);
                        Log.i("Fragment2", "thread: " + Thread.currentThread());
                    }
                }.start();
                break;
            case R.id.sendToTarget_btn:
                new Thread(){
                    @Override
                    public void run() {
                        //使用obtainMessage()方法，同样可以获取Message对象
                        Message msg = mHandler.obtainMessage();
                        msg.arg1 = 1;
                        msg.arg2 = 2;
                        Person person = new Person();
                        person.name = "pig";
                        person.age = 10;
                        msg.obj = person;
                        //这里调用Message.sendToTarget()发送消息，Target通常都是Handler本身
                        msg.sendToTarget();
                        Log.i("Fragment2", "thread: " + Thread.currentThread());
                    }
                }.start();
                break;
        }
    }

    private  class Person{
        String name;
        int age;

        @Override
        public String toString() {
            return "name="+name+" age="+age;
        }
    }

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_2, null);
        textView = (TextView) view.findViewById(R.id.display);
        mSendM = (Button) view.findViewById(R.id.sendMessage_btn);

        mSendT = (Button) view.findViewById(R.id.sendToTarget_btn);

        mSendM.setOnClickListener(this);
        mSendT.setOnClickListener(this);

        return view;
    }

}
