package com.bluetooth.rxandroidtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.MyHolder> {

    private LayoutInflater mInfalater;
    private List<CateModel> mDataSource;


    public void setDataSource(List<CateModel> newDataSource){
        this.mDataSource = newDataSource;
        notifyDataSetChanged();
    }

    public CateAdapter(Context context, List<CateModel> dateSource){
        mDataSource = dateSource;
        mDataSource = dateSource;
        mInfalater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(mInfalater.inflate(R.layout.item_cate,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        CateModel cateModel = mDataSource.get(i);
        myHolder.tvNickName.setText(cateModel.getNickname());
        myHolder.tvName.setText(cateModel.getName());
        myHolder.ivImage.setImageResource(cateModel.getImageId());
        myHolder.ivAvatar.setImageResource(cateModel.getAvatarId());
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvNickName;
        ImageView ivImage,ivAvatar;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
           tvName = itemView.findViewById(R.id.name);
           tvNickName = itemView.findViewById(R.id.nickname);


           ivImage = itemView.findViewById(R.id.image);
           ivAvatar = itemView.findViewById(R.id.avatar);
        }
    }
}
