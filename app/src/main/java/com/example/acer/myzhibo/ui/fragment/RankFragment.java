package com.example.acer.myzhibo.ui.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.zycadapter.HotEvent;
import com.example.acer.myzhibo.adapter.zycadapter.MyRankAdapter;
import com.example.acer.myzhibo.adapter.zycadapter.MyRankRightAdapter;
import com.example.acer.myzhibo.bean.RankBean;
import com.example.acer.myzhibo.bean.RankCurrBean;
import com.example.acer.myzhibo.bean.RankTotalBean;
import com.example.acer.myzhibo.bean.RankWeekBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends Fragment {
    private TextView tv_left,tv_right;
    private RecyclerView mRecyclerView;
    private int[] icon = {R.mipmap.top1,R.mipmap.top2,R.mipmap.top3,R.mipmap.top4,
            R.mipmap.top5,R.mipmap.top6,R.mipmap.top7,R.mipmap.top8,R.mipmap.top9,R.mipmap.top10};
    private int[] change = {R.mipmap.ic_sp_player_paihang_zb_up,R.mipmap.ic_sp_player_paihang_zb_ping,R.mipmap.ic_sp_player_paihang_zb_down};
    private List<RankCurrBean> rankCurr = new ArrayList<>();
    private List<RankWeekBean> rankWeek = new ArrayList<>();
    private List<RankTotalBean> rankTotal = new ArrayList<>();
    private List<String> str = new ArrayList<>();
    private MyRankAdapter mra;
    private MyRankRightAdapter mrra;
    private String s;


    public RankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        s = bundle.getString("room");
        initData();
        View rootView = inflater.inflate(R.layout.fragment_rank, container, false);
        return rootView;
    }


    private void initData() {
        String playurl = String.format(Constant.RANKURL, s);
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<RankBean> room = retrofitInterface.getRankBean(playurl);
        room.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RankBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(RankBean rankBean) {
                        rankWeek.addAll(rankBean.rankWeek);
                        rankTotal.addAll(rankBean.rankTotal);
                        rankCurr.addAll(rankBean.rankCurr);
                        str.addAll(rankBean.hotWord);
                        EventBus.getDefault().post(new HotEvent(str));
                        mra.notifyDataSetChanged();
                        mrra.notifyDataSetChanged();
                    }
                });



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View rootView) {
        tv_left = (TextView) rootView.findViewById(R.id.tv_rank_left);
        tv_right = (TextView) rootView.findViewById(R.id.tv_rank_right);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_rank);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mra = new MyRankAdapter(getActivity(),icon,rankTotal);
        mrra = new MyRankRightAdapter(change,getActivity(),rankWeek,icon);
        mRecyclerView.setAdapter(mra);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.setAdapter(mra);
                tv_left.setBackgroundResource(R.drawable.textview_left);
                tv_left.setTextColor(Color.WHITE);
                tv_right.setBackgroundResource(R.drawable.textview_right);
                tv_right.setTextColor(Color.RED);

            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.setAdapter(mrra);
                tv_left.setBackgroundResource(R.drawable.text_left_choose);
                tv_left.setTextColor(Color.RED);
                tv_right.setBackgroundResource(R.drawable.text_right_choose);
                tv_right.setTextColor(Color.WHITE);
            }
        });

    }


}
