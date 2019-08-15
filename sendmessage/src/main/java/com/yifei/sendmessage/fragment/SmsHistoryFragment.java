package com.yifei.sendmessage.fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.yifei.sendmessage.R;
import com.yifei.sendmessage.bean.SendedMsg;
import com.yifei.sendmessage.db.SmsProvider;
import com.yifei.sendmessage.view.FlowLayout;

public class SmsHistoryFragment extends ListFragment {

    public static final int LOADER_ID = 1;
    private LayoutInflater mInflater;
    private CursorAdapter mCursorAdaper;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInflater = LayoutInflater.from(getActivity());
        initLoader();
        setupListAdapter();
    }



    private void initLoader() {
        getLoaderManager().initLoader(LOADER_ID, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
                CursorLoader loader = new CursorLoader(getActivity(), SmsProvider.URI_SMS_ALL,
                        null, null, null, null);
                return loader;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (loader.getId() == LOADER_ID) {
                    mCursorAdaper.swapCursor(cursor);
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                mCursorAdaper.swapCursor(null);
            }
        });
    }

    private void setupListAdapter() {
        mCursorAdaper = new CursorAdapter(getActivity(),null,false) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {

                return   mInflater.inflate(R.layout.item_sended_msg,parent,false);
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView tv_msg = view.findViewById(R.id.id_tv_msg);
                FlowLayout fl = view.findViewById(R.id.id_flowlayout_contacts);
                TextView fest = view.findViewById(R.id.id_text_test);
                TextView date = view.findViewById(R.id.id_text_data);
                tv_msg.setText(cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_MSG)));
                fest.setText(cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_FESTIVALNAME)));
                date.setText(cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_DATE)));

                String names= cursor.getString(cursor.getColumnIndex(SendedMsg.COLUMN_NAMES));
                names= names.substring(0,names.length()-1);
                if(TextUtils.isEmpty(names)){
                    return;
                }
                fl.removeAllViews();
                for (String name:names.split(",")){
                    addTag(name,fl);
                }

            }
        };
        setListAdapter(mCursorAdaper);
    }

    private void addTag(String name, FlowLayout fl) {
        TextView tv = (TextView) mInflater.inflate(R.layout.tag, fl, false);
        tv.setText(name);
        fl.addView(tv);
    }
}
