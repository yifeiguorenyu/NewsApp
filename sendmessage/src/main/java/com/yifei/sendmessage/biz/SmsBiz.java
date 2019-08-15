package com.yifei.sendmessage.biz;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import com.yifei.sendmessage.bean.SendedMsg;
import com.yifei.sendmessage.db.SmsProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class SmsBiz {
    private static final String TAG = "SMSBIZ";
    private Context context;

    public SmsBiz(Context context) {
        this.context = context;
    }

    public int sendMsg(String number, String msg, PendingIntent sendPi,
                       PendingIntent deliverPi) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> strings = smsManager.divideMessage(msg);
        for (String content : strings) {
            smsManager.sendTextMessage(number, null, content, sendPi, deliverPi);
        }
        return strings.size();
    }

    public int sendMsg(Set<String> numbers, SendedMsg msg, PendingIntent sendPi,
                       PendingIntent deliverPi) {
        save(msg);
        int result = 0;
        for (String number : numbers) {
            result += sendMsg(number, msg.getMsg(), sendPi, deliverPi);
        }
        return result;
    }

    private void save(SendedMsg sendedMsg) {
        Log.d(TAG, "save");
        sendedMsg.setDate(new Date());
        ContentValues values = new ContentValues();
        values.put(SendedMsg.COLUMN_DATE, sendedMsg.getDateStr());
        values.put(SendedMsg.COLUMN_FESTIVALNAME, sendedMsg.getFestivalName());
        values.put(SendedMsg.COLUMN_MSG, sendedMsg.getMsg());
        values.put(SendedMsg.COLUMN_NAMES, sendedMsg.getNames());
        values.put(SendedMsg.COLUMN_NUMBERS, sendedMsg.getNumbers());
        context.getContentResolver().insert(SmsProvider.URI_SMS_ALL,values);
    }
}
