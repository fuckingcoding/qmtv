package com.example.acer.myzhibo.adapter.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.RecomBean;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by apple on 16/11/24.
 * Eemil:635727195@qq.com
 */

public class InRecycleAdapter extends RecyclerViewAdapterHelper<RecomBean.RoomBean.ListBean> {
    public interface IOnItemClickListener{
        void onclick(int position);
    }
    private Context context;
    private IOnItemClickListener itemClickListener;
    public InRecycleAdapter(Context context, List<RecomBean.RoomBean.ListBean> list,IOnItemClickListener itemClickListener) {
        super(context,list);
        this.context=context;
        this.itemClickListener=itemClickListener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_item_live,parent,false);
        return new ViewHolder1(view);
    }


    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, final int position) {



        ((ViewHolder1)holder).tv_title.setText(mList.get(position).getTitle());
        ((ViewHolder1)holder).tv_name.setText(mList.get(position).getNick());
        ((ViewHolder1)holder).tv_view.setText(mList.get(position).getView());
        Glide.with(mContext).load(mList.get(position).getThumb()).into(((ViewHolder1)holder).iv_cover);
        Glide.with(mContext).load(mList.get(position).getAvatar()).transform(new BitmapCircleTransformation(mContext)).into(((ViewHolder1) holder).iv_head);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onclick(position);
            }
        });

    }
    class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView iv_cover,iv_head;
        TextView tv_view,tv_name,tv_title;
        public ViewHolder1(View itemView) {
            super(itemView);
            iv_cover = (ImageView) itemView.findViewById(R.id.iv_item_cover);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_item_head);
            tv_view = (TextView) itemView.findViewById(R.id.tv_item_cover);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_title);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_content);
        }
    }

}
