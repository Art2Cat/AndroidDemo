package com.art2cat.dev.asynctaskandhandler;


import android.content.Intent;
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
public class MainFragment extends Fragment implements View.OnClickListener{

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, null);
        Button button1 = (Button) view.findViewById(R.id.btn1);
        Button button2 = (Button) view.findViewById(R.id.btn2);
        Button button3 = (Button) view.findViewById(R.id.btn3);
        Button button4 = (Button) view.findViewById(R.id.btn4);
        Button button5 = (Button) view.findViewById(R.id.btn5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("flag", 1);
                startActivity(intent);
                break;
            case R.id.btn2:
                Intent intent2 = new Intent(getActivity(), SecondActivity.class);
                intent2.putExtra("flag", 2);
                startActivity(intent2);
                break;

            case R.id.btn3:
                Intent intent3 = new Intent(getActivity(), SecondActivity.class);
                intent3.putExtra("flag", 3);
                startActivity(intent3);
                break;

            case R.id.btn4:
                Intent intent4 = new Intent(getActivity(), SecondActivity.class);
                intent4.putExtra("flag", 4);
                startActivity(intent4);
                break;
            case R.id.btn5:
                Intent intent5 = new Intent(getActivity(), SecondActivity.class);
                intent5.putExtra("flag", 5);
                startActivity(intent5);
                break;
        }
    }
}
