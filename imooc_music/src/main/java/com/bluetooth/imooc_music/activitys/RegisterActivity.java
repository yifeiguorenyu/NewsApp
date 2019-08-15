package com.bluetooth.imooc_music.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.utils.UserUtils;
import com.bluetooth.imooc_music.views.InputView;

public class RegisterActivity extends BaseActivity {

    private InputView iv_phone,iv_password,iv_password_confirm;
    private static final String TAG = "RegisterActivity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        initNavBar(true,"注册",false);
        iv_phone = fd(R.id.input_phone);
        iv_password = fd(R.id.input_password);
        iv_password_confirm = fd(R.id.input_password_confirm);
    }


    //点击注册按钮

    /**
     * 1 用户输入合法性验证
     * 2 用户是否输入了密码和确定密码 并且两次的输入是否相同
     * 3 用户输入的手机号是否已经被注册
     *  保存用户输入的手机号码和密码
     * @param view
     */
    public void onResiterClick(View view) {
        String phone = iv_phone.getInputStr();
        String password = iv_password.getInputStr();
        String passwordConfirm = iv_password_confirm.getInputStr();
        boolean isSuccess = UserUtils.registerUser(this, phone, password, passwordConfirm);
        if(isSuccess){
            onBackPressed();
        }
    }


}
