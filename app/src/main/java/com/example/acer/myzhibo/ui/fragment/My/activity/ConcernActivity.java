package com.example.acer.myzhibo.ui.fragment.My.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.recommend.RecycleViewAdapter;
import com.example.acer.myzhibo.bean.GuanZhu;
import com.example.acer.myzhibo.database.PreUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ConcernActivity extends AppCompatActivity {
    private Context mContext=this;
    private XRecyclerView mxRecyclerView;
    private GridLayoutManager layoutManager;
    private RecycleViewAdapter adapter;
    private List<GuanZhu> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concern);
        initView();
        initData();
    }

    private void initData() {
        String name= PreUtils.readStrting(mContext,"username");
        BmobQuery<GuanZhu> query = new BmobQuery<GuanZhu>();
        query.addWhereEqualTo("name", name);
        query.findObjects(new FindListener<GuanZhu>() {
            @Override
            public void done(List<GuanZhu> object,BmobException e) {
                if(e==null){
                    list=object;
                    adapter.reloadRecyclerView(list,false);
                }else{
                }
            }
        });
    }

    private void initView() {
        mxRecyclerView=(XRecyclerView)findViewById(R.id.xrecycleview_guanzhu);
        layoutManager=new GridLayoutManager(mContext,2);
        mxRecyclerView.setLayoutManager(layoutManager);
        mxRecyclerView.setHasFixedSize(true);
        adapter=new RecycleViewAdapter(mContext,list);
        mxRecyclerView.setAdapter(adapter);
    }
}
