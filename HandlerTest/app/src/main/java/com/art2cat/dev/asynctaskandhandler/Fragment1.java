package com.art2cat.dev.asynctaskandhandler;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";

    private TextView mTextView;
    private ImageView mImageView;

    //1.首先要新建一个handler对象
    private Handler mHandler = new Handler();

    private int[] mTexts = new int[]{R.string.heading_method1_content,
            R.string.heading_method1_content1, R.string.heading_method1_content2,
            R.string.heading_method1_content3, R.string.heading_method1_content4};

    private int[] mImages = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5};
    private int index = 0;

    private MyRunnable myRunnable;

    //2.然后新建一个类继承Runnable，在run()方法中操作更新UI
    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            index = index % 5;
            Log.d(TAG, "run: " + index);
            mTextView.setText(mTexts[index]);
            mImageView.setBackgroundResource(mImages[index]);
            index++;
            mHandler.postDelayed(myRunnable, 2000);
        }
    }

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        mTextView = (TextView) view.findViewById(R.id.display_textView);
        mImageView = (ImageView) view.findViewById(R.id.display);
        //3.建立自定义Runnable类的对象
        myRunnable = new MyRunnable();
        //4.调用handler.postDelayed()方法，传入Runnable参数和需要延长的时间
        mHandler.postDelayed(myRunnable, 2000);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
