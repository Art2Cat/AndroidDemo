package com.art2cat.dev.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class RxJavaActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RxJavaActivity";
    private String[] data = new String[]{"hello world", "fuck world", "anti-social"};
    private List<Integer> mIntList;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        initView();
        mList = new ArrayList<>();
        mList.add("Fuck world");
        mList.add("hello world");
        mList.add("like a charm");
        mList.add("anti-socialist");

        mIntList = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            mIntList.add(i, i);
        }
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.button);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        LinearLayout activity_rx_java = (LinearLayout) findViewById(R.id.activity_rx_java);

        button.setOnClickListener(this);
        button4.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                rxj2Sample();
                break;
            case R.id.button4:
                simplifySample(data);
                break;
            case R.id.button2:
                mapOperation();
                break;
            case R.id.button3:
                moreOperation(mList);
                break;
            case R.id.button5:
                filter();
                break;
            case R.id.button6:
                mapOperation1();
                break;
            case R.id.button7:
                take(mIntList);
                break;
            case R.id.button8:
                doOnNext();
                break;
        }
    }

    private void moreOperation(List<String> mList) {
        Flowable.just(mList)
                .flatMap(new Function<List<String>, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(List<String> strings) throws Exception {
                        return Flowable.fromIterable(strings);
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                showToast(s);
            }
        });
    }

    private void filter() {
        Flowable.fromArray(1, 20, 5, 0, -1, 8)
                //filter 是用于过滤数据的，返回false表示拦截此数据
                //这里只是大于5的值才会被显示
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 5;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showToast(integer.toString());
                        System.out.println(integer);
                    }
                });
    }

    private void take(List<Integer> ints) {
        Flowable.just(ints)
                // take 用于指定订阅者最多收到多少数据。
                .take(8)
                .flatMap(new Function<List<Integer>, Publisher<Integer>>() {
                    @Override
                    public Publisher<Integer> apply(List<Integer> integers) throws Exception {
                        return Flowable.fromIterable(integers);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showToast(integer.toString());
                    }
                });
    }

    private void doOnNext() {
        Flowable.just(1, 2, 3)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showToast("保存:" + integer);
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showToast(integer.toString());
                    }
                });
    }

    private void mapOperation1() {

        Flowable.just("fuck world")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) throws Exception {
                        return s.hashCode();
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return integer.toString();
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showToast("fuck world hashcode: " + s);
                        System.out.println(s);
                    }
                });
    }

    @SuppressWarnings("unchecked")
    private void rxj2Sample() {
        // 创建一个Flowable对象很简单，直接调用Flowable.create即可。
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //创建一个订阅器
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                //设置请求数量
                //如果不调用request，Subscriber的onNext和onComplete方法将不会被调用
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                showToast(s);
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        // 发射器与订阅器绑定
        flowable.subscribe(subscriber);
    }

    private void simplifySample(String[] data) {
        //直接调用Flowable.just创建一个发射字符串的”发射器”
        Flowable<String[]> flowable = Flowable.just(data);

        //创建一个消费者
        Consumer<String[]> consumer = new Consumer<String[]>() {
            @Override
            public void accept(String[] strings) throws Exception {
                showToast(strings[0]);
                showToast(strings[1]);
                showToast(strings[2]);
            }
        };

        //订阅消费者
        flowable.subscribe(consumer);
    }

    private void mapOperation() {
        //map的作用就变换 Flowable 然后返回一个指定类型的 Flowable 对象
        Flowable.just("Fuck world")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + " -art2cat";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showToast(s);
                        System.out.println(s);
                    }
                });
    }

    private void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}
