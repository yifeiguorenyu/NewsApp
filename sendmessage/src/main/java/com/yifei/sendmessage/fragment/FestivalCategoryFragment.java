package com.yifei.sendmessage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.yifei.sendmessage.ChooseMessageActivity;
import com.yifei.sendmessage.R;
import com.yifei.sendmessage.bean.Festival;
import com.yifei.sendmessage.bean.FestivalLab;

public class FestivalCategoryFragment extends Fragment {

    public static final String FESTIVAL_ID = "festivalId";
    private GridView mGridView;
    private ArrayAdapter<Festival> mAdapater;
    private LayoutInflater mInflater;
    private static final String TAG = "FestivategoryFragmen-1";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.frament_festival_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mGridView = view.findViewById(R.id.gv_festival);
        mInflater = LayoutInflater.from(getActivity());
        FestivalLab festivalLab = FestivalLab.getInstance();
        mAdapater = new ArrayAdapter<Festival>(getActivity(), -1, festivalLab.getFestivals()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.item_festival, parent, false);
                }
                TextView tv = convertView.findViewById(R.id.tv_festival);
                tv.setText(getItem(position).getName());
                return convertView;
            }
        };

        mGridView.setAdapter(mAdapater);
        
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ChooseMessageActivity.class);
                intent.putExtra(FESTIVAL_ID,mAdapater.getItem(position).getId());
                startActivity(intent);
            }
        });
        
        
    }


}
