package com.art2cat.encrypttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //rsa_public_key
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFGMdDEV5C6AEwAGWD5olbyDhc\n" +
            "IQZ4xTwMNEBJi5kBRWYaiBMzzs2sTTVzxCPE20eAAxt8I6zXqa6rQzl78g/m6n+e\n" +
            "GRI/LlLenNgZatoj7GQGgnIPKx2HEQy22BzXaWs2AOBLX4US79hTpWY02eXKCXBd\n" +
            "ofyEm2LH2uWPPoQqKwIDAQAB";

    //rsa_private_key
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMUYx0MRXkLoATAA" +
            "ZYPmiVvIOFwhBnjFPAw0QEmLmQFFZhqIEzPOzaxNNXPEI8TbR4ADG3wjrNeprqtD" +
            "OXvyD+bqf54ZEj8uUt6c2Blq2iPsZAaCcg8rHYcRDLbYHNdpazYA4EtfhRLv2FOl" +
            "ZjTZ5coJcF2h/ISbYsfa5Y8+hCorAgMBAAECgYEAkU8kUTjz0sXzYi1YqncDm8ww" +
            "aRPcDpvzGlhHcTFfO4WrsjHSXmbAUaCWoOPhLxgvTZx83ff7TQCHizJDpjKfL2ZD" +
            "WsxAJtwd37OByiI6kUNlMykOxNZycUGJ9vaXr57OEH1DDNkMkX7H9E4lb5z6bOwC" +
            "fuDM32zh/niQtSQP65kCQQDzCB0tlx1Hh14bhk2UBJM2tux/5P4NacxITHUzWHbK" +
            "rhR8hT23PLeUi72nP1NJ9GpIoVCws/J10ICKI8q+WKfPAkEAz50uBinog4bPV6wE" +
            "sLg0XXTR1ac8ZaQ3uBzwCnh7m3M/0QZu1W7TQvYkm5PdLaiN3MFdk4/7EjWDaV3j" +
            "3N6S5QJAXS+qSHXd8zRTgEhR7MSIUf13115Nj4UWoE44zjRIcFSpZEmOrXjph1rB" +
            "oKRmYkAGlMzN7MNC36vP7aflsHC7/wJAVc1i8P8u7fSwCk64XYSzd5BJDGCiUGtu" +
            "77Nd7SXgB924mR1sft7fhsQNWxLgDPelMX/kuZB+tgbRuaEpA+YklQJBAJh0CDaQ" +
            "qA4fcaG0b4KKhYROOcNn1L5XPIxiDZ6QjKwyOsQX6agu0pIetIUobOT7M/HgRTFC" +
            "pO6j8TaXggxr2mA=";
    private EditText editText;
    private TextView encryptData, decryptData;
    private PublicKey publicKey;
    private PrivateKey privateKey;

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

        //随机生成publicKey和privateKey
        KeyPair keyPair = RSAUtils.generateRSAKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.encrypt:
                try {
                    String editTexts = editText.getText().toString();
                    //PublicKey publicKey = RSAUtils.loadPublicKey(PUBLIC_KEY);
                    byte[] bytes = editTexts.getBytes();
                    byte[] bytes1 = RSAUtils.encryptData(bytes, publicKey);
                    if (bytes1 != null) {
                        String encrypts =  new String(Base64.encode(bytes1, Base64.CRLF));
                        encryptData.setText(encrypts);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.decrypt:
                try {
                    String test = encryptData.getText().toString();
                    //PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
                    byte[] bytes = test.getBytes();

                    byte[] bytes1 = RSAUtils.decryptData(Base64.decode(bytes, Base64.CRLF), privateKey);
                    if (bytes1 != null) {
                        String decrypts = new String(bytes1);
                        decryptData.setText(decrypts);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
