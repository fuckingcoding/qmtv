package com.example.acer.myzhibo.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.DataBean;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;

import java.util.List;



/**
 * Created by apple on 16/11/23.
 * Eemil:635727195@qq.com
 */

public class ReRecycleViewAdapter extends RecyclerViewAdapterHelper<DataBean> {
    public interface IOnItemClickListener{
        void onclick(int position);
    }
    private Context context;

    private IOnItemClickListener itemClickListener;
    public ReRecycleViewAdapter(Context context, List<DataBean> list,IOnItemClickListener itemClickListener) {
        super(context,list);
        this.context=context;
        this.itemClickListener=itemClickListener;
    }
    public ReRecycleViewAdapter(Context context, List<DataBean> list) {
        super(context,list);
        this.context=context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_live,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, final int position) {

            Glide.with(mContext).load(mList.get(position).getThumb()).into(((MyViewHolder) holder).iv_cover);
            Glide.with(mContext).load(mList.get(position).getAvatar()).transform(new BitmapCircleTransformation(mContext)).into(((MyViewHolder) holder).iv_head);
            ((MyViewHolder) holder).tv_view.setText(mList.get(position).getView());
            ((MyViewHolder) holder).tv_title.setText(mList.get(position).getTitle());
            ((MyViewHolder) holder).tv_name.setText(mList.get(position).getNick());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onclick(position);
            }
        });

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
