package com.example.acer.myzhibo.ui.fragment.recommend.demochannel;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.ui.fragment.RecommendFragment;
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

    private Context mContext = this;

    //    public static final String []OtherName= UrlConfig.OtherName;
    private List<String> allList;
    //    public static final String []DefultName=UrlConfig.DefultName;
    private List<String> defultlist;

    private String other;
    private String defult;
    private String[] othername;
    private String[] defultname;

    private ImageView image_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        other = PreUtils.readStrting(mContext, "other");
        othername = other.split(",");

        defult = PreUtils.readStrting(mContext, "defult");
        defultname = defult.split(",");
        Log.e("wwww", "onCreate: " + defultname.length);

        //全部频道转成list

        allList = new ArrayList<>();
        Collections.addAll(allList, othername);
        //默认频道转成list
        defultlist = new ArrayList<>();
        Collections.addAll(defultlist, defultname);


        mRecy = (RecyclerView) findViewById(R.id.recy);
        init();

//        image_back=(ImageView)findViewById(R.id.image_ahannel_back);
//        image_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ChannelActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void init() {
        final List<ChannelEntity> items = new ArrayList<>();
        for (int i = 0; i < defultlist.size(); i++) {
            ChannelEntity entity = new ChannelEntity();
            entity.setName(defultlist.get(i));
            items.add(entity);
        }
        final List<ChannelEntity> otherItems = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


            RecommendFragment recommend=new RecommendFragment();





        finish();
        return true;

    }
}
