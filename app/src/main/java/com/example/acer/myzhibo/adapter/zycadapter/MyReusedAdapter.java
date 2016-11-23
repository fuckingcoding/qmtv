package com.example.acer.myzhibo.adapter.zycadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.bean.DataBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class MyReusedAdapter extends RecyclerView.Adapter<MyReusedAdapter.MyViewHolder> {
    private Context mContext;
    private List<DataBean> data;
    private LayoutInflater mLayoutInflater;

    public MyReusedAdapter(Context mContext, List<DataBean> data) {
        this.mContext = mContext;
        this.data = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
