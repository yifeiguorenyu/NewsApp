package com.bluetooth.imooc_music.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.helps.UserHelper;
import com.bluetooth.imooc_music.utils.UserUtils;

public class MeActivity extends BaseActivity {
    private TextView mTvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
        mTvUser=fd(R.id.tv_user);
        mTvUser.setText("用户名:"+ UserHelper.getInstance().getPhone());

    }

    //修改密码点击事件
    public void onChangeClick(View view) {
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }
    //退出登录点击事件
    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }



}
