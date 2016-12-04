package com.example.acer.myzhibo.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.zycadapter.MyPlayTablayoutAdapter;
import com.example.acer.myzhibo.bean.Alert;
import com.example.acer.myzhibo.bean.GuanZhu;
import com.example.acer.myzhibo.bean.History;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.ui.fragment.ChatFragment;
import com.example.acer.myzhibo.ui.fragment.ProtectFragment;
import com.example.acer.myzhibo.ui.fragment.RankFragment;
import com.example.acer.myzhibo.ui.login.LoginActivity;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

public class PlayActivity extends AppCompatActivity implements Runnable {
    private Context mContext = this;
    private VideoView videoView;
    private MediaController mMediaController;
    private ImageView imageView, iv_guanzhu, iv_tixing;
    private ImageView iv_back, iv_more, iv_gift, iv_pause, iv_screen;
    private TextView textView_name;
    private TextView textView_content;
    private TextView textgz, texttixing;
    private String playurl;
    private String pic, head, content, view1, thumb;
    private boolean flag;
    private boolean kg ;
    private boolean pause = true;
    private boolean screen = true;
    private TabLayout tablayout;
    private ViewPager viewpaper;
    private List<Fragment> list_fragment;         //fragment的数据集合
    private List<String> list_title;
    private Fragment chatFragment, rankFragment, protectFragment;
    private MyPlayTablayoutAdapter mptAdapter;
    private LinearLayout linearlayout;
    private RelativeLayout relativeLayout;
    private String stringExtra;

    //弹幕相关
    private DanmakuView danmakuView;
    private boolean showDanmaku;
    private DanmakuContext danmakuContext;

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };
    //登录相关
    private boolean login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (!LibsChecker.checkVitamioLibs(this))
//            return;
        login = PreUtils.readBoolean(mContext, "login");

        setContentView(R.layout.activity_play);
        Vitamio.isInitialized(PlayActivity.this);

        initGuanZhu();
        initAlert();
        initURL();
        initTabLayout();
        initTabData();
        initView();
        initHistory();
        initData();
        initControl();


    }
    //TODO 进入播放页后判断是否登录以及与bomb云中相关信息进行比较，然后判断是否已经订阅
    private void initAlert() {
        if (login) {
            Intent intent = getIntent();
            final String uid = intent.getStringExtra(Constant.UID);
            Log.e("s", "initGuanZhu: " + uid);
            BmobQuery<Alert> query = new BmobQuery<Alert>();
            query.addWhereEqualTo("uid", uid);
         query.findObjects(new FindListener<Alert>() {
                @Override
                public void done(List<Alert> object, BmobException e) {
                    if (e == null) {
                        for (int i = 0; i < object.size(); i++) {
                            String uid1 = object.get(i).getUid();
                            if (uid1.equals(uid)) {
                                iv_tixing.setImageResource(R.mipmap.home_more_selected);
                                flag = true;
                            }
                        }
                    }
                }
            });
        }
    }

    //TODO 将点击进入的房间信息添加到Bomb
    private void initHistory() {
        if (login) {
            String name = PreUtils.readStrting(mContext, "username");
            History history = new History();
            history.setName(name);
            history.setUid(stringExtra);
            history.setAvatar(pic);
            history.setNick(head);
            history.setTitle(content);
            history.setView(view1);
            history.setThumb(thumb);
            history.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                }
            });
        }
    }
    //TODO 进入播放页后判断是否登录以及与bomb云中相关信息进行比较，然后判断是否已经关注
    private void initGuanZhu() {
        if (login) {
            Intent intent = getIntent();
            final String uid = intent.getStringExtra(Constant.UID);
            Log.e("s", "initGuanZhu: " + uid);
            BmobQuery<GuanZhu> query = new BmobQuery<GuanZhu>();
            query.addWhereEqualTo("uid", uid);
            query.findObjects(new FindListener<GuanZhu>() {
                @Override
                public void done(List<GuanZhu> object, BmobException e) {
                    if (e == null) {
                        for (int i = 0; i < object.size(); i++) {
                            String uid1 = object.get(i).getUid();
                            Log.i("222", "done: " + uid1 + "+" + uid);
                            if (uid1.equals(uid)) {
                                iv_guanzhu.setImageResource(R.mipmap.btn_focus_selected);
                                textgz.setText("已关注");
                                flag = true;
                            }
                        }
                    }
                }
            });
        }
    }

    private void initControl() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        iv_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        iv_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pause) {
                    videoView.pause();
                    iv_pause.setImageResource(R.mipmap.btn_live_play);
                    pause = false;
                } else {
                    videoView.start();
                    iv_pause.setImageResource(R.mipmap.btn_live_pause);
                    pause = true;
                }

            }
        });
        iv_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (screen) {
//                    changeOritation(-1);

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
//////                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
//                    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//                    int width = wm.getDefaultDisplay().getWidth();
//                    int height = wm.getDefaultDisplay().getHeight();
//                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width,height);
//                    videoView.setLayoutParams(params);
//                    videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_FIT_PARENT,0);
                    screen = false;


                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    linearlayout.setVisibility(View.VISIBLE);
//                    viewpaper.setVisibility(View.VISIBLE);
//                    tablayout.setVisibility(View.VISIBLE);
//                    WindowManager.LayoutParams params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    videoView.setLayoutParams(params);
                    screen = true;
//                    changeOritation(1);

                }


            }
        });
    }

