package com.yifei.sendmessage;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.yifei.sendmessage.fragment.FestivalCategoryFragment;
import com.yifei.sendmessage.fragment.SmsHistoryFragment;

public class MainActivity extends AppCompatActivity {


    private TabLayout mTableLayout;
    private ViewPager mViewPager;
    private String[] mTitles = new String[]{"节日短信","发送记录"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mTableLayout= findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_page);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                if(i==0){
                    return new FestivalCategoryFragment();
                }
                return  new SmsHistoryFragment();
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles[position];
            }
        });

      mTableLayout.setupWithViewPager(mViewPager);
    }
}
