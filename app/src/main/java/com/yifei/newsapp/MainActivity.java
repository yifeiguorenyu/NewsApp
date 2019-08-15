package com.yifei.newsapp;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private NavigationView mNavigation ;
    private static final String TAG = "MainActivity-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design_layount);
        mNavigation = findViewById(R.id.navigationview);
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Log.d("TAG", (String) menuItem.getTitle());
                return false;
            }
        });
        String key ="wx5515!!";
        String encode = null;
        try {
            encode = DesUtil.encrypt1(key, "今天星期二");
        } catch (Exception e) {
            Log.d(TAG, "onCreate: "+e.getMessage());
            e.printStackTrace();
        }
        Log.d(TAG, "encode: "+encode);

    }
}
