package com.bluetooth.imooc_music.activitys;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.views.PlayMusicView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {


    //音乐地址  http://res.lgdsunday.club/Nostalgic%20Piano.mp3
    private ImageView mIvBg;
    private PlayMusicView mPlayMusicView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        //去掉status 使页面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();
    }

    public void onBackClick(View view) {
        onBackPressed();
    }

    private void initView() {
        mIvBg = fd(R.id.iv_bg);
        // glide-transformations
        Glide.with(this)
                .load("http://res.lgdsunday.club/poster-1.png")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(mIvBg);
        mPlayMusicView = fd(R.id.play_music_view);
        mPlayMusicView.setMusicIcon("http://res.lgdsunday.club/poster-1.png");
        mPlayMusicView.playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");
    }
}
