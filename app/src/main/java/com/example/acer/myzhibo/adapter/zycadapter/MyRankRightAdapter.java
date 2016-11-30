package com.example.acer.myzhibo.adapter.zycadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.RankWeekBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class MyRankRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContent;
    private int[] change;
    private int[] icon;
    private List<RankWeekBean> rankWeek;
    private LayoutInflater mLayoutInflater;

    public MyRankRightAdapter(int[] change, Context mContent, List<RankWeekBean> rankWeek,int[] icon) {
        this.change = change;
        this.mContent = mContent;
        this.rankWeek = rankWeek;
        this.icon = icon;
        mLayoutInflater = LayoutInflater.from(mContent);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_rank_fragment, parent, false);
        MyViewHolder1 vh = new MyViewHolder1(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder1)holder).iv_rank.setImageResource(icon[position]);
        Glide.with(mContent).load(rankWeek.get(position).getIconUrl()).into(((MyViewHolder1)holder).iv_icon);
        ((MyViewHolder1)holder).tv_name.setText(rankWeek.get(position).getSendNick());
        if(rankWeek.get(position).getChange().equals("up")){
            ((MyViewHolder1)holder).iv_heart.setImageResource(change[0]);
        }else if(rankWeek.get(position).getChange().equals("equal")){
            ((MyViewHolder1)holder).iv_heart.setImageResource(change[1]);
        }else{
            ((MyViewHolder1)holder).iv_heart.setImageResource(change[2]);
        }


    }

    @Override
    public int getItemCount() {
        return rankWeek.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder{
        ImageView iv_rank,iv_icon,iv_heart;
        TextView tv_name,tv_love;
        public MyViewHolder1(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_love = (TextView) itemView.findViewById(R.id.tv_heart_count);
            iv_rank = (ImageView) itemView.findViewById(R.id.iv_rank);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            iv_heart = (ImageView) itemView.findViewById(R.id.iv_heart);
        }
    }
}
