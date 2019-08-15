package com.bluetooth.imooc_music.activitys;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.adapters.MusicGridAdapter;
import com.bluetooth.imooc_music.adapters.MusicListAdapter;
import com.bluetooth.imooc_music.views.GridSpaceItemDecoration;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGrid,mRvList;
    private MusicGridAdapter mGridAdapter;
    private MusicListAdapter musicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        initNavBar(false, "慕课音乐", true);
        mRvGrid = fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        mRvGrid.setNestedScrollingEnabled(false);
        mRvGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelOffset(R.dimen.albumMarginSize), mRvGrid));


        mGridAdapter = new MusicGridAdapter(this);
        mRvGrid.setAdapter(mGridAdapter);


        /***
         * 1 .假如已知高度的情况下,可以直接在布局中把RecyclerView 的高度定义上
         * 2 .在不知道列表高度的情况,需要手动计算列表RecyclerView的高度
         */
        mRvList = fd(R.id.rv_list);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setNestedScrollingEnabled(false);
        mRvList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        musicListAdapter = new MusicListAdapter(this,mRvList);

        mRvList.setAdapter(musicListAdapter);

    }
}
