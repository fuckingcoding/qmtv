package com.example.acer.myzhibo.ui.fragment.live;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.LiveAdapter;
import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.utils.ToastHelper;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends Fragment  implements LiveContract.ILiveView,LiveAdapter.LiveOnClickListener{
   private Context mContext ;
   private LivePresenter presenter ;
    private TextView tv_title ;
    private ImageView iv_searvh;
    private List<LiveBean.DataBeanX> list ;
    private XRecyclerView recyclerView;
    private LiveAdapter  adapter ;
    public LiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        presenter = new LivePresenter(this);
        presenter.getZhiBoBean(UrlConfig.TOTALURL);
        recyclerView = (XRecyclerView) view.findViewById(R.id.recycler_livefragment);
        tv_title = (TextView) view.findViewById(R.id.tv_title_livefra);
        iv_searvh = (ImageView) view.findViewById(R.id.iv_search_livefragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initAdapter();


        iv_searvh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initAdapter() {

        adapter = new LiveAdapter(mContext,list,this);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    //直播BEAN的回调
    @Override
    public void getZhiBoData(LiveBean bean) {
         list = bean.getData();
        adapter.notifyDataSetChanged();
        int follow = bean.getData().get(0).getFollow();
        String view = bean.getData().get(0).getView();
        Log.e("TAG", "getZhiBoData: "+follow);
    }


     // 子项点击事件
    @Override
    public void onClick(int position) {

    }
}
