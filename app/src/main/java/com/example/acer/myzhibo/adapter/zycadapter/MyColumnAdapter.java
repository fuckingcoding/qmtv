package com.example.acer.myzhibo.adapter.zycadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.LanmuBean;
import com.example.acer.myzhibo.ui.zbactivity.ReusedActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class MyColumnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<LanmuBean> list ;
    private LayoutInflater mLayoutInflater;

    public MyColumnAdapter(Context mContext, List<LanmuBean> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_fragment_column,parent,false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Glide.with(mContext).load(list.get(position).getImage()).into(((MyViewHolder)holder).iv);
        ((MyViewHolder)holder).tv.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ReusedActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("slug",list.get(position).getSlug());
                intent.putExtra("name",list.get(position).getName());
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_item_column);
            tv = (TextView) itemView.findViewById(R.id.tv_item_column);
        }
    }
}
