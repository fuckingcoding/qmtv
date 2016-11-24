package com.example.acer.myzhibo.adapter.recommend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by apple on 16/10/5.
 * Eemil:635727195@qq.com
 */
public class Mainadapter2 extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    private List<String> list;
    public Mainadapter2(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;

    }

    public Mainadapter2(FragmentManager fm, List<Fragment> fragmentList, List<String> list) {
        super(fm);
        this.fragmentList=fragmentList;
        this.list=list;
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
        return list.get(position);
    }

}
