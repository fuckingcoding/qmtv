package com.example.acer.myzhibo.ui.fragment.recommend.recomMVP;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.recommend.ReRecycleViewAdapter;
import com.example.acer.myzhibo.bean.DataBean;
import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.utils.ToastHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragmentList extends Fragment implements RecommendContract.IRecommendView,ReRecycleViewAdapter.IOnItemClickListener{
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;



    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

        Log.e("123", "onCreate: "+allmap.size() );
        Log.e("123", "onCreate: "+title );
        Log.e("123", "onCreate: "+pinyin );

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
        pinyin = allmap.get(title);
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
        adapter=new ReRecycleViewAdapter(mContext,data,this);
        mRecycleView.setAdapter(adapter);




    }


    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new RecommendPresenter(this);
        presenter.getQMBean(pinyin);
    }

    @Override
    public void getQMData(QMBean bean) {

        this.data.addAll(bean.getData());
        Log.i(TAG, "getQMData: "+ this.data.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onclick(int position) {
        ToastHelper.showToast(mContext,"点击的是"+data.get(position).getTitle());
    }
}
