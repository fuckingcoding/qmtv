package com.example.acer.myzhibo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by apple on 16/10/5.
 * Eemil:635727195@qq.com
 */
public class Mainadapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    private String tabs[];
    public Mainadapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;

    }

    public Mainadapter(FragmentManager fm, List<Fragment> fragmentList, String tabs[]) {
        super(fm);
        this.fragmentList=fragmentList;
        this.tabs=tabs;
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
        return tabs[position];
    }

}
