package com.example.acer.myzhibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.TulingChatBean;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;

import java.util.List;

/**
 * Created by acer on 2016/11/27.
 */

public class TulingAdapter extends RecyclerViewAdapterHelper<TulingChatBean> {


    public TulingAdapter(Context context, List<TulingChatBean> list) {
        super(context, list);
    }


    @Override
    public int getItemViewType(int position) {
        switch (mList.get(position).getType()){

            case  0:
                return  0;
            case 1 :
                return  1;
        }
        return 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {

        if(viewType ==0) {
            View view = mInflater.inflate(R.layout.layout_tulingchat1,parent,false);
            return  new MyTulingViewHolder1(view);
        }else{
            View view  =mInflater.inflate(R.layout.layout_tulingchat2,parent,false);
            return  new MytulingViewHolder2(view);
        }


    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  MyTulingViewHolder1){
            ((MyTulingViewHolder1) holder).tv_tuling1.setText(mList.get(position).getContent());
        }else  if(holder instanceof  MytulingViewHolder2){
            ((MytulingViewHolder2) holder).tv_tuling2.setText(mList.get(position).getContent());
        }

    }

    class  MyTulingViewHolder1 extends RecyclerView.ViewHolder{
        TextView tv_tuling1 ;
        ImageView iv_tuling1;
        public MyTulingViewHolder1(View itemView) {
            super(itemView);
            tv_tuling1 = (TextView) itemView.findViewById(R.id.tv_tulingitem_1);
            iv_tuling1 = (ImageView) itemView.findViewById(R.id.iv_tulingitem_1);

        }
    }

    class  MytulingViewHolder2 extends RecyclerView.ViewHolder{

        TextView tv_tuling2 ;
        ImageView iv_tuling2;
        public MytulingViewHolder2(View itemView) {
            super(itemView);
            tv_tuling2 = (TextView) itemView.findViewById(R.id.tv_tulingitem_2);
            iv_tuling2 = (ImageView) itemView.findViewById(R.id.iv_tulingitem_2);
        }
    }
}
