package com.yifei.sendmessage;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yifei.sendmessage.bean.Festival;
import com.yifei.sendmessage.bean.FestivalLab;
import com.yifei.sendmessage.bean.Msg;
import com.yifei.sendmessage.bean.SendedMsg;
import com.yifei.sendmessage.biz.SmsBiz;
import com.yifei.sendmessage.view.FlowLayout;

import java.util.HashSet;

public class SendMessageActivity extends AppCompatActivity {

    public static final String FESTIVAL_ID = "festivalId";
    public static final String MSG_ID = "msgId";
    public static final int CODE_REQEST = 1010;
    private int mFestivalId;
    private int msgId;

    private Festival mFestival;
    private Msg mMsg;

    private EditText mEditMsg;
    private Button mBtnAdd;
    private FlowLayout mflowLayout;
    private FloatingActionButton actionButton;

    private FrameLayout m_loading;
    private static final String TAG = "SendMessage11";

    private HashSet<String> mContactNames = new HashSet<>();
    private HashSet<String> mContactNums = new HashSet<>();

    private LayoutInflater mInflater;

    public static final String ACTION_SEND_MSG = "ACTION_SEND_MSG";
    public static final String ACTION_DELIVER_MSG = "ACTION_DELIVER_MSG";

    private PendingIntent mSendPi;
    private PendingIntent mDeliverPi;

    private BroadcastReceiver mSendBroadcastReceiver;
    private BroadcastReceiver mDeliverBroadcastReceiver;

    private SmsBiz mSmsBiz;

    private int mMsgSendCount;
    private int mTotalCount;

    public static void toActivity(Context context, int festivalId, int msgId) {
        Intent intent = new Intent(context, SendMessageActivity.class);
        intent.putExtra(FESTIVAL_ID, festivalId);
        intent.putExtra(MSG_ID, msgId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        mInflater = LayoutInflater.from(this);
        mSmsBiz = new SmsBiz(this);
        initData();
        initViews();
        initEvent();
        initReciver();
    }

    private void initData() {
        mFestivalId = getIntent().getIntExtra(FESTIVAL_ID, -1);
        msgId = getIntent().getIntExtra(MSG_ID, -1);
        mFestival = FestivalLab.getInstance().getFestivalById(mFestivalId);
        setTitle(mFestival.getName());
    }

    private void initViews() {
        mEditMsg = findViewById(R.id.id_ed_content);
        mBtnAdd = findViewById(R.id.btn_send);
        mflowLayout = findViewById(R.id.fl_content);
        actionButton = findViewById(R.id.actionButton_send);
        m_loading = findViewById(R.id.id_layout_loading);

        if (msgId != -1) {
            mMsg = FestivalLab.getInstance().getMsgById(msgId);
            mEditMsg.setText("  " + mMsg.getContent());
        }
        m_loading.setVisibility(View.GONE);

    }

    private void initEvent() {
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, CODE_REQEST);
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mContactNums.size() == 0) {
                    Toast.makeText(SendMessageActivity.this, "请先选择联系人", Toast.LENGTH_SHORT).show();
                    return;
                }
                m_loading.setVisibility(View.VISIBLE);
                mTotalCount = mSmsBiz.sendMsg(mContactNums,
                        buidSendMsg(mEditMsg.getText().toString()), mSendPi,
                        mDeliverPi);
                mMsgSendCount = 0;
            }
        });
    }

    private SendedMsg buidSendMsg(String msg) {
        SendedMsg sendedMsg = new SendedMsg();
        sendedMsg.setMsg(msg);
        sendedMsg.setFestivalName(mFestival.getName());
        String totalName = "";
        for (String name : mContactNames) {
            totalName += name + ",";
        }
        Log.d(TAG, "totalName: " + totalName);
        sendedMsg.setNames(totalName);
        String numbers = "";
        for (String number : mContactNums) {
            numbers += number + ",";
        }
        Log.d(TAG, "numbers: " + numbers);
        sendedMsg.setNumbers(numbers);
        return sendedMsg;
    }

    private void initReciver() {
        Intent sendIntent = new Intent(ACTION_SEND_MSG);
        mSendPi = PendingIntent.getBroadcast(this, 0, sendIntent, 0);

        Intent deliverIntent = new Intent(ACTION_DELIVER_MSG);
        mDeliverPi = PendingIntent.getBroadcast(this, 0, deliverIntent, 0);

        registerReceiver(mSendBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (getResultCode() == RESULT_OK) {
                    Log.d(TAG, "短信发送成功" + mMsgSendCount + "/" + mTotalCount);
                } else {
                    Log.d(TAG, "短信发送失败");
                }
                mMsgSendCount++;
                if (mMsgSendCount == mTotalCount) {
                    finish();
                }
            }
        }, new IntentFilter(ACTION_SEND_MSG));

        registerReceiver(mDeliverBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(TAG, "联系人已经成功接收到我们的短信");
            }
        }, new IntentFilter(ACTION_DELIVER_MSG));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_REQEST) {
            if (resultCode == RESULT_OK) {

                Uri contactUri = data.getData();
                Cursor cursor = getContentResolver().query(contactUri, null, null, null, null);
                cursor.moveToFirst();
                String contactName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                String number = getContactNumber(cursor);
                if (!TextUtils.isEmpty(number)) {
                    mContactNums.add(number);
                    mContactNames.add(contactName);
                    addTag(contactName);
                }

            }
        }
    }

    private void addTag(String contactName) {
        TextView view = (TextView) mInflater.inflate(R.layout.tag, mflowLayout, false);
        view.setText(contactName);
        mflowLayout.addView(view);

    }

    private String getContactNumber(Cursor cursor) {
        int numCount =
                cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        //判断是否有电话号码
        if (numCount > 0) {
            int contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            cursor.close();
            Cursor phoneCusor =
                    getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                                    "=" + contactId, null, null);

            phoneCusor.moveToFirst();
            String phoneNumber =
                    phoneCusor.getString(phoneCusor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ;
            phoneCusor.close();
            return phoneNumber;
        }


        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDeliverBroadcastReceiver);
        unregisterReceiver(mSendBroadcastReceiver);

    }
}
