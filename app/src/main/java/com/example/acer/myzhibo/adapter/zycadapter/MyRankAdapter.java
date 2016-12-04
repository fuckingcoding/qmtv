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
import com.example.acer.myzhibo.bean.RankTotalBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class MyRankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContent;
    private int[] icon;
    private List<RankTotalBean> rankTotal;
    private LayoutInflater mLayoutInflater;

    public MyRankAdapter(Context mContent, int[] icon, List<RankTotalBean> rankTotal) {
        this.mContent = mContent;
        this.icon = icon;
        this.rankTotal = rankTotal;
        mLayoutInflater = LayoutInflater.from(mContent);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_rank_fragment, parent, false);
        MyViewHolder vh = new MyViewHolder(inflate);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder)holder).iv_rank.setImageResource(icon[position]);
        Glide.with(mContent).load(rankTotal.get(position).getIconUrl()).into(((MyViewHolder)holder).iv_icon);
        ((MyViewHolder)holder).tv_name.setText(rankTotal.get(position).getSendNick());
        ((MyViewHolder)holder).tv_love.setText(rankTotal.get(position).getRank());

    }

    @Override
    public int getItemCount() {
        return rankTotal.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_rank,iv_icon,iv_heart;
        TextView tv_name,tv_love;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_love = (TextView) itemView.findViewById(R.id.tv_heart_count);
            iv_rank = (ImageView) itemView.findViewById(R.id.iv_rank);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            iv_heart = (ImageView) itemView.findViewById(R.id.iv_heart);
        }
    }
}
