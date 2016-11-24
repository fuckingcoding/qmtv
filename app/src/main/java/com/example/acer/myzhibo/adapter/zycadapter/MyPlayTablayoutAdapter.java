package com.example.acer.myzhibo.adapter.zycadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class MyPlayTablayoutAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> listtitle;
    public MyPlayTablayoutAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> listtitle) {
        super(fm);
        this.fragmentList = fragmentList;
        this.listtitle = listtitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listtitle.get(position);
    }
}
