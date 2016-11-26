package com.example.acer.myzhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.PostSearchBean;
import com.example.acer.myzhibo.bean.SearchBean;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.NumberChangeUtils;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by acer on 2016/11/23.
 */

public class SearchAdapter extends RecyclerViewAdapterHelper<SearchBean.DataBean.ItemsBean> {
   private  SearchOnClickListener listener;
    public SearchAdapter(Context context, List<SearchBean.DataBean.ItemsBean> list,SearchOnClickListener listener) {
        super(context, list);
        this.listener = listener;
    }

    public  interface SearchOnClickListener{
        void onClick(int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        View view  = mInflater.inflate(R.layout.layout_item_live,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder)holder).tv_title.setText(mList.get(position).getNick());
        ((MyViewHolder)holder).tv_content.setText(mList.get(position).getTitle());

        String view = mList.get(position).getView();
        String numchange = NumberChangeUtils.numchange(view);
        ((SearchAdapter.MyViewHolder)holder).tv_view.setText(numchange);

        String avatar = mList.get(position).getAvatar();
        Log.e("TAG", "onBindMyViewHolder: "+avatar );
        String thumb = mList.get(position).getThumb();
        Glide.with(mContext).load(avatar).asBitmap().placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher) .transform(new BitmapCircleTransformation(mContext))
                .into(((MyViewHolder)holder).iv_head);
        Glide.with(mContext).load(thumb).asBitmap().placeholder(R.mipmap.ic_launcher)//TODO 给一个默认头像
                .error(R.mipmap.ic_launcher) .into(((MyViewHolder)holder).iv_cover);

        if(mList.get(position).isPlay_status()){
            ((MyViewHolder)holder).tv_right.setVisibility(View.VISIBLE);
        }
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  listener.onClick(position);
              }
          });
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title, tv_content ,tv_view,tv_right;
        ImageView iv_cover ,iv_head;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_item_content);
            tv_view = (TextView) itemView.findViewById(R.id.tv_item_cover);
            iv_cover = (ImageView) itemView .findViewById(R.id.iv_item_cover);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_item_head);
            tv_right = (TextView) itemView.findViewById(R.id.tv_item_right);
        }
    }
}
