package com.bluetooth.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PhoneInfo siminfo = new PhoneInfo(MainActivity.this);
        System.out.println("getProvidersName:" + siminfo.getProvidersName());
        System.out.println("getNativePhoneNumber:"
                + siminfo.getNativePhoneNumber());
        System.out.println("------------------------");
        System.out.println("getPhoneInfo:" + siminfo.getPhoneInfo());
        tv = findViewById(R.id.abc);
        tv.setText("getProvidersName:" + siminfo.getProvidersName()
                + "getNativePhoneNumber:" + siminfo.getNativePhoneNumber()+"\n"
                + "getPhoneInfo:" + siminfo.getPhoneInfo());
    }
}