//    private void changeOritation(int n) {
//        if(n>0&&getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        else if(n<0&&getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
//            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//    }

    @Override
    public int getRequestedOrientation() {
        return super.getRequestedOrientation();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.e("TAG", "onConfigurationChanged: ");
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //隐藏下面的布局和标题
            linearlayout.setVisibility(View.GONE);
            viewpaper.setVisibility(View.GONE);
            tablayout.setVisibility(View.GONE);
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();
            int height = wm.getDefaultDisplay().getHeight();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
            videoView.setLayoutParams(params);
//            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_FIT_PARENT,0);


        }
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //显示下面的布局和标题
            linearlayout.setVisibility(View.VISIBLE);
            viewpaper.setVisibility(View.VISIBLE);
            tablayout.setVisibility(View.VISIBLE);

        }

        super.onConfigurationChanged(newConfig);

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
        mptAdapter = new MyPlayTablayoutAdapter(getSupportFragmentManager(), list_fragment, list_title);
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
                //TODO 判断是否登录以及将相关信息添加到bomb云
                if (login) {
                    if (flag == false) {
                        iv_guanzhu.setImageResource(R.mipmap.btn_focus_selected);
                        textgz.setText("已关注");
                        flag = true;
                        String name = PreUtils.readStrting(mContext, "username");
                        GuanZhu guanzhu = new GuanZhu();
                        guanzhu.setName(name);
                        guanzhu.setUid(stringExtra);
                        guanzhu.setAvatar(pic);
                        guanzhu.setNick(head);
                        guanzhu.setTitle(content);
                        guanzhu.setView(view1);
                        guanzhu.setThumb(thumb);
                        guanzhu.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    ToastHelper.showToast(mContext, "关注成功");
                                } else {
                                    ToastHelper.showToast(mContext, "关注失败");
                                }
                            }
                        });
                    } else {
                        iv_guanzhu.setImageResource(R.mipmap.btn_focus_normal);
                        textgz.setText("关注");
                        flag = false;
                        //TODO  查询bomb云，将该房间信息从bomb中删除
                        final GuanZhu p2 = new GuanZhu();
                        String name = PreUtils.readStrting(mContext, "username");
                        BmobQuery<GuanZhu> query = new BmobQuery<GuanZhu>();
                        query.addWhereEqualTo("uid", stringExtra);
                        query.addWhereEqualTo("name", name);
                        query.findObjects(new FindListener<GuanZhu>() {
                            @Override
                            public void done(List<GuanZhu> object, BmobException e) {
                                Log.e("TAG", "done: " + e);
                                if (e == null) {
                                    String objectId = object.get(0).getObjectId();
                                    p2.setObjectId(objectId);
                                    p2.delete(new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                ToastHelper.showToast(mContext, "删除成功:");
                                            } else {
                                                ToastHelper.showToast(mContext, "删除失败：" + e.getMessage());
                                            }
                                        }

                                    });
                                }
                            }
                        });
                    }
                } else {
                    Intent intent = new Intent(PlayActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }

        });
        iv_tixing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 判断是否登录以及将相关信息添加到bomb云
                if (login) {
                    if (!kg) {
                        iv_tixing.setImageResource(R.mipmap.home_more_selected);
                        ToastHelper.showToast(mContext, "已订阅该主播");
                        kg = true;
                        String name = PreUtils.readStrting(mContext, "username");
                        Alert alert = new Alert();
                        alert.setName(name);
                        alert.setUid(stringExtra);
                        alert.setAvatar(pic);
                        alert.setNick(head);
                        alert.setTitle(content);
                        alert.setView(view1);
                        alert.setThumb(thumb);
                        alert.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    ToastHelper.showToast(mContext, "订阅成功");
                                } else {
                                    ToastHelper.showToast(mContext, "订阅失败");
                                }
                            }
                        });
                    } else {
                        iv_tixing.setImageResource(R.mipmap.home_more_normal);
                        ToastHelper.showToast(mContext, "已退订该主播");
                        kg = false;
                        //TODO 判断是否登录以及查询bomb云删除相关信息
                        final Alert p2 = new Alert();
                        String name = PreUtils.readStrting(mContext, "username");
                        BmobQuery<Alert> query = new BmobQuery<Alert>();
                        query.addWhereEqualTo("uid", stringExtra);
                        query.addWhereEqualTo("name", name);
                        query.findObjects(new FindListener<Alert>() {
                            @Override
                            public void done(List<Alert> object, BmobException e) {
                                if (e == null) {
                                    String objectId = object.get(0).getObjectId();
                                    p2.setObjectId(objectId);
                                    p2.delete(new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if (e == null) {
                                                ToastHelper.showToast(mContext, "取消成功:");
                                            } else {
                                                ToastHelper.showToast(mContext, "取消失败：" + e.getMessage());
                                            }
                                        }

                                    });
                                }
                            }
                        });
                    }
                } else {
                    Intent intent = new Intent(PlayActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_ZOOM,0);
//        }
    }

    private void initURL() {
        Intent intent = getIntent();
        stringExtra = intent.getStringExtra(Constant.UID);
        pic = intent.getStringExtra(Constant.AVATAR);
        view1 = intent.getStringExtra(Constant.VIEW);
        content = intent.getStringExtra(Constant.TITLE);
        head = intent.getStringExtra(Constant.NICK);
        thumb = intent.getStringExtra(Constant.Thumb);
        playurl = String.format(Constant.PLAYERURL, stringExtra);
    }

    private void initView() {
        danmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        relativeLayout = (RelativeLayout) findViewById(R.id.play_rl);
        //linearlayout = (LinearLayout) findViewById(R.id.ll_play);
        iv_back = (ImageView) findViewById(R.id.small_model_back);
        iv_more = (ImageView) findViewById(R.id.small_model_more);
        iv_gift = (ImageView) findViewById(R.id.small_model_gift);
        iv_pause = (ImageView) findViewById(R.id.small_model_pause);
        iv_screen = (ImageView) findViewById(R.id.small_model_screen);
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
        mMediaController.setVisibility(View.INVISIBLE);
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_MEDIUM);
        videoView.requestFocus();
        videoView.start();

        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser, danmakuContext);
    }


    @Override
    public void run() {

    }


    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }


}
