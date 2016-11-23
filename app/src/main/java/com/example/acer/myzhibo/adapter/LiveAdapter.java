package com.example.acer.myzhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class LiveAdapter extends RecyclerViewAdapterHelper<LiveBean> {


    public LiveAdapter(Context context, List<LiveBean> list) {
        super(context, list);
    }

    

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
