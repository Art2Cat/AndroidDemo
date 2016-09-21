package com.art2cat.dev.ottotest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    private TextView mTextView;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        BusProvider.getInstance().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, null);
        mTextView = (TextView) view.findViewById(R.id.textView);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 dialogFragment = new Fragment2();
                dialogFragment.show(getFragmentManager(), "ddd");
            }
        });
        return view;
    }

    //这个注解一定要有,表示订阅了TestAction,并且方法的用 public 修饰的.方法名可以随意取,重点是参数,它是根据你的参数进行判断
    @Subscribe
    public void testAction(BustReceiver testAction) {
        //这里更新视图或者后台操作,从TestAction获取传递参数.
        if (testAction.getStr() != null){
            mTextView.setText(testAction.getStr());
        }
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

}
