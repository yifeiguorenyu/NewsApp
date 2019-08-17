package com.bluetooth.imooc_music;

import android.app.Application;
import com.blankj.utilcode.*;
import com.blankj.utilcode.util.Utils;
import com.bluetooth.imooc_music.helps.RealmHelp;
import com.bumptech.glide.Glide;

import io.realm.Realm;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Realm.init(this);

//        RealmHelp.migration();
    }

}
