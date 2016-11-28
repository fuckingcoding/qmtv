package com.example.acer.myzhibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.ChatBean;
import com.example.acer.myzhibo.utils.BaseAdapterHelper;

import java.util.List;

/**
 * Created by acer on 2016/11/26.
 */

public class ChatAdapter extends BaseAdapterHelper<ChatBean> {


    public ChatAdapter(Context context, List<ChatBean> list) {
        super(context, list);
    }


    @Override
    public View getItemView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder vh = null;
        if(convertView==null){
            vh = new BaseViewHolder();
            convertView =mInflater.inflate(R.layout.layout_list_chat,parent,false);
            vh.tv_content = (TextView) convertView.findViewById(R.id.tv_content_chat);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name_chat);
            convertView.setTag(vh);

        }else {
             vh = (BaseViewHolder) convertView.getTag();
        }
        vh.tv_name.setText(mList.get(position).getUsername()+":");
        vh.tv_content.setText(mList.get(position).getContent());

        return convertView;
    }

    class BaseViewHolder{
        TextView tv_name ,tv_content;
    }
}
