package com.bluetooth.imooc_music.views;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    public GridSpaceItemDecoration(int space,RecyclerView parent){
        mSpace = space;
        getRecyclerViewOffsets(parent);
    }

    /**
     *
     * @param outRect  item 的矩形边界
     * @param view     item View本身
     * @param parent   RecyclerView
     * @param state    RecyclerView 的状态
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
    }

    private void getRecyclerViewOffsets(RecyclerView parent){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
