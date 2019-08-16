package com.bluetooth.imooc_music.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.utils.UserUtils;
import com.bluetooth.imooc_music.views.InputView;

import java.io.FileDescriptor;

public class ChangePasswordActivity extends BaseActivity {

    private InputView mOldPassword,mPassword,mPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);
        mOldPassword = fd(R.id.input_old_password);
        mPassword = fd(R.id.input_new_password);
        mPasswordConfirm = fd(R.id.confirm_new_password);
    }

    public void onChangePasswordClick(View view) {
        String oldPassword = mOldPassword.getInputStr();
        String password = mPassword.getInputStr();
        String passwordConfirm = mPasswordConfirm.getInputStr();
        boolean result = UserUtils.changePassword(this, oldPassword, password, passwordConfirm);
        if(!result){
            return;
        }
        UserUtils.logout(this);

    }
}
