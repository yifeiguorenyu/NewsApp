package com.bluetooth.imooc_music.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bluetooth.imooc_music.R;

public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);
    }
}
