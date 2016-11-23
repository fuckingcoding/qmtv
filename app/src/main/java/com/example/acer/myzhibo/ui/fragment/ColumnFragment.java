package com.example.acer.myzhibo.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.zycadapter.MyColumnAdapter;
import com.example.acer.myzhibo.bean.RecommendBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;
import com.example.acer.myzhibo.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColumnFragment extends Fragment {
    private Context mContent ;
    private RecyclerView recyclerView;
    private List<RecommendBean> list = new ArrayList<>();
    private MyColumnAdapter mycolumnadapter;




    public ColumnFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContent = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initData();
        View rootView = inflater.inflate(R.layout.fragment_column, container, false);

        initView(rootView);
        return rootView;
    }



    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IRetrofitInterface iRetrofitInterface = retrofit.create(IRetrofitInterface.class);
        Observable<List<RecommendBean>> column = iRetrofitInterface.getColumn(UrlConfig.COLUMN_URL);
        column.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<RecommendBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<RecommendBean> recommendBeen) {
                        list.addAll(recommendBeen);
                        mycolumnadapter.notifyDataSetChanged();

                    }
                });

    }

    private void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_column_recyclerview);
        mycolumnadapter = new MyColumnAdapter(mContent,list);
        GridLayoutManager gridlayout = new GridLayoutManager(mContent,3, LinearLayoutManager.VERTICAL,false);
        recyclerView.addItemDecoration(new DividerGridItemDecoration(mContent));
        recyclerView.setLayoutManager(gridlayout);
        recyclerView.setAdapter(mycolumnadapter);
    }

}
