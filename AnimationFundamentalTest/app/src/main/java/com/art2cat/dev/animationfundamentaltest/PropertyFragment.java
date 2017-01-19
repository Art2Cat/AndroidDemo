package com.art2cat.dev.animationfundamentaltest;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.animation.AnimatorInflater.loadAnimator;
import static com.art2cat.dev.animationfundamentaltest.R.id.scale_xml;


/**
 * A simple {@link Fragment} subclass.
 */
public class PropertyFragment extends Fragment implements View.OnClickListener {


    private Button mAlphaXml;
    private Button mScaleXml;
    private Button mTranslateXml;
    private Button mRotateXml;
    private Button mAlphaJava;
    private Button mScaleJava;
    private Button mTranslateJava;
    private Button mRotateJava;
    private ImageView mImageView;

    public PropertyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_property, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mAlphaXml = (Button) view.findViewById(R.id.alpha_xml);
        mScaleXml = (Button) view.findViewById(scale_xml);
        mTranslateXml = (Button) view.findViewById(R.id.translate_xml);
        mRotateXml = (Button) view.findViewById(R.id.rotate_xml);
        mAlphaJava = (Button) view.findViewById(R.id.alpha_java);
        mScaleJava = (Button) view.findViewById(R.id.scale_java);
        mTranslateJava = (Button) view.findViewById(R.id.translate_java);
        mRotateJava = (Button) view.findViewById(R.id.rotate_java);
        Button custom = (Button) view.findViewById(R.id.custom);
        mImageView = (ImageView) view.findViewById(R.id.imageView);

        mAlphaXml.setOnClickListener(this);
        mScaleXml.setOnClickListener(this);
        mTranslateXml.setOnClickListener(this);
        mRotateXml.setOnClickListener(this);
        mAlphaJava.setOnClickListener(this);
        mScaleJava.setOnClickListener(this);
        mTranslateJava.setOnClickListener(this);
        mRotateJava.setOnClickListener(this);
        custom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int height = mImageView.getHeight();
        int width = mImageView.getWidth();
        switch (v.getId()) {
            case R.id.alpha_xml:
                ObjectAnimator objectAnimator = (ObjectAnimator)
                        loadAnimator(getActivity(), R.animator.animator_alpha);
                objectAnimator.setTarget(mImageView);
                objectAnimator.start();
                break;
            case scale_xml:
                AnimatorSet scale_xml = (AnimatorSet)
                        loadAnimator(getActivity(), R.animator.animator_scale);
                scale_xml.setTarget(mImageView);
                scale_xml.start();
                break;
            case R.id.translate_xml:
                AnimatorSet translate_xml = (AnimatorSet)
                        loadAnimator(getActivity(), R.animator.animator_translate);
                translate_xml.setTarget(mImageView);
                translate_xml.start();
                break;
            case R.id.rotate_xml:
                ObjectAnimator rotate_xml = (ObjectAnimator) AnimatorInflater.loadAnimator(getActivity(),
                        R.animator.animator_rotate);
                rotate_xml.setTarget(mImageView);
                rotate_xml.start();

                break;
            case R.id.alpha_java:
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.ALPHA, 1.0f, 0.1f).setDuration(1000);
                alpha.setRepeatCount(1);
                alpha.setRepeatMode(ValueAnimator.REVERSE);
                alpha.start();
                break;
            case R.id.scale_java:
                AnimatorSet animatorSet = new AnimatorSet();
                ObjectAnimator scaleX_java = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.SCALE_X, 1.0f, 2f).setDuration(1000);
                ObjectAnimator scaleY_java = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.SCALE_Y, 1.0f, 2f).setDuration(1000);
                scaleX_java.setRepeatCount(1);
                scaleX_java.setRepeatMode(ValueAnimator.REVERSE);
                scaleY_java.setRepeatCount(1);
                scaleY_java.setRepeatMode(ValueAnimator.REVERSE);
                animatorSet.playTogether(scaleX_java, scaleY_java);
                animatorSet.start();
                break;
            case R.id.translate_java:
                PropertyValuesHolder translateX = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_X, 0f, 100f);
                PropertyValuesHolder translateY = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_Y, 0f, 100f);
                ObjectAnimator translate_java =
                        ObjectAnimator.ofPropertyValuesHolder(mImageView, translateX, translateY)
                                .setDuration(1000);
                translate_java.setRepeatMode(ValueAnimator.REVERSE);
                translate_java.setRepeatCount(1);
                translate_java.start();

                break;
            case R.id.rotate_java:
                ObjectAnimator rotate = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.ROTATION, 0f, 360f).setDuration(2000);
                rotate.start();
                break;
            case R.id.custom:
                AnimatorSet animatorSet1 = new AnimatorSet();
                ObjectAnimator scaleX_java1 = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.SCALE_X, 1.0f, 2f).setDuration(1000);
                ObjectAnimator scaleY_java1 = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.SCALE_Y, 1.0f, 2f).setDuration(1000);
                scaleX_java1.setRepeatCount(12);
                scaleX_java1.setRepeatMode(ValueAnimator.REVERSE);
                scaleY_java1.setRepeatCount(12);
                scaleY_java1.setRepeatMode(ValueAnimator.REVERSE);
                ObjectAnimator rotate1 = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.ROTATION, 0f, 360f).setDuration(2000);
                rotate1.setRepeatCount(6);
                PropertyValuesHolder translate1X = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_X, 0f, 300f);
                PropertyValuesHolder translate1Y = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_Y, 0f, 300f);
                ObjectAnimator translate_java1 =
                        ObjectAnimator.ofPropertyValuesHolder(mImageView, translate1X, translate1Y)
                                .setDuration(1500);
                animatorSet1.play(rotate1).with(translate_java1).with(scaleX_java1).with(scaleY_java1);
                ObjectAnimator translate2Y = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.TRANSLATION_Y, 300f, -300f).setDuration(3000);
                animatorSet1.play(translate2Y).after(translate_java1);
                PropertyValuesHolder translate3X = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_X, 300f, -300f);
                PropertyValuesHolder translate3Y = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_Y, -300f, 300f);
                ObjectAnimator translate_java2 =
                        ObjectAnimator.ofPropertyValuesHolder(mImageView, translate3X, translate3Y)
                                .setDuration(3000);
                animatorSet1.play(translate_java2).after(translate2Y);
                ObjectAnimator translate4Y = ObjectAnimator.ofFloat(mImageView,
                        AnimatorConstants.TRANSLATION_Y, 300f, -300f).setDuration(3000);
                animatorSet1.play(translate4Y).after(translate_java2);
                PropertyValuesHolder translate5X = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_X, -300f, 0f);
                PropertyValuesHolder translate5Y = PropertyValuesHolder.ofFloat(
                        AnimatorConstants.TRANSLATION_Y, -300f, 0f);
                ObjectAnimator translate_java3 =
                        ObjectAnimator.ofPropertyValuesHolder(mImageView, translate5X, translate5Y)
                                .setDuration(1500);
                animatorSet1.play(translate_java3).after(translate4Y);
                animatorSet1.start();
                break;
        }
    }
}
