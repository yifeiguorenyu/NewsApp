package com.bluetooth.imooc_music.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bluetooth.imooc_music.R;
import com.bluetooth.imooc_music.activitys.AlbumListActivity;
import com.bluetooth.imooc_music.activitys.PlayMusicActivity;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {
    private Context mContext;

    private View mItemView;  //每一个item
    private RecyclerView mRecyclerView;
    private boolean isCalcaulationRvHeight;

    public MusicListAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        mRecyclerView = recyclerView;
    }

    /**
     * 获取ItemView的高度
     * 获取ItemView的数量
     * 使用ItemViewHeight * ItemViewNum = RecyclerView的高度
     */
    private void setRecyclerViewHeight() {

        if (isCalcaulationRvHeight || mRecyclerView == null) return; //保证这个方法只调用一次
        isCalcaulationRvHeight = true;
        //获取ItemView的高度

        RecyclerView.LayoutParams itemViewLp =
                (RecyclerView.LayoutParams) mItemView.getLayoutParams();
        //设置recyclerView的高度
        LinearLayout.LayoutParams linearParams =
                (LinearLayout.LayoutParams) mRecyclerView.getLayoutParams();
        linearParams.height = itemViewLp.height * getItemCount();
        mRecyclerView.setLayoutParams(linearParams);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mItemView = LayoutInflater.from(mContext).inflate(R.layout.item_list_muic, viewGroup,
                false);
        return new ViewHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        setRecyclerViewHeight();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, PlayMusicActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
