package com.example.acer.myzhibo.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.History;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;
import com.example.acer.myzhibo.utils.UIManager;

import java.util.List;

/**
 * Project Name:MyZhibo
 * Package Name:com.example.acer.myzhibo.adapter.recommend
 * File    Name:RecycleViewAdapter
 * Created by WYJ on 16/11/30.
 * Description :TODO
 */

public class HistoryRecycleViewAdapter extends RecyclerViewAdapterHelper<History> {
    public interface IOnItemClickListener{
        void onclick(int position);
    }
    private Context context;
    private InRecycleAdapter.IOnItemClickListener itemClickListener;

    public HistoryRecycleViewAdapter(Context context, List<History> list) {
        super(context, list);
        this.context=context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_live,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        final History guanZhu = mList.get(position);
        ((ViewHolder)holder).tv_title.setText(mList.get(position).getTitle());
        ((ViewHolder)holder).tv_name.setText(mList.get(position).getNick());
        ((ViewHolder)holder).tv_view.setText(mList.get(position).getView());
        Glide.with(mContext).load(mList.get(position).getThumb()).into(((ViewHolder)holder).iv_cover);
        Glide.with(mContext).load(mList.get(position).getAvatar()).transform(new BitmapCircleTransformation(mContext)).into(((ViewHolder) holder).iv_head);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIManager.startPlayActivity(mContext,guanZhu.getAvatar(),guanZhu.getTitle(),guanZhu.getNick(),guanZhu.getView(), String.valueOf(guanZhu.getUid()));
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_cover,iv_head;
        TextView tv_view,tv_name,tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_item_cover);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_item_head);
            tv_view = (TextView) itemView.findViewById(R.id.tv_item_cover);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_title);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_content);
        }
    }
}
