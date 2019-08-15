package com.bluetooth.imooc_music.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bluetooth.imooc_music.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomActivity extends BaseActivity {
    //延迟三秒,跳转页面
    private Timer mTimer;
    private static final String TAG = "WelcomActivity-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        init();
    }

    private void init(){
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //打印当前线程为 主线程
                toLogin();
            }
        },3000);
    }
    
    private void toMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void toLogin(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
