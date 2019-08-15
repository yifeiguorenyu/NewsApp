package com.bluetooth.imooc_music.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.helps.MediaPlayerHelp;
import com.bumptech.glide.Glide;

public class PlayMusicView extends FrameLayout {
    private Context mContext;
    private View mView;
    private ImageView mIvIcon,mIvNeedle,mIvPlay;

    private FrameLayout mFlPlayMusic;


    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;
    private boolean isPlaying;


    private MediaPlayerHelp mediaPlayerHelp;

    private String mPath;




    public PlayMusicView( Context context) {
        super(context);
        init( context);
    }

    public PlayMusicView( Context context,  AttributeSet attrs) {
        super(context, attrs);
        init( context);
    }

    public PlayMusicView( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init( context);
    }


    private void init(Context context){
        mContext = context;
        mView=LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mFlPlayMusic = mView.findViewById(R.id.fl_play_music);
        mIvNeedle = mView.findViewById(R.id.iv_needle);
        mIvPlay = mView.findViewById(R.id.iv_play);
        /**
         *  1 定义所需要执行的动画
         *    1 光盘转动的动画
         *    2 指针指向光盘的动画
         *    3 指针离开光盘的动画
         *  2  startAnimation
         */

        mPlayMusicAnim= AnimationUtils.loadAnimation(mContext,R.anim.play_music_anim);
        mPlayNeedleAnim= AnimationUtils.loadAnimation(mContext,R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext,R.anim.stop_needle_anim);



        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                trigger();
            }
        });

        addView(mView);

        mediaPlayerHelp = MediaPlayerHelp.getInstance(context);
    }

    /***
     * 切换播放状态
     *
     */
    private void trigger(){
        if(isPlaying){
            stopMusic();
        }else {
            playMusic(mPath);
        }
    }



    /**
     * 播放音乐
     */
    public void playMusic(String path){
        mPath = path;
        isPlaying = true;
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);
        mIvPlay.setVisibility(GONE);

        /**
         *1 当前的音乐是否已经在播放的音乐
         * 2  当前的音乐是已经在播放的音乐,那么直接执行start方法
         * 3 如果当前播放的音乐,不是需要播放的音乐,那么就调用 setPath 方法
         *
         */
        if(mediaPlayerHelp.getPath()!=null&&mediaPlayerHelp.getPath().equals(path)){
            mediaPlayerHelp.start();
        }else {
            mediaPlayerHelp.setPath(path);
            mediaPlayerHelp.setmListener(new MediaPlayerHelp.OnMedaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayerHelp.start();
                }
            });
        }
    }

    /***
     * 停止音乐的方法
     *
     */
    public void stopMusic(){
        isPlaying = false;
        mIvPlay.setVisibility(VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);
        mediaPlayerHelp.pause();
    }


    //设置光盘中显示的音乐封面的图片
    public void setMusicIcon(String icon){
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);

    }
}
