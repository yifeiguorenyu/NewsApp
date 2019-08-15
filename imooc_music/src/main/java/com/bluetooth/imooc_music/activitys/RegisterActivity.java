package com.bluetooth.imooc_music.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView iv_phone,iv_password,iv_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);
    }


    public void onResiterClick(View view) {
        Log.d("tage", "onResiterClick: abcd");
    }


}
