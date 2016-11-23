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
import com.example.acer.myzhibo.bean.DataBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.ui.PlayActivity;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;

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
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load(data.get(position).getThumb()).into(holder.iv_cover);
        Glide.with(mContext).load(data.get(position).getAvatar()).transform(new BitmapCircleTransformation(mContext)).into(holder.iv_head);
        holder.tv_view.setText(data.get(position).getView());
        holder.tv_title.setText(data.get(position).getTitle());
        holder.tv_name.setText(data.get(position).getNick());
        holder.iv_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayActivity.class);
                intent.putExtra(Constant.UID,data.get(position).getUid());
                intent.putExtra(Constant.AVATAR,data.get(position).getAvatar());
                intent.putExtra(Constant.VIEW,data.get(position).getView());
                intent.putExtra(Constant.TITLE,data.get(position).getTitle());
                intent.putExtra(Constant.NICK,data.get(position).getNick());
                mContext.startActivity(intent);
            }
        });

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
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_item_cover);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_item_head);
            tv_view = (TextView) itemView.findViewById(R.id.tv_item_cover);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_title);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_content);
        }
    }

}
