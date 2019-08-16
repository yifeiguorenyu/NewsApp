package com.bluetooth.imooc_music.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bluetooth.imooc_music.constants.SPConstants;
import com.bluetooth.imooc_music.helps.UserHelper;

public class SPUtils {
    /**
     * 当用户登录时,利用SharedPrefrences 保存用户登录的用户标记(手机号码)
     */
    public static boolean saveUser(Context context, String phone) {
        SharedPreferences sp =
                context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(SPConstants.SP_KEY_PHONE,phone);
        boolean result = edit.commit();
        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean isLoginUser(Context context){
        boolean result = false;
        SharedPreferences sp =
                context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPConstants.SP_KEY_PHONE, "");
        if(!TextUtils.isEmpty(phone)){
            result = true;
            UserHelper.getInstance().setPhone(phone);
        }
        return  result;
    }

    /**
     * 删除用户标记
     */
    public static boolean removeUser(Context context){
        SharedPreferences sp =
                context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(SPConstants.SP_KEY_PHONE);
        boolean isRemove = edit.commit();
        return isRemove;

    }
}
