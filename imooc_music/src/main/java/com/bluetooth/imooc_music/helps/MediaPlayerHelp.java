package com.bluetooth.imooc_music.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerHelp {
    public static MediaPlayerHelp instance;
    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private OnMedaPlayerHelperListener mListener;

    private String mPath;

    public void setmListener(OnMedaPlayerHelperListener mListener) {
        this.mListener = mListener;
    }

    public static MediaPlayerHelp getInstance(Context context){
        if(instance==null){
            synchronized (MediaPlayerHelp.class){
                if(instance==null){
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelp(Context context){
        mContext = context;
        mMediaPlayer = new MediaPlayer();

    }

    /***
     * 1 setPath :当前需要播放的音乐
     * 2 start 播放音乐
     * 3 pause 暂停播放
     *
     */

    //1 setPath :当前需要播放的音乐
    public void setPath(String path){
        /**
         * 1 音乐正在播放,重置音乐播放状态
         * 2 设置播放音乐路径
         * 3 准备播放
         *
         */
        // 1 音乐正在播放,重置音乐播放状态
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }
        //2 设置播放音乐路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3 准备播放
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(mListener!=null){
                    mListener.onPrepared(mp);
                }
            }
        });

        mPath = path;
    }

    // 2 start 播放音乐
    public void start(){
        if(mMediaPlayer.isPlaying()){
            return;
        }
        mMediaPlayer.start();
    }

    //3 pause 暂停播放
    public void pause(){
        mMediaPlayer.pause();
    }
    //获取当前播放的音乐
    public String getPath(){
        return  mPath;
    }

    public interface OnMedaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
    }
}
