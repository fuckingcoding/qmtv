package com.example.acer.myzhibo.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.Mainadapter;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.ui.fragment.recommend.RecomFirstFragment;
import com.example.acer.myzhibo.ui.fragment.recommend.RecommendFragmentList;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Mainadapter mainadapter;
    public static final String [] TABS={"推荐","测试2","测试3","测试4","测试5","测试6","测试7","测试8"};
    private List<Fragment> refragmentList=new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recommend, container, false);
       initView(view);
        return view;
    }

    private void initView(View view) {
        initFragment(view);
        mViewPager=(ViewPager)view.findViewById(R.id.viewpager_recommendfragment);
        mainadapter=new Mainadapter(getChildFragmentManager(),refragmentList,TABS);
        mViewPager.setAdapter(mainadapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout=(TabLayout)view.findViewById(R.id.tablayout_recommendfragment);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    private void initFragment(View view) {
        RecomFirstFragment recomFirstFragment=new RecomFirstFragment();
        refragmentList.add(recomFirstFragment);
        for (int i = 1; i < TABS.length; i++) {
            RecommendFragmentList recommendFragmentList=new RecommendFragmentList();
           // refragmentList.clear();
            Bundle bundle=new Bundle();
            bundle.putString(Constant.KEY_RECOMMEND_URL_KEY,"SSS");
           recommendFragmentList.setArguments(bundle);
            refragmentList.add(recommendFragmentList);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
