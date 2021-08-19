package com.isccohhh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;


public class MainActivity extends AppCompatActivity{

    private EditText input, input2;
    private TextView resultText;
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public static String TAG = "isccohhh_MainActivity";

    static{
        try {
            System.loadLibrary("nc");
            System.loadLibrary("Libs");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            Log.e("ISCCOHHHHHHHHHHHHHH", "UnsatisfiedLinkError!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.input_ori);
        input2 = (EditText) findViewById(R.id.input_ori2);
        resultText = (TextView) findViewById(R.id.text_result);

        findViewById(R.id.btn_native_encode).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String code = input.getText().toString().trim();

                if(!check1(code.getBytes())){
                    resultText.setText("Wrong Code !!!");
                    return;
                }

                MessageDigest digest;
                byte[] messageDigest = null;
                try {
                    digest = MessageDigest.getInstance("MD5");
                    digest.update(code.getBytes());
                    messageDigest = digest.digest();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                assert messageDigest != null;
                StringBuilder sb = new StringBuilder(messageDigest.length * 2);

                for (byte b : messageDigest) {
                    sb.append(HEX_DIGITS[(b & 0xF0) >>> 4]);
                    sb.append(HEX_DIGITS[b & 0xF]);
                }

                String flagMd5 = sb.toString();

                int count = 0;
                int sumLocation = 0;
                for (int i = 0; i < flagMd5.length(); i++) {
                    char a = flagMd5.charAt(i);
                    if (a == '0') {
                        count++;
                        sumLocation += i;
                    }
                }
                if (10 * count + sumLocation == 403) {
                    input2.setEnabled(true);
                    char[] head = flagMd5.substring(0, 4).toCharArray();
                    long seed = (int) head[0] + (int) head[1] + (int) head[2] + (int) head[3] * 1000;
                    Random random = new Random(seed);
                    char[] ans = new char[32];
                    for (int i = 0; i < 32; i++) {
                        ans[i] = HEX_DIGITS[random.nextInt(16)];
                    }
                    String flag = input2.getText().toString().trim();
                    if (flag.length() != 32){
                        resultText.setText("Wrong fLag !!!!!!");
                        return;
                    }
                    for (int i = 0; i < 32; i++) {
                        if (flag.charAt(i) != ans[i]) {
                            resultText.setText("Wrong fLag !");
                            return;
                        }
                    }
                    resultText.setText("OHHH, YOU GET IT ! \n Your fLag is : ISCC{" + flag + "}");
                    Log.i(TAG, "OHHHHH, You get it!");
                }
                else {
                    resultText.setText("Wrong Code !");
                }
            }
        });

        resultText.setText("Welcome");
    }

    public native boolean check1(byte[] s);
}

