package com.bluetooth.imooc_music.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.Utils;
import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.activitys.LoginActivity;

public class UserUtils {
    /***
     *  验证用户输入的合法性
     */
    private static final String TAG = "UserUtils-1";
    public static boolean validateLogin(Context context,String phoneNumber ,String password){
        boolean mobileExact = RegexUtils.isMobileExact(phoneNumber);
        if(!mobileExact){
            Toast.makeText(context,"输入的手机号码不合法",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"输入的密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //退出登录
    public static void logout(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义activity跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    //
}
