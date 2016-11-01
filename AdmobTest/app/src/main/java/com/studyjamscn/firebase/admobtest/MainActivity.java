package com.studyjamscn.firebase.admobtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AdView mBannerAdView;
    private NativeExpressAdView mNativeExpressAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button banner = (Button) findViewById(R.id.ban_btn);
        Button interstitial = (Button) findViewById(R.id.interstitial_btn);
        Button native1 = (Button) findViewById(R.id.native_btn);
        banner.setOnClickListener(this);
        interstitial.setOnClickListener(this);
        native1.setOnClickListener(this);

        mBannerAdView = (AdView) findViewById(R.id.banner_adView);
        mNativeExpressAdView = (NativeExpressAdView) findViewById(R.id.native_adView);

        loadInterstitialAd();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ban_btn:
                mBannerAdView.setVisibility(View.VISIBLE);
                mNativeExpressAdView.setVisibility(View.GONE);
                displayBannerAd();
                break;
            case R.id.interstitial_btn:
                mBannerAdView.setVisibility(View.GONE);
                mNativeExpressAdView.setVisibility(View.GONE);
                displayInterstitialAd();
                break;
            case R.id.native_btn:
                mNativeExpressAdView.setVisibility(View.VISIBLE);
                mBannerAdView.setVisibility(View.GONE);
                displayNativeAd();
                break;
        }
    }

    private void displayBannerAd() {
        // 初始化Mobile Ads SDK.
        MobileAds.initialize(this, getString(R.string.ban_unit_id));

        // 创建一个广告请求
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // 在AVD测试广告显示加入
                .addTestDevice("0ACA1878D607E6C4360F91E0A0379C2F")
                // 真机测试的话，运行app然后在logcat中搜索“addTestDevice”，找到手机的device id
                .build();

        // 后台加载广告
        mBannerAdView.loadAd(adRequest);
    }

    private void loadInterstitialAd() {
        // 创建InterstitialAd
        mInterstitialAd = new InterstitialAd(this);
        // 设置ad unit id
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_unit_id));
        // 请求新的广告
        requestNewAd();

        // 设置广告监听器，当用户关闭了广告，后台请求新的广告
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                requestNewAd();
            }
        });
    }

    private void requestNewAd() {
        // 创建一个广告请求
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("0ACA1878D607E6C4360F91E0A0379C2F")
                .build();

        // 加载广告
        mInterstitialAd.loadAd(adRequest);
    }

    private void displayInterstitialAd() {
        // 如果广告不为空，且加载完毕， 则显示广告
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }


    private void displayNativeAd() {
        // 创建一个广告请求
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR) // 在AVD测试广告显示加入
                .addTestDevice("0ACA1878D607E6C4360F91E0A0379C2F")
                // 真机测试的话，运行app然后在logcat中搜索“addTestDevice”，找到手机的device id
                .build();
        // 后台加载广告
        mNativeExpressAdView.loadAd(adRequest);
    }

}

