package com.art2cat.aestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private TextView encryptData, decryptData;
    private AESUtils aesUtils = new AESUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button encrypt = (Button) findViewById(R.id.encrypt);
        Button decrypt = (Button) findViewById(R.id.decrypt);

        encryptData = (TextView) findViewById(R.id.rsa_encrypt);
        decryptData = (TextView) findViewById(R.id.rsa_decrypt);
        editText = (EditText) findViewById(R.id.editText);

        encrypt.setOnClickListener(this);
        decrypt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.encrypt:
                try {
                    String editTexts = editText.getText().toString();
                    String encrypts = aesUtils.encrypt(editTexts);
                    encryptData.setText(encrypts);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.decrypt:
                try {
                    String test = encryptData.getText().toString();

                    String decrypts = aesUtils.decrypt(test);
                    decryptData.setText(decrypts);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
