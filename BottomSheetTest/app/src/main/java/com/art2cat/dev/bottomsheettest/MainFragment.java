package com.art2cat.dev.bottomsheettest;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private TextView mTextView;
    private Button mButton;
    private CoordinatorLayout mCoordinatorLayout;
    private BottomSheetBehavior bottomSheetBehavior;
    private static final String TAG = "MainFragment";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        mTextView = (TextView) mView.findViewById(R.id.display);
        mButton = (Button) mView.findViewById(R.id.show_button);
        mCoordinatorLayout = (CoordinatorLayout) mView.findViewById(R.id.cl);
        showBottomSheet();
        mButton.setOnClickListener(this);
        LinearLayoutCompat layoutCompat = (LinearLayoutCompat) mView.findViewById(R.id.bottom_sheet_selection_1);
        LinearLayoutCompat layoutCompat2 = (LinearLayoutCompat) mView.findViewById(R.id.bottom_sheet_selection_2);
        LinearLayoutCompat layoutCompat3 = (LinearLayoutCompat) mView.findViewById(R.id.bottom_sheet_selection_3);
        LinearLayoutCompat layoutCompat4 = (LinearLayoutCompat) mView.findViewById(R.id.bottom_sheet_selection_4);
        LinearLayoutCompat layoutCompat5 = (LinearLayoutCompat) mView.findViewById(R.id.bottom_sheet_selection_5);
        layoutCompat.setOnClickListener(this);
        layoutCompat2.setOnClickListener(this);
        layoutCompat3.setOnClickListener(this);
        layoutCompat4.setOnClickListener(this);
        layoutCompat5.setOnClickListener(this);
        return mView;
    }

    private void showBottomSheet() {
        View bottomSheet = mCoordinatorLayout.findViewById(R.id.design_bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变，根据slideOffset可以做一些动画
                //Log.i("cjj", "newState--->" + newState);
//                ViewCompat.setScaleX(bottomSheet,1);
//                ViewCompat.setScaleY(bottomSheet,1);
                if (newState == BottomSheetBehavior.STATE_SETTLING) {
                    Log.d(TAG, "onStateChanged: " + "STATE_SETTLING");
                } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    Log.d(TAG, "onStateChanged: " + "STATE_DRAGGING");
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    Log.d(TAG, "onStateChanged: " + "STATE_COLLAPSED");
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    Log.d(TAG, "onStateChanged: " + "STATE_EXPANDED");
                } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    Log.d(TAG, "onStateChanged: " + "STATE_HIDDEN");
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画

                //Log.i("cjj", "slideOffset=====》" + slideOffset);
//                ViewCompat.setScaleX(bottomSheet,slideOffset);
//                ViewCompat.setScaleY(bottomSheet,slideOffset);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.show_button:
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    Log.d(TAG, "onClick: " + "STATE_EXPANDED");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    Log.d(TAG, "onClick: " + "STATE_COLLAPSED");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.bottom_sheet_selection_1:
                mTextView.setText("你选择了测试1");
                break;
            case R.id.bottom_sheet_selection_2:
                mTextView.setText("你选择了测试2");
                break;
            case R.id.bottom_sheet_selection_3:
                mTextView.setText("你选择了测试3");
                break;
            case R.id.bottom_sheet_selection_4:
                mTextView.setText("你选择了测试4");
                break;
            case R.id.bottom_sheet_selection_5:
                mTextView.setText("你选择了测试5");
                break;
        }
    }
}
