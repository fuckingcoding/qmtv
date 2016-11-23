package com.example.acer.myzhibo.ui.fragment.recommend.demochannel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.ui.fragment.recommend.helper.ItemDragHelperCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 频道 增删改查 排序
 * Created by YoKeyword on 15/12/29.
 */
public class ChannelActivity extends AppCompatActivity {

    private RecyclerView mRecy;

    public static final String []OtherName= UrlConfig.OtherName;
    private List<String> allList;
    public static final String []DefultName=UrlConfig.DefultName;
    private List<String> defultlist;
    private Boolean isChanened;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        //全部频道转成list
        allList=new ArrayList<>();
        Collections.addAll(allList,OtherName);
        //默认频道转成list
        defultlist=new ArrayList<>();
        Collections.addAll(defultlist,DefultName);
        mRecy = (RecyclerView) findViewById(R.id.recy);
        init();
    }

    private void init() {
        final List<ChannelEntity> items = new ArrayList<>();
        for (int i = 0; i <defultlist.size() ; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(defultlist.get(i));
            items.add(entity);
        }
        final List<ChannelEntity> otherItems = new ArrayList<>();
        for (int i = 0; i <allList.size() ; i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(allList.get(i));
            otherItems.add(entity);
        }

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecy.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecy);

        final ChannelAdapter adapter = new ChannelAdapter(this, helper, items, otherItems);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = adapter.getItemViewType(position);
                return viewType == ChannelAdapter.TYPE_MY || viewType == ChannelAdapter.TYPE_OTHER ? 1 : 4;
            }
        });
        mRecy.setAdapter(adapter);

        adapter.setOnMyChannelItemClickListener(new ChannelAdapter.OnMyChannelItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(ChannelActivity.this, items.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
