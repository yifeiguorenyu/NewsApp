package com.bluetooth.imooc_music.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.utils.UserUtils;
import com.bluetooth.imooc_music.views.InputView;

public class LoginActivity extends BaseActivity {

    private InputView inputPhone;
    private InputView inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        initNavBar(false, "登录", false);
        inputPhone = fd(R.id.input_phone);
        inputPassword = fd(R.id.input_password);
    }

    //立即注册,点击事件 跳转注册页面
    public void onRegisterClick(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);

    }

    //点击登录
    public void onCommitClick(View view) {
        String phone = inputPhone.getInputStr();
        String password = inputPassword.getInputStr();
//        boolean isValidate = UserUtils.validateLogin(this, phone ,password);
//        if(!isValidate){
//            return;
//        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
