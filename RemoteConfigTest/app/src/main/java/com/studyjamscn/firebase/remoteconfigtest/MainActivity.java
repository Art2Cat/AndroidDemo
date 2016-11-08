package com.studyjamscn.firebase.remoteconfigtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View mView;
    private List<Bean> mData = new ArrayList<Bean>();
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;
    private int mColor;
    private String mTitle;

    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private DatabaseReference mDatabaseReference;
    private ValueEventListener mListener;
    //这里的常量是firebase console remote config 中设置的参数名及默认值参数名
    private static final String DEFAULT_UI = "default_content";
    private static final String DEFAULT_COLOR = "default_color";
    private static final String DEFAULT_TITLE = "default_title";
    private static final String NATIONAL_HOLIDAYS = "national_holidays";
    private static final String NATIONAL_DAY_TITLE = "national_day_title";
    private static final String NATIONAL_DAY_COLOR = "national_day_color";
    private static final String IS_HOLIDAY_UI = "holiday_ui";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //使用getSystemService()方法获取LayoutInflater
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //生成视图
        mView = inflater.inflate(R.layout.activity_main, null);
        setContentView(mView);
        //视图组件初始化
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Button fetch_config = (Button) findViewById(R.id.fetch_config);

        //获取FirebaseRemoteConfig实例
        // [START get_remote_config_instance]
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        // [END get_remote_config_instance]

        // Create Remote Config Setting to enable developer mode.
        // 创建Remote Config Setting启用开发者模式
        // Fetching configs from the server is normally limited to 5 requests per hour.
        // 从服务器获取远程配置通常限制每小时请求5次
        // Enabling developer mode allows many more requests to be made per hour, so developers
        // can test different config values during development.
        // 启用开发者模式可以允许更多次的请求，以便开发者在开发中测试不同的配置值
        // [START enable_dev_mode]
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        // [END enable_dev_mode]

        // Set default Remote Config values. In general you should have in app defaults for all
        // values that you may configure using Remote Config later on. The idea is that you
        // use the in app defaults and when you need to adjust those defaults, you set an updated
        // value in the App Manager console. Then the next time you application fetches from the
        // server, the updated value will be used. You can set defaults via an xml file like done
        // here or you can set defaults inline by using one of the other setDefaults methods.S
        // 设置Remote Config默认值，通常你必须为你稍后需要配置的Remote Config设置默认值，
        // 这个想法是，您使用的应用程序的默认值，当你需要调整这些默认值时，可以设置在App Manager控制台更新这些值。
        // 然后，你的应用程序下一次将使用从服务器获取更新后的值。您可以设置默认值通过一个XML文件像在这里完成，
        // 也可以通过使用其他内置设置默认值的方法
        // [START set_default_values]
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        // [END set_default_values]

        getDataFromFirebase(DEFAULT_UI);

        fetch_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRemoteConfig();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //退出activity时，当listener不为空，移除listener
        if (mListener != null) {
            mDatabaseReference.removeEventListener(mListener);
        }
    }

    private void updateUI() {
        mToolbar.setTitle(mTitle);
        mToolbar.setBackgroundColor(mColor);

        //新建RecyclerView.Adapter对象
        MyAdapter myAdapter = new MyAdapter(this, mData);
        //设置适配器
        mRecyclerView.setAdapter(myAdapter);
        //设置LayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private Context context;
        private List<Bean> mData;

        public MyAdapter() {
        }

        public MyAdapter(Context context, List<Bean> data) {
            this.context = context;
            this.mData = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //使用LayoutInflater.from(Context).inflate()方法生成item的视图
            MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context)
                    .inflate(R.layout.items, parent, false));
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.onBindItem(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView mImageView;
            private TextView mTitle;
            private TextView mAddress;

            public MyViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.imageView);
                mTitle = (TextView) itemView.findViewById(R.id.title_TV);
                mAddress = (TextView) itemView.findViewById(R.id.content_TV);
            }

            public void onBindItem(Bean bean) {
                mTitle.setText(bean.getTitle());
                mAddress.setText(bean.getAddress());

                if (bean.getPic_url() != null) {
                    new DisplayImage(mImageView).execute(bean.getPic_url());
                }

            }
        }
    }

    /**
     * Fetch RemoteConfig from server.
     * 从服务器获取远程配置
     */
    private void fetchRemoteConfig() {

        long cacheExpiration = 3600; // 1 hour in seconds.
        // If in developer mode cacheExpiration is set to 0 so each fetch will retrieve values from
        // the server.
        // 如果开发者模式cacheExpiration设置为0，将从服务器提取配置值。
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        // [START fetch_config_with_callback]
        //
        // cacheExpirationSeconds is set to cacheExpiration here, indicating that any previously
        // fetched and cached config would be considered expired because it would have been fetched
        // more than cacheExpiration seconds ago. Thus the next fetch would go to the server unless
        // throttling is in progress. The default expiration duration is 43200 (12 hours).
        // 在这里设置cacheExpiration时限，这表明先前提取和缓存的配置将被视为过期，
        // 因为它会一直比cacheExpiration秒前获取更多。因此，下次将会从服务器提取，除非节流正在进行中。
        // 因为在cacheExpiration前已经获取。因此下次将会从服务器提取，除非节流正在进行中。
        // 默认到期时限为43200（12小时）。
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Snackbar.make(mView, "Fetch Succeeded", Snackbar.LENGTH_SHORT).show();

                            // Once the config is successfully fetched it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Snackbar.make(mView, "Fetch Failed", Snackbar.LENGTH_SHORT).show();
                        }
                        getDataFromFirebase(IS_HOLIDAY_UI);
                    }
                });
    }

    /**
     * 获取配置
     * @param type
     * @return
     */
    private String getConfig(String type) {
        String content;
        //这里判断是获取默认值还是其他配置，
        if (type.equals(DEFAULT_UI)) {
            content = mFirebaseRemoteConfig.getString(DEFAULT_UI);
            mColor = (int) mFirebaseRemoteConfig.getDouble(DEFAULT_COLOR);
            mTitle = mFirebaseRemoteConfig.getString(DEFAULT_TITLE);
            Log.d(TAG, "getConfig: " + content);
            return content;
        } else if (type.equals(IS_HOLIDAY_UI)) {
            if (mFirebaseRemoteConfig.getBoolean(type)) {
                content = mFirebaseRemoteConfig.getString(NATIONAL_HOLIDAYS);
                mColor = (int) mFirebaseRemoteConfig.getDouble(NATIONAL_DAY_COLOR);
                mTitle = mFirebaseRemoteConfig.getString(NATIONAL_DAY_TITLE);
                Log.d(TAG, "getConfig: " + content);
                return content;
            }
        }
        return null;
    }

    private void getDataFromFirebase(String type) {
        String content = getConfig(type);
        Log.d(TAG, "getDataFromFirebase: " + content);
        if (content != null) {
            //获取DatabaseReference并传入获取的key
            mDatabaseReference = FirebaseDatabase.getInstance().getReference()
                    .child("remoteconfigtest").child(content);
            //新建ValueEventListener对象
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Log.d(TAG, "getChildrenCount: " + dataSnapshot.getChildrenCount());
                        //清除原数据
                        if (!mData.isEmpty()) {
                            mData.clear();
                        }
                        //使用for循环读取database中“bean”的数据
                        for (DataSnapshot child : dataSnapshot.child("bean").getChildren()) {
                            Bean bean = child.getValue(Bean.class);
                            Log.d(TAG, "onDataChange: " + bean.title);
                            mData.add(bean);
                        }
                        updateUI();
                        //获取database中toolbar的配置
                        //ToolbarConfig toolbarConfig = dataSnapshot.child("toolbarconfig")
                        //        .getValue(ToolbarConfig.class);
                        //mToolbar.setBackgroundColor(Integer.valueOf(toolbarConfig.backgroundcolor));
                        //mToolbar.setTitle(toolbarConfig.title);
                    } else {
                        Log.d(TAG, "nothing here");
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Snackbar.make(mView, databaseError.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            };
            //将ValueEventListener添加到DatabaseReference
            mDatabaseReference.addValueEventListener(listener);
            mListener = listener;
        }
    }

    class DisplayImage extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageview;
        //创建DisplayImage构造方法传入ImageView
        public DisplayImage(ImageView imageview) {
            this.imageview = imageview;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            //初始化HttpURLConnection
            HttpURLConnection conn = null;
            try {
                URL url = new URL(params[0]);
                //使用URL.openConnection()方法获取http连接
                conn = (HttpURLConnection) url.openConnection();
                //设置连接超时时间5秒
                conn.setConnectTimeout(5000);
                //设置读取超时时间5秒
                conn.setReadTimeout(5000);
                //设置http请求方法
                conn.setRequestMethod("GET");
                //获取连接返回值，200表示连接成功
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    //使用BitmapFactory.decodeStream()解码从网络获取的输入流
                    Bitmap bitmap = BitmapFactory.decodeStream(conn.getInputStream(), null, null);
                    return bitmap;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //当http连接不为空时断开连接
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //使用setImageBitmap()传入返回的bitmap设置图片
            if (bitmap != null) {
                imageview.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * Created by art2cat
     * on 10/4/16.
     * Bean类对应的是database中“bean”的"imageX"数据
     * Bean类成员变量名字要对应““bean”的"imageX"数据中子数据的名字，
     * 否则无法通过getValue(Class<T> var1)方法获得Bean对象
     */

    public static class Bean {

        public String title;
        public String address;
        public String pic_url;

        public Bean() {
        }

        //这个构造方法必须要有
        public Bean(String title, String address, String pic_url) {
            this.title = title;
            this.address = address;
            this.pic_url = pic_url;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    /**
     * Created by art2cat
     * on 10/4/16.
     */

    public class ToolbarConfig {
        public String title;
        public String backgroundcolor;

        public ToolbarConfig() {
        }

        //这个构造方法必须有
        public ToolbarConfig(String title, String backgroundcolor) {
            this.title = title;
            this.backgroundcolor = backgroundcolor;
        }
    }
}
