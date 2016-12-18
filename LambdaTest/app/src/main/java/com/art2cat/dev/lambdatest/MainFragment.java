package com.art2cat.dev.lambdatest;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    private OnTextChangeListener mListener;

    public interface OnTextChangeListener {
        void onChange(String text);
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTextChangeListener) {
            mListener = (OnTextChangeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainFragment.OnTextChangeListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1 = inflater.inflate(R.layout.fragment_main, container, false);

        TextView textView = (TextView) view1.findViewById(R.id.textview);
        Button button = (Button) view1.findViewById(R.id.button);

        button.setOnClickListener(view -> {
            mListener.onChange(textView.getText().toString());
        });
        return view1;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
