package com.bluetooth.imooc_music.views;

import android.content.Context;
import android.util.AttributeSet;


//宽高相等的ImageView
public class WEqualsHImageView extends android.support.v7.widget.AppCompatImageView {

    public WEqualsHImageView(Context context) {
        super(context);
    }

    public WEqualsHImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //获取view的宽度
//        int width = MeasureSpec.getSize(widthMeasureSpec);
        super.onMeasure(widthMeasureSpec,widthMeasureSpec);
    }
}
