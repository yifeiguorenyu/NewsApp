package com.bluetooth.imooc_music.helps;

import com.bluetooth.imooc_music.models.UserModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {
    private Realm mRealm;

    public RealmHelp() {
        mRealm = Realm.getDefaultInstance();
    }

    public void close() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }

    /*
     *保存用户信息
     */
    public void saveUser(UserModel userModel) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(userModel);
        mRealm.commitTransaction();
        close();
    }

    /***
     * 返回所有的用户
     */
    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        RealmResults<UserModel> allUser = query.findAll();
        return allUser;
    }

    /**
     * 验证用户信息
     */
    public boolean validateUser(String phone, String password) {
        boolean result = false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel =
                query.equalTo("phone", phone).equalTo("password", password).findFirst();
        if (userModel != null) {
            result = true;
        }
        return result;
    }
}
