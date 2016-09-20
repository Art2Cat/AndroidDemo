package com.art2cat.dev.progessbartest;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private View view;
    private ProgressBar progress;
    private ProgressBar progress1;
    private ProgressBar progress2;
    private ProgressBar progress3;
    private Button add;
    private Button reduce;
    private Button reset;
    private String content;
    private ProgressDialog progressDialog;
    private Button show;


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        init();
        // 启用窗口特征，启用带进度和不带进度的进度条
        //progress1.requestFitSystemWindows();
        //progress2.requestWindowFeature();

        // 显示两种进度条
        //setProgressBarVisibility(true);
        //setProgressBarIndeterminateVisibility(false);
        // Max=10000
        //setProgress(9999);

        return view;
    }

    private void init() {
        progress = (ProgressBar) view.findViewById(R.id.horiz);
        progress1 = (ProgressBar) view.findViewById(R.id.progressBar1);
        progress2 = (ProgressBar) view.findViewById(R.id.progressBar2);
        progress3 = (ProgressBar) view.findViewById(R.id.progressBar3);
        add = (Button) view.findViewById(R.id.add);
        reduce = (Button) view.findViewById(R.id.reduce);
        reset = (Button) view.findViewById(R.id.reset);
        show = (Button) view.findViewById(R.id.show);
        show.setOnClickListener(this);
        // getProgress()获取第一进度条的进度
        int first = progress.getProgress();
        // getSecondaryProgress()获取第二进度条的进度
        int second = progress.getSecondaryProgress();
        // getMax()获取进度条的最大进度
        int max = progress.getMax();
        content = ("第一进度百分比：" + (int) (first / (float) max * 100)
                + "%，第二进度百分比：" + (int) (second / (float) max * 100) + "%");
        //设置监听事件
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                // 增加第一进度和第二进度10个刻度
                progress.incrementProgressBy(10);
                progress.incrementSecondaryProgressBy(10);
                break;
            case R.id.reduce:
                // 减少第一进度和第二进度10个刻度
                progress.incrementProgressBy(-10);
                progress.incrementSecondaryProgressBy(-10);
                break;
            case R.id.reset:
                progress.setProgress(50);
                progress.setSecondaryProgress(80);
                break;
            case R.id.show:
                showProgressDialog();
                break;
        }
        content = "第一进度百分比：" + (int) (progress.getProgress() / (float) progress.getMax() * 100)+
                "%，第二进度百分比：" + (int) (progress.getSecondaryProgress()/ (float) progress.getMax() * 100) + "%";
        Snackbar.make(v, content, Snackbar.LENGTH_LONG).show();
    }
    
    private void showProgressDialog() {
        /**
         * 设置页面风格
         */
        //新建ProgressDialog对象
        progressDialog =new ProgressDialog(getActivity());
        //设置显示风格
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //设置标题
        progressDialog.setTitle("ProgressDialog");
        //设置对话框里的文字信息
        progressDialog.setMessage("进度正在加载：");
        //设置图标
        progressDialog.setIcon(android.R.drawable.ic_dialog_alert);

        /**
         * 设定关于ProgressBar的相关属性
         */
        //设置最大进度
        progressDialog.setMax(100);
        //设置初始化已经增加到的进度
        progressDialog.incrementProgressBy(50);
        //进度条是显示精度进度的
        progressDialog.setIndeterminate(false);

        /**
         * 设置一个确定按钮
         */
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar.make(view,"进度条加载完毕！", Snackbar.LENGTH_LONG).show();
            }
        });

        //是否可以通过返回按钮退出对话框
        progressDialog.setCancelable(true);
        //显示ProgressDialog
        progressDialog.show();

    }


}
