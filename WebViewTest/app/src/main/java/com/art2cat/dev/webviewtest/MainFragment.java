package com.art2cat.dev.webviewtest;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MainFragment extends Fragment {
    private View view;
    private String url = "http://3g.163.com/";
    private WebView webView;
    private ProgressDialog dialog;
    private ProgressBar progressBar;
    private static final String TAG = "MainFragment";

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        init();
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        return view;
    }
    private void init() {
        webView = (WebView) view.findViewById(R.id.webView);
        // WebView加载本地资源
        // webView.loadUrl("file:///android_asset/example.html");
        // WebView加载web资源
        webView.loadUrl(url);
        // 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器去打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知

        });
        //启用支持JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress 1-100之间的整数
                if(newProgress==100) {
                    //网页加载完毕，关闭ProgressDialog
                    //closeDialog();
                    progressBar.setVisibility(View.INVISIBLE);
                }
                else {
                    //网页正在加载,打开ProgressDialog
                    //openDialog(newProgress);
                    displayProgress(newProgress);
                }
            }

            private void closeDialog() {
                if(dialog!=null&&dialog.isShowing())
                {
                    dialog.dismiss();
                    dialog=null;
                }
            }

            private void openDialog(int newProgress) {
                if(dialog==null)
                {
                    dialog=new ProgressDialog(getActivity());
                    dialog.setTitle("正在加载");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();

                }
                else
                {
                    dialog.setProgress(newProgress);
                }

            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
        //当webView不为null时，监听back按钮
        if (webView != null) {
            webView.setFocusableInTouchMode(true);
            //请求焦点
            webView.requestFocus();
            //设置按键监听器
            webView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    //检查事件是否等于用户从屏幕上移开某一点对应的手指与用户是否按了back键
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                        // handle back button 当webView可以返回时，webView返回前一页
                        if (webView.canGoBack()) {
                            webView.goBack();
                        }
                        Log.d(TAG, "onKey: true");
                        return true;
                    }
                    Log.d(TAG, "onKey: false");
                    return false;
                }
            });
        }

    }

    private void displayProgress(int newProgress) {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(newProgress);
    }

}
