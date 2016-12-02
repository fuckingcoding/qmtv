package com.example.acer.myzhibo.adapter.zycadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.ui.Listener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class MyListViewHotAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> hot;
    private Listener lis;

    public MyListViewHotAdapter(Context mContext, List<String> hot) {
        this.mContext = mContext;
        this.hot = hot;
    }

    public MyListViewHotAdapter(Context mContext, List<String> hot, Listener lis) {
        this.mContext = mContext;
        this.hot = hot;
        this.lis=lis;
    }

    @Override
    public int getCount() {
        return hot.size();
    }

    @Override
    public Object getItem(int i) {
        return hot.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_word,null);
        TextView tv= (TextView) view.findViewById(R.id.tv_hot_word);
        if(hot!=null){
            tv.setText(hot.get(i));
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lis.send(hot.get(i));
                }
            });
        }
        return view;
    }
}
