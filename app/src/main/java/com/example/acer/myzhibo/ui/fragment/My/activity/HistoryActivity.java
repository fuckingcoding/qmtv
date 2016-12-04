package com.example.acer.myzhibo.ui.fragment.My.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.recommend.HistoryRecycleViewAdapter;
import com.example.acer.myzhibo.bean.History;
import com.example.acer.myzhibo.database.PreUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HistoryActivity extends AppCompatActivity {
    private Context mContext=this;
    private XRecyclerView mxRecyclerView;
    private GridLayoutManager layoutManager;
    private HistoryRecycleViewAdapter adapter;
    private List<History> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        initData();
    }
    private void initData() {
        String name= PreUtils.readStrting(mContext,"username");
        BmobQuery<History> query = new BmobQuery<History>();
        query.addWhereEqualTo("name", name);
        query.findObjects(new FindListener<History>() {
            @Override
            public void done(List<History> object,BmobException e) {
                if(e==null){
                    list=object;
                    adapter.reloadRecyclerView(list,false);
                }else{
                }
            }
        });
    }
    private void initView() {
        mxRecyclerView=(XRecyclerView)findViewById(R.id.xrecycleview_history);
        layoutManager=new GridLayoutManager(mContext,2);
        mxRecyclerView.setLayoutManager(layoutManager);
        mxRecyclerView.setHasFixedSize(true);
        adapter=new HistoryRecycleViewAdapter(mContext,list);
        mxRecyclerView.setAdapter(adapter);
    }
}
