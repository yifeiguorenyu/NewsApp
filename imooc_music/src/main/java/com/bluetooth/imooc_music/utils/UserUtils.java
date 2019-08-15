package com.bluetooth.imooc_music.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.activitys.LoginActivity;
import com.bluetooth.imooc_music.helps.RealmHelp;
import com.bluetooth.imooc_music.models.UserModel;

public class UserUtils {
    /***
     *  验证用户输入的合法性
     */
    private static final String TAG = "UserUtils-1";

    public static boolean validateLogin(Context context, String phoneNumber, String password) {
        boolean mobileExact = RegexUtils.isMobileExact(phoneNumber);
        if (!mobileExact) {
            Toast.makeText(context, "输入的手机号码不合法", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "输入的密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //退出登录
    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义activity跳转动画
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

    //注册用户
    public static boolean registerUser(Context context, String phone, String password,
                                    String passwordConfirm) {
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(StringUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"新密码和确认密码不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        //用户当前输入的手机号是否已经被注册
        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户到数据库
     */
    public static void saveUser(UserModel userModel){
        RealmHelp realmHelp =new RealmHelp();
        realmHelp.saveUser(userModel);
    }
}
