package com.bluetooth.imooc_music.helps;

import android.content.Context;
import android.util.Log;

import com.bluetooth.imooc_music.migration.Migration;
import com.bluetooth.imooc_music.models.MusicSourceModel;
import com.bluetooth.imooc_music.models.UserModel;
import com.bluetooth.imooc_music.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelp {
    private Realm mRealm;
    private static final String TAG = "RealmHelp-1";

    public RealmHelp() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Realm 数据库发生结构性变化时 模型或者模型中的字段发生了新增
     */
    /**
     * 告诉数据需要迁移,并且为Realm设置最新的配置
     * @return
     */
    public static void migration(){
        RealmConfiguration conf = getRealmConf();
        Realm.setDefaultConfiguration(conf);
        //
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*
    * 返回RealmConfiguration
    *
    *
     */
    private static RealmConfiguration getRealmConf(){
     return new RealmConfiguration.Builder().schemaVersion(1)
            .migration(new Migration())
             .build();
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


    /**
     * 获取当前用户
     */
    public UserModel getUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel user  = query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
        return user;
    }

    /*
    *修改密码
     */
    public void changePassword(String password){
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();


    }

    /**
     *  用户登录存放数据
     *  用户退出 删除数据
     */
    /**
     * 保存音乐原数据
     *
     */

    public void setMusicSource(Context context){
        //拿到资源文件中的数据
        String musicSourceJson = DataUtils.getJsonFromAssets(context, "DataSource.json");
        Log.d(TAG, "musicSourceJson: "+musicSourceJson);
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class,musicSourceJson);
        mRealm.commitTransaction();
    }

    /**
     * 删除音乐原数据
     *
     *  1 realmRsult delete
     *  2 Realm delete 删除这个模型下所有的数据
     */

    public void removeMusicSource(){
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.commitTransaction();
    }
}
