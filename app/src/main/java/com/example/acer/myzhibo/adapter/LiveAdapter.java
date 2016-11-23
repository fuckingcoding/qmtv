package com.example.acer.myzhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class LiveAdapter extends RecyclerViewAdapterHelper<LiveBean> {
   private LiveOnClickListener listener;

    public LiveAdapter(Context context, List<LiveBean> list, LiveOnClickListener listener) {
        super(context, list);
        this.listener = listener;
    }

    interface LiveOnClickListener{
        void onClick(int position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_content ,tv_view;
        ImageView iv_cover ,iv_head;
        public MyViewHolder(View itemView) {
            super(itemView);

            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}
