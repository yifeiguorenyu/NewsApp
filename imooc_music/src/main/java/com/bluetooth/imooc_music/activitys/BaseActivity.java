package com.bluetooth.imooc_music.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluetooth.imooc_music.R;

public class BaseActivity extends Activity {
    private ImageView mIvBack, mIvMe;
    private TextView mTvTitle;

    protected void initNavBar(boolean isShowBack, String title, boolean isShowMe) {
        mIvBack = fd(R.id.iv_back);
        mIvMe = fd(R.id.iv_me);
        mTvTitle = fd(R.id.tv_title);
        mIvBack.setVisibility(isShowBack?View.VISIBLE:View.GONE);
        mTvTitle.setText(title);
        mIvMe.setVisibility(isShowMe?View.VISIBLE:View.GONE);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this,MeActivity.class);
                startActivity(intent);
            }
        });
    }

    protected <T extends View> T fd(@IdRes int id) {
        return findViewById(id);
    }

}
