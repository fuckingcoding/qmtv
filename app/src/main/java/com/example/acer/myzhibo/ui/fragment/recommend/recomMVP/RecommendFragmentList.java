package com.example.acer.myzhibo.ui.fragment.recommend.recomMVP;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.LiveAdapter;
import com.example.acer.myzhibo.adapter.recommend.ReRecycleViewAdapter;
import com.example.acer.myzhibo.bean.DataBean;
import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.ui.fragment.live.LiveContract;
import com.example.acer.myzhibo.ui.fragment.live.LivePresenter;
import com.example.acer.myzhibo.utils.UIManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragmentList extends Fragment implements RecommendContract.IRecommendView,ReRecycleViewAdapter.IOnItemClickListener,LiveContract.ILiveView,LiveAdapter.LiveOnClickListener{
    private static final String TAG = "RecommendFragmentList";
    private Context mContext;
    private RecyclerView mRecycleView;
    private GridLayoutManager mlayoutManager;
    private ReRecycleViewAdapter adapter;
    private RecommendContract.IRecommendPresenter presenter;
    private String title;
    public Map<String,String> allmap=new HashMap<>();
    //全部频道
    public static final String[] AllCHALLEL= UrlConfig.ColumnName;
    private List<String> allList;
    //全部频道的拼音
    public static final String [] ALL_CHALLEL_PY=UrlConfig.URLSTRING;
    private List<String> allpyList;

    private String pinyin;

    private List<DataBean> data=new ArrayList<>();

    private LivePresenter presenter1 ;
    private List<LiveBean.DataBeanX> data1=new ArrayList<>();
    private LiveAdapter adapter1 ;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        //全部频道转成list
        allList=new ArrayList<>();
        Collections.addAll(allList,AllCHALLEL);
        //全部频道转换为拼音
        allpyList=new ArrayList<>();
        Collections.addAll(allpyList,ALL_CHALLEL_PY);
        for (int i = 0; i < allList.size(); i++) {
            allmap.put(allList.get(i),allpyList.get(i));
        }
        Bundle arguments = getArguments();
        title = arguments.getString(Constant.KEY_RECOMMEND_URL_KEY);
        if(!title.equals("全部")){
            pinyin = allmap.get(title);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recommend_fragment_list, container, false);
        initRecycleView(view);
        return view;
    }

    private void initRecycleView(View view) {
        mRecycleView=(RecyclerView)view.findViewById(R.id.recycleview_recommendfragList);
        mRecycleView.setHasFixedSize(true);
        //设置布局为竖直
        mlayoutManager=new GridLayoutManager(mContext,2);
        mRecycleView.setLayoutManager(mlayoutManager);
        if(title=="全部"){
            adapter1=new LiveAdapter(mContext,data1,this);
            mRecycleView.setAdapter(adapter1);
        }else{
            adapter=new ReRecycleViewAdapter(mContext,data,this);
            mRecycleView.setAdapter(adapter);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(title=="全部"){
            presenter1 = new LivePresenter(this);
            presenter1.getZhiBoBean(UrlConfig.TOTALURL);
        }else {
            presenter = new RecommendPresenter(this);
            presenter.getQMBean(pinyin);
        }
    }

    @Override
    public void getQMData(QMBean bean) {
        this.data.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onclick(int position) {
        DataBean dataBean = data.get(position);
        UIManager.startPlayActivity(mContext, dataBean.getAvatar(), dataBean.getTitle(), dataBean.getNick(), dataBean.getView(), dataBean.getUid());
    }

    @Override
    public void getZhiBoData(LiveBean bean) {
        this.data1.addAll(bean.getData());
        adapter1.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position) {

        LiveBean.DataBeanX beanX = data1.get(position);
        UIManager.startPlayActivity(mContext,beanX.getAvatar(),beanX.getTitle(),beanX.getNick(),beanX.getView(),beanX.getUid());
    }
}
