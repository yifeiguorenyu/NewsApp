package com.yifei.sendmessage.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.yifei.sendmessage.bean.SendedMsg;

public class SmsProvider extends ContentProvider {
    private static final String AUTHORITY = "com.imooc.sms.provider.SmsProvider";
    public static final Uri URI_SMS_ALL = Uri.parse("content://" + AUTHORITY + "/sms");
    private static UriMatcher matcher;

    private static final int SMS_ALL = 0;
    private static final int SMS_ONE = 1;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "sms", SMS_ALL);
        matcher.addURI(AUTHORITY, "sms/#", SMS_ONE);
    }

    private SmsDbOpenHelper mHelper;
    private SQLiteDatabase mDb;

    @Override
    public boolean onCreate() {
        mHelper = SmsDbOpenHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        int match = matcher.match(uri);
        switch (match) {
            case SMS_ALL:
                break;
            case SMS_ONE:
                long id = ContentUris.parseId(uri);
                selection = "_id = ?";
                selectionArgs = new String[]{String.valueOf(id)};
                break;
            default:
                throw new IllegalArgumentException("wrong uri " + uri);
        }
        mDb = mHelper.getReadableDatabase();
        Cursor curson = mDb.query(SendedMsg.TABLE_NAME, projection, selection, selectionArgs, null
                , null, sortOrder);
        curson.setNotificationUri(getContext().getContentResolver(),URI_SMS_ALL);
        return curson;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int match = matcher.match(uri);
        if (match != SMS_ALL) {
            throw new IllegalArgumentException("wrong uri " + uri);
        }
        mDb = mHelper.getWritableDatabase();
        long rowId = mDb.insert(SendedMsg.TABLE_NAME, null, values);
        if (rowId > 0) {
            notifyDataSetChanged();
        }

        return ContentUris.withAppendedId(uri, rowId);
    }

    private void notifyDataSetChanged() {
        getContext().getContentResolver().notifyChange(URI_SMS_ALL, null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
