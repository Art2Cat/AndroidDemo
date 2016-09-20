package com.art2cat.dev.asynctaskandhandler;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by art2cat
 * on 8/23/16.
 */
public class Fragment5  extends Fragment {
    private TextView mTextView;
    private ProgressBar progressBar;
    private Button show;
    private AsyncTaskTest task;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5, null);
        mTextView = (TextView) view.findViewById(R.id.tv);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        task = new AsyncTaskTest();

        show = (Button) view.findViewById(R.id.show_btn);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当task对象不为空，且没有被标记为Cancel
                if (task != null && !task.isCancelled()) {
                    task.execute();
                } else {
                    task.cancel(false);
                }
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //cancel 只是通知AsyncTask标记为cancel状态，并不是真正的取消了线程的执行
        if (task != null && task.getStatus() == AsyncTask.Status.RUNNING) {
            task.cancel(true);
        }
    }

    /**
     *
     */
    class AsyncTaskTest extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (isCancelled()) {
                return;
            }
            progressBar.setVisibility(View.VISIBLE);
            mTextView.setText(0 + "%");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 100; i++) {
                //当AsyncTask被标记为cancel，跳出循环
                if (isCancelled()) {
                    break;
                }
                //模拟进度条更新
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()) {
                return;
            }
            if (values[0] != 99) {
                progressBar.setProgress(values[0]);
                int show = values[0] + 1;
                mTextView.setText(show + "%");
            } else {
                progressBar.setProgress(100);
                mTextView.setText(100 + "%");
            }
        }
    }
}
