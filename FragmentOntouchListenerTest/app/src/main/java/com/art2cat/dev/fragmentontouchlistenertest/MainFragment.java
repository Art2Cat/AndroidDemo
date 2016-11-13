package com.art2cat.dev.fragmentontouchlistenertest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnTouchListener {

    private static final String TAG = "MainFragment";
    MainActivity.MyOnTouchListener myOnTouchListener;
    private TextView mTextView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTextView = (TextView) view.findViewById(R.id.show);

//        myOnTouchListener = new MainActivity.MyOnTouchListener() {
//
//            @Override
//            public boolean onTouch(MotionEvent ev) {
//                //boolean result = mGestureDetector.onTouchEvent(ev);
//                Log.d(TAG, "onTouch: ");
//                if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//                    Toast.makeText(getActivity(), "you finger move on the screen :-)", Toast.LENGTH_SHORT).show();
//                } else if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//                    Toast.makeText(getActivity(), "you touch the screen :-)", Toast.LENGTH_SHORT).show();
//                }
//                return false;
//            }
//        };
//        ((MainActivity) getActivity()).registerMyOnTouchListener(myOnTouchListener);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            Toast.makeText(getActivity(), "you finger move on the screen :-)", Toast.LENGTH_SHORT).show();
        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(getActivity(), "you touch the screen :-)", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
