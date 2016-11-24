package com.example.acer.myzhibo.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.recommend.Mainadapter2;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.ui.fragment.recommend.RecomFirstFragment;
import com.example.acer.myzhibo.ui.fragment.recommend.recomMVP.RecommendFragmentList;
import com.example.acer.myzhibo.ui.fragment.recommend.demochannel.ChannelActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment {
    private static final String TAG = "RecommendFragment";
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Mainadapter2 mainadapter2;

    private List<Fragment> refragmentList=new ArrayList<>();
    //tab标题
    public static final String [] TABS={"推荐","全部"};
    private List<String> tabList;
    //常用频道
    public static final String [] ALWAYS=UrlConfig.DefultName;
    private List<String> normallist;
    //全部频道
    public static final String[] AllCHALLEL=UrlConfig.ColumnName;
    private List<String> allList;
    //全部频道的拼音
    public static final String [] ALL_CHALLEL_PY=UrlConfig.URLSTRING;
    private List<String> allpyList;
    public Map<String,String> allmap=new HashMap<>();

    private  RecommendFragmentList recommendFragmentList;
    //tablayout上按钮
    private Button  Btn_PD;
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
        initTabData();
       initView(view);

        initUrl();
        return view;
    }

   

    private void initTabData() {
        normallist=new ArrayList<>();
        Collections.addAll(normallist,ALWAYS);
        //常用频道转成list
        tabList=new ArrayList<>();
        Collections.addAll(tabList,TABS);
        tabList.addAll(normallist);





        //全部频道转成list
        allList=new ArrayList<>();
        Collections.addAll(allList,AllCHALLEL);
        //全部频道转换为拼音
        allpyList=new ArrayList<>();
        Collections.addAll(allpyList,ALL_CHALLEL_PY);
        for (int i = 0; i < allList.size(); i++) {
            allmap.put(allList.get(i),allpyList.get(i));
        }


    }

    private void initView(View view) {
        initFragment(view);
        mViewPager=(ViewPager)view.findViewById(R.id.viewpager_recommendfragment);
        mainadapter2=new Mainadapter2(getChildFragmentManager(),refragmentList,tabList);
        mViewPager.setAdapter(mainadapter2);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout=(TabLayout)view.findViewById(R.id.tablayout_recommendfragment);
        mTabLayout.setupWithViewPager(mViewPager);
        Btn_PD=(Button)view.findViewById(R.id.btn_recomm_pindap);
        Btn_PD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, ChannelActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initFragment(View view) {
        RecomFirstFragment recomFirstFragment=new RecomFirstFragment();
        refragmentList.add(recomFirstFragment);
        for (int i = 1; i < tabList.size(); i++) {
        recommendFragmentList=new RecommendFragmentList();
                 //refragmentList.clear();
               Bundle bundle=new Bundle();
               bundle.putString(Constant.KEY_RECOMMEND_URL_KEY,tabList.get(i));
            Log.e(TAG, "initFragment: "+tabList.get(i) );
                recommendFragmentList.setArguments(bundle);
                refragmentList.add(recommendFragmentList);
            }
        }
    private void initUrl() {
//        for (int i = 1; i < refragmentList.size()-1; i++) {
//             Bundle bundle=new Bundle();
//             bundle.putString(Constant.KEY_RECOMMEND_URL_KEY,normalList.get(i));
//            recommendFragmentList.setArguments(bundle);
//        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
