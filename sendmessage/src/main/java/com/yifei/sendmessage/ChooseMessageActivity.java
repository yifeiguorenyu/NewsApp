package com.yifei.sendmessage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yifei.sendmessage.bean.FestivalLab;
import com.yifei.sendmessage.bean.Msg;
import com.yifei.sendmessage.fragment.FestivalCategoryFragment;

import java.util.List;

public class ChooseMessageActivity extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton actionButton;

    private ArrayAdapter<Msg> msgArrayAdapter;

    private int mFestivalId;
    private String TAG ="ChooseMessage11";
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_message);


        mInflater = LayoutInflater.from(this);
        mFestivalId = getIntent().getIntExtra(FestivalCategoryFragment.FESTIVAL_ID, 0);
        setTitle(FestivalLab.getInstance().getFestivalById(mFestivalId).getName());
        initViews();

        initEvent();

    }

    private void initEvent() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessageActivity.toActivity(ChooseMessageActivity.this,mFestivalId,-1);
            }
        });
    }

    private void initViews() {
        listView = findViewById(R.id.id_lv_message);
        actionButton = findViewById(R.id.fab_send);
        List<Msg> mdatas = FestivalLab.getInstance().getMsgsByFestivalId(mFestivalId);
        listView.setAdapter(msgArrayAdapter = new ArrayAdapter<Msg>(this, -1, mdatas) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_message, parent, false);

                }
                Log.d(TAG, "getView: "+convertView.toString());
                TextView mTextView = convertView.findViewById(R.id.tv_content);
                Button button = convertView.findViewById(R.id.id_btn_toSend);
                mTextView.setText("   "+msgArrayAdapter.getItem(position).getContent());
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       int msgId= msgArrayAdapter.getItem(position).getId();
                        Log.d(TAG, "onClick: msgId="+msgId);
                        SendMessageActivity.toActivity(ChooseMessageActivity.this,mFestivalId,msgId);

                    }
                });
                return convertView;
            }
        });

    }
}
