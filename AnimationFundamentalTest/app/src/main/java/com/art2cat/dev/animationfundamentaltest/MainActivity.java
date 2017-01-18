package com.art2cat.dev.animationfundamentaltest;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAlphaXml;
    private Button mScaleXml;
    private Button mTranslateXml;
    private Button mRotateXml;
    private Button mAlphaJava;
    private Button mScaleJava;
    private Button mTranslateJava;
    private Button mRotateJava;
    private Button mLayoutAnimation;
    private Button mFrameAnimation;
    private Button mPropertyAnimation;
    private ImageView mImageView;
    private ScrollView mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        mLayoutAnimation = (Button) findViewById(R.id.layout_animation);
        mFrameAnimation = (Button) findViewById(R.id.frame_animation);
        mPropertyAnimation = (Button) findViewById(R.id.property_animation);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mActivityMain = (ScrollView) findViewById(R.id.activity_main);

        mAlphaXml.setOnClickListener(this);
        mScaleXml.setOnClickListener(this);
        mTranslateXml.setOnClickListener(this);
        mRotateXml.setOnClickListener(this);
        mAlphaJava.setOnClickListener(this);
        mScaleJava.setOnClickListener(this);
        mTranslateJava.setOnClickListener(this);
        mRotateJava.setOnClickListener(this);
        mLayoutAnimation.setOnClickListener(this);
        mFrameAnimation.setOnClickListener(this);
        mPropertyAnimation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float width = mImageView.getWidth();
        float height = mImageView.getHeight();
        switch (v.getId()) {
            case R.id.alpha_xml:
                loadLocalAnimation(mImageView, R.anim.anim_alpha);
                break;
            case R.id.scale_xml:
                loadLocalAnimation(mImageView, R.anim.anim_scale);
                break;
            case R.id.translate_xml:
                loadLocalAnimation(mImageView, R.anim.anim_translate);
                break;
            case R.id.rotate_xml:
                loadLocalAnimation(mImageView, R.anim.anim_rotate);
                break;
            case R.id.alpha_java:
                Animation alpha = new AlphaAnimation(1.0f, 0.0f);
                alpha.setDuration(1000);
                alpha.setRepeatCount(1);
                alpha.setRepeatMode(Animation.REVERSE);
                mImageView.startAnimation(alpha);
                break;
            case R.id.scale_java:
                Animation scale = new ScaleAnimation(1.0f, 0f, 1.0f, 0f,
                        width / 2, height / 2);
                scale.setDuration(2000);
                scale.setRepeatCount(1);
                scale.setRepeatMode(Animation.REVERSE);
                mImageView.startAnimation(scale);
                break;
            case R.id.translate_java:

                Animation translate = new TranslateAnimation(0f, 200f, 0f, 0f);
                translate.setDuration(2000);
                translate.setRepeatCount(1);
                translate.setRepeatMode(Animation.REVERSE);
                mImageView.startAnimation(translate);
                break;
            case R.id.rotate_java:
                Animation rotate = new RotateAnimation(0f, 360f, width / 2, height / 2);
                rotate.setDuration(2000);
                mImageView.startAnimation(rotate);
                break;
            case R.id.layout_animation:
                startActivity(new Intent(MainActivity.this, LayoutAnimationActivity.class));
                break;
            case R.id.frame_animation:
                mImageView.setImageResource(0);
                mImageView.setBackgroundResource(R.drawable.frame_animation_list);
                AnimationDrawable animation = (AnimationDrawable) mImageView.getBackground();
                if (animation == null) {
                    return;
                }
                animation.start();
                break;
            case R.id.property_animation:

                break;
        }
    }

    private void loadLocalAnimation(View view, int animDrawable) {
        Animation animation = AnimationUtils.loadAnimation(this, animDrawable);
        view.startAnimation(animation);
    }
}
