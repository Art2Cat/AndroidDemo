package com.art2cat.dev.fileandcontentprovider;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.ContactsContract.*;
import static android.provider.ContactsContract.CommonDataKinds.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private View view;
    private EditText mEditText;
    private Button btn1, btn2, btn3, btn4;
    private ListView mListView;
    private List<Map<String, Object>> mData;
    private ProgressBar progressBar;
    private static final String TAG = "MainFragment";


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, null);

        btn1 = (Button) view.findViewById(R.id.button);
        mListView = (ListView) view.findViewById(R.id.listView);

        btn1.setOnClickListener(this);

        File file = getActivity().getFilesDir();
        Log.d(TAG, "onCreateView: " + file.toString());
        File file1 = new File(Environment.getExternalStorageDirectory() + "/test.txt");
        Log.d(TAG, "test.txt path: " + file1.getPath());
        file1.delete();
        if (mData != null) {
            SimpleAdapter adapter = new SimpleAdapter(getActivity(), mData, R.layout.contact,
                    new String[]{"id", "name", "phone"}, new int[]{R.id.id_TV, R.id.name_TV, R.id.phone_TV});
            mListView.setAdapter(adapter);

        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                new MyTask().execute();
                break;
        }
    }

    public void WriteFiles(String content) {
        try {
            FileOutputStream fos = getActivity().openFileOutput("test.txt", Context.MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readFiles() {
        String content = null;
        try {
            FileInputStream fis = getActivity().openFileInput("test.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            content = baos.toString();
            fis.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private class MyTask extends AsyncTask<Void, Void, List<Map<String, Object>>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            displayProgressBar();
        }

        @Override
        protected List<Map<String, Object>> doInBackground(Void... params) {
            List<Map<String, Object>> data = getContactInfo();
            return data;
        }

        @Override
        protected void onPostExecute(List<Map<String, Object>> maps) {
            super.onPostExecute(maps);
            mData = maps;
            if (mData != null) {
                SimpleAdapter adapter = new SimpleAdapter(getActivity(), mData, R.layout.contact,
                        new String[]{"id", "name", "phone"}, new int[]{R.id.id_TV, R.id.name_TV, R.id.phone_TV});
                mListView.setAdapter(adapter);
            }
            cancelProgressBar();
        }
    }

    public List<Map<String, Object>> getContactInfo() {
        List<Map<String, Object>> contactInfo = new ArrayList<Map<String, Object>>();
        ContentResolver cr = getActivity().getContentResolver();
        Cursor c = cr.query(Contacts.CONTENT_URI, new String[]{Contacts._ID,
                Contacts.DISPLAY_NAME}, null, null, null);
        if (c != null) {

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("_id"));
                Log.i("info", "_id:" + id);
                Log.i("info", "name:" + c.getString(c.getColumnIndex("display_name")));
                String name = c.getString(c.getColumnIndex("display_name"));
                Cursor c1 = cr.query(Phone.CONTENT_URI, new String[]{
                                Phone.NUMBER, Phone.TYPE},
                        Phone.CONTACT_ID + "=" + id, null, null);

                // 查询通讯录中电话号码
                if (c1 != null) {
                    while (c1.moveToNext()) {
                        int type = c1.getInt(c1.getColumnIndex(Phone.TYPE));
                        if (type == Phone.TYPE_HOME) {
                            Log.i("info",
                                    "ŒÒÍ¥µç»°£º"
                                            + c1.getString(c1
                                            .getColumnIndex(Phone.NUMBER)));
                        } else if (type == Phone.TYPE_MOBILE) {
                            Log.i("info",
                                    "ÊÖ»ú£º"
                                            + c1.getString(c1
                                            .getColumnIndex(Phone.NUMBER)));

                            String phone = c1.getString(c1.getColumnIndex(Phone.NUMBER));
                            Map<String, Object> contact = new HashMap<String, Object>();
                            contact.put("id", id);
                            contact.put("name", name);
                            contact.put("phone", phone);
                            contactInfo.add(contact);
                        }
                    }
                    c1.close();
                }
                // 根据通讯录中的ＩＤ查询用户的邮箱
                Cursor c2 = cr.query(Email.CONTENT_URI, new String[]{
                                Email.DATA, Email.TYPE}, Email.CONTACT_ID + "=" + id,
                        null, null);
                if (c2 != null) {
                    while (c2.moveToNext()) {
                        int type = c2.getInt(c2.getColumnIndex(Email.DATA));
                        if (type == Email.TYPE_WORK) {
                            Log.i("info",
                                    "ｅｍａｉｌ"
                                            + c2.getString(c2
                                            .getColumnIndex(Email.DATA)));
                        }
                    }
                    c2.close();
                }
            }
            c.close();
        }
        return contactInfo;
    }

    private void displayProgressBar() {
        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void cancelProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
