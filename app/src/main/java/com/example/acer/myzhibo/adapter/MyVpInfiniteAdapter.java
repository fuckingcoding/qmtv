package com.example.acer.myzhibo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by My on 2016/10/10.
 */
public class MyVpInfiniteAdapter extends PagerAdapter {
    private List<ImageView> list;

    public MyVpInfiniteAdapter(List<ImageView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        if(list.size()!=0) {
            view = list.get(position % list.size());
            container.addView(view);
            return view;
        }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if(list.size()!=0) {
            container.removeView(list.get(position % list.size()));
        }
    }
}
