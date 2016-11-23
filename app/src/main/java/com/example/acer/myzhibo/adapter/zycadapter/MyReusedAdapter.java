package com.example.acer.myzhibo.adapter.zycadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.acer.myzhibo.R;
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
        View view = mLayoutInflater.inflate(R.layout.layout_item_live,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_cover,iv_head;
        TextView tv_view,tv_name,tv_title;
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
