package com.art2cat.dev.animationfundamentaltest;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mAlphaXml;
    private Button mScaleXml;
    private Button mTranslateXml;
    private Button mRotateXml;
    private Button mAlphaJava;
    private Button mScaleJava;
    private Button mTranslateJava;
    private Button mRotateJava;
    private ImageView mImageView;
    private LinearLayout mActivityPropertyAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        setTitle("属性动画");
        initView();

    }

    private void initView() {
        mAlphaXml = (Button) findViewById(R.id.alpha_xml);
        mScaleXml = (Button) findViewById(R.id.scale_xml);
        mTranslateXml = (Button) findViewById(R.id.translate_xml);
        mRotateXml = (Button) findViewById(R.id.rotate_xml);
        mAlphaJava = (Button) findViewById(R.id.alpha_java);
        mScaleJava = (Button) findViewById(R.id.scale_java);
        mTranslateJava = (Button) findViewById(R.id.translate_java);
        mRotateJava = (Button) findViewById(R.id.rotate_java);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mActivityPropertyAnimation = (LinearLayout) findViewById(R.id.activity_property_animation);

        mAlphaXml.setOnClickListener(this);
        mScaleXml.setOnClickListener(this);
        mTranslateXml.setOnClickListener(this);
        mRotateXml.setOnClickListener(this);
        mAlphaJava.setOnClickListener(this);
        mScaleJava.setOnClickListener(this);
        mTranslateJava.setOnClickListener(this);
        mRotateJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha_xml:
                ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater
                        .loadAnimator(PropertyAnimationActivity.this, R.animator.animator_alpha);
                objectAnimator.setTarget(mImageView);
                objectAnimator.start();
                break;
            case R.id.scale_xml:

                break;
            case R.id.translate_xml:

                break;
            case R.id.rotate_xml:

                break;
            case R.id.alpha_java:

                break;
            case R.id.scale_java:

                break;
            case R.id.translate_java:

                break;
            case R.id.rotate_java:

                break;
        }
    }
}
