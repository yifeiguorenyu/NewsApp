package com.bluetooth.imooc_music.helps;

import com.bluetooth.imooc_music.models.UserModel;

import io.realm.Realm;

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
}
