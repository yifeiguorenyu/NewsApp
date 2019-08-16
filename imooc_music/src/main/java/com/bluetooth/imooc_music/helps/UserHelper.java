package com.bluetooth.imooc_music.helps;

/**
 * 1 用户登录 时
 * 1 当用户没有登录过应用程序时 利用SharedPreferences 保存用户登录的标记,
 * 2 利用全局单例类UserHelper  保存用户登录信息
 * <p>
 * 2 用户退出
 * 1 删除掉SharedPreferences保存的用户标记, 退出到登录页面
 */
public class UserHelper {
    private static UserHelper instance;

    private UserHelper() {
    }

    public static UserHelper getInstance() {
        if (instance == null) {
            synchronized (UserHelper.class) {
                if (instance == null) {
                    instance = new UserHelper();
                }
            }
        }
        return instance;
    }


    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
