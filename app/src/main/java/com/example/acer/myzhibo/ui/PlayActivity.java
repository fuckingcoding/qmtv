package com.example.acer.myzhibo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.zycadapter.MyPlayTablayoutAdapter;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.ui.fragment.ChatFragment;
import com.example.acer.myzhibo.ui.fragment.ProtectFragment;
import com.example.acer.myzhibo.ui.fragment.RankFragment;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends AppCompatActivity implements Runnable{
    private Context mContext=this;
    private VideoView videoView;
    private MediaController mMediaController;
    private ImageView imageView,iv_guanzhu,iv_tixing;
    private TextView textView_name;
    private TextView textView_content;
    private TextView textgz,texttixing;
    private String playurl ;
    private String pic,head,content,view;
    private boolean flag=true;
    private boolean kg=true;
    private TabLayout tablayout;
    private ViewPager viewpaper;
    private List<Fragment> list_fragment;         //fragment的数据集合
    private List<String> list_title;
    private Fragment chatFragment,rankFragment,protectFragment;
    private MyPlayTablayoutAdapter mptAdapter;
//    private String path = "http://flv.quanmin.tv/live/3043295.flv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (!LibsChecker.checkVitamioLibs(this))
//            return;

        setContentView(R.layout.activity_play);
        initURL();
        initTabLayout();
        initTabData();
        initView();
        initData();
    }

    private void initTabData() {



        //往Fragment数组中装入数据
        list_fragment.add(chatFragment);
        list_fragment.add(rankFragment);
        list_fragment.add(protectFragment);

        //往TabLayout数组中放入名称
        list_title.add("聊天");
        list_title.add("排行");
        list_title.add("守护");
        //设置TabLayout的模式
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tablayout.addTab(tablayout.newTab().setText(list_title.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(1)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(2)));
        mptAdapter = new MyPlayTablayoutAdapter(getSupportFragmentManager(),list_fragment,list_title);
        viewpaper.setAdapter(mptAdapter);
        tablayout.setupWithViewPager(viewpaper);
        viewpaper.setOffscreenPageLimit(2);
    }

    private void initTabLayout() {
        tablayout = (TabLayout) findViewById(R.id.tablayout_play);
        viewpaper = (ViewPager) findViewById(R.id.viewpager_play);
        chatFragment = new ChatFragment();
        rankFragment = new RankFragment();
        protectFragment = new ProtectFragment();
        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();

    }

    private void initData() {
        Glide.with(mContext).load(pic).transform(new BitmapCircleTransformation(mContext)).into(imageView);
        textView_name.setText(head);
        textView_content.setText(content);
        iv_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag){
                    iv_guanzhu.setImageResource(R.mipmap.btn_focus_selected);
                    textgz.setText("已关注");
                    flag=false;

                }else{
                    iv_guanzhu.setImageResource(R.mipmap.btn_focus_normal);
                    textgz.setText("关注");
                    flag=true;
                }
            }
        });
        iv_tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kg){
                    iv_tixing.setImageResource(R.mipmap.home_more_selected);
                    ToastHelper.showToast(mContext,"已订阅该主播");
                    kg=false;
                }else{
                    iv_tixing.setImageResource(R.mipmap.home_more_normal);
                    ToastHelper.showToast(mContext,"已退订该主播");
                    kg=true;
                }
            }
        });
    }

    private void initURL() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(Constant.UID);
        pic = intent.getStringExtra(Constant.AVATAR);
        view = intent.getStringExtra(Constant.VIEW);
        content = intent.getStringExtra(Constant.TITLE);
        head = intent.getStringExtra(Constant.NICK);
        playurl = String.format(Constant.PLAYERURL, stringExtra);
    }

    private void initView() {

        textgz = (TextView) findViewById(R.id.tv_play_guanzhu);
        texttixing = (TextView) findViewById(R.id.tv_play_tixing);
        imageView = (ImageView) findViewById(R.id.iv_play_icon);
        iv_guanzhu = (ImageView) findViewById(R.id.iv_play_guanzhu);
        iv_tixing = (ImageView) findViewById(R.id.iv_play_tixing);
        textView_name = (TextView) findViewById(R.id.tv_play_name);
        textView_content = (TextView) findViewById(R.id.tv_play_content);
        videoView = (VideoView) findViewById(R.id.videoview_play);
        videoView.setVideoPath(playurl);
        mMediaController = new MediaController(this);
        mMediaController.show(5000);
        videoView.setMediaController(mMediaController);
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_MEDIUM);
        videoView.requestFocus();
        videoView.start();

    }

    @Override
    public void run() {

    }
}
