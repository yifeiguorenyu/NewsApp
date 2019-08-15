package com.bluetooth.rxandroidtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity-1";

    private SmartRefreshLayout sr_layout;
    private GifImageView gifImageView;
    private RecyclerView recyclerView;


    private Handler mHandler;
    private List<CateModel> mDataSource;

    private CateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        init();

        initEvents();

    }



    private void initView() {
        sr_layout = findViewById(R.id.refresh_layout);
        recyclerView = findViewById(R.id.my_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void  initDataSource(){
        mDataSource.addAll(getDatas());
        mAdapter.setDataSource(mDataSource);
    }

    private void init() {
        mDataSource = new ArrayList<>();
        mAdapter = new CateAdapter(this,mDataSource);
        mHandler = new Handler();
        recyclerView.setAdapter(mAdapter);
    }
    private void initEvents() {
        sr_layout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
             initDataSource();
             sr_layout.finishLoadMore();
            }
        });
        sr_layout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initDataSource();
                sr_layout.finishRefresh();
            }
        });
        sr_layout.setEnableLoadMore(true);
        sr_layout.autoRefresh();
    }
    private List<CateModel> getDatas () {
        return Arrays.asList(
                new CateModel("但家香酥鸭", "爱过那张脸", R.mipmap.image_practice_repast_1, R.mipmap.image_avatar_1)
                , new CateModel("香菇蒸鸟蛋", "淑女算个鸟", R.mipmap.image_practice_repast_2, R.mipmap.image_avatar_2)
                , new CateModel("花溪牛肉粉", "性感妩媚", R.mipmap.image_practice_repast_3, R.mipmap.image_avatar_3),
                new CateModel("破酥包", "一丝丝纯真", R.mipmap.image_practice_repast_4, R.mipmap.image_avatar_4)
                , new CateModel("盐菜饭", "等着你回来", R.mipmap.image_practice_repast_5, R.mipmap.image_avatar_5)
                , new CateModel("米豆腐", "宝宝树人", R.mipmap.image_practice_repast_6, R.mipmap.image_avatar_6));
    }
}
