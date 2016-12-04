package com.example.acer.myzhibo.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.zycadapter.HotEvent;
import com.example.acer.myzhibo.adapter.zycadapter.MyListViewHotAdapter;
import com.example.acer.myzhibo.adapter.zycadapter.MyPlayTablayoutAdapter;
import com.example.acer.myzhibo.bean.ChatBean;
import com.example.acer.myzhibo.bean.RankBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;
import com.example.acer.myzhibo.ui.fragment.ChatFragment;
import com.example.acer.myzhibo.ui.fragment.ProtectFragment;
import com.example.acer.myzhibo.ui.fragment.RankFragment;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.example.acer.myzhibo.utils.DensityUtil;
import com.example.acer.myzhibo.utils.DialogHelper;
import com.example.acer.myzhibo.utils.ToastHelper;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PlayActivity extends AppCompatActivity implements Runnable{
    private Context mContext=this;
    private VideoView videoView;
    private MediaController mMediaController;
    private ImageView imageView,iv_guanzhu,iv_tixing;
    private ImageView iv_back,iv_more,iv_gift,iv_pause,iv_screen;
    private ImageView iv_landscape_back;
    private ImageView iv_loading;
    private TextView textView_name;
    private TextView textView_content;
    private TextView textgz,texttixing;
    private TextView tv_full_title,tv_full_qxd,tv_full_gift;
    private String playurl ;
    private String pic,head,content,view;
    private ImageView iv_full_pause,iv_full_refrsh,iv_full_hot,iv_full_send,iv_full_text;
    private boolean flag=true;
    private boolean kg=true;
    private boolean pause=true;
    private boolean stouch=true;
    private boolean touch=true;
    private TabLayout tablayout;
    private ViewPager viewpaper;
    private List<Fragment> list_fragment;         //fragment的数据集合
    private List<String> list_title;
    private Fragment chatFragment,rankFragment;
    private MyPlayTablayoutAdapter mptAdapter;
    private LinearLayout linearlayout;
    private RelativeLayout relativeLayout;
    private String stringExtra;
    private RelativeLayout rl_top,rl_bottom;
    private  TextView tv_bq,tv_cq,tv_gq;
    private PopupWindow pw_qxd;
    private String bqurl;
    private String gqurl;
    private boolean dm=true;
    private boolean gift=true;
    private Animation operatingAnim;
    private List<String> hot=new ArrayList<>();
    private ListView pop_listView;
    private MyListViewHotAdapter mlvha;
    private EditText editText;
    private Listener listener =new Listener() {
        @Override
        public void send(String str) {
            editText.setText(str);
        }
    };
    private  EMMessageListener msgListener;
    //弹幕相关
    private DanmakuView danmakuView;
    private boolean showDanmaku;
    private DanmakuContext danmakuContext;
    private  String s;

    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
//        EventBus.getDefault().register(this);
        Vitamio.isInitialized(PlayActivity.this);
        initURL();
        initTabLayout();
        initTabData();
        initView();
        initHotData();
        initData();
        initControl();



    }

    private void initHotData() {
        String hoturl=String.format(Constant.RANKURL,stringExtra);
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<RankBean> rankBean = retrofitInterface.getRankBean(hoturl);
        rankBean.subscribeOn(Schedulers.io())
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
                        hot.addAll(rankBean.hotWord);
                        hot.add("666666");hot.add("233333");hot.add("呀,6的不行");hot.add("老哥稳");
                        hot.add("二营长,老子的意大利炮呢");
                        EventBus.getDefault().post(new HotEvent(hot));
                    }
                });


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
                if(pause){
                    videoView.pause();
                    iv_pause.setImageResource(R.mipmap.btn_live_play);
                    pause=false;
                }else{
                    videoView.start();
                    iv_pause.setImageResource(R.mipmap.btn_live_pause);
                    pause=true;
                }

            }
        });
        iv_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
        iv_landscape_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    @Override
    public int getRequestedOrientation() {
        return super.getRequestedOrientation();
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //隐藏下面的布局和标题
            linearlayout.setVisibility(View.GONE);
            viewpaper.setVisibility(View.GONE);
            tablayout.setVisibility(View.GONE);
            rl_top.setVisibility(View.VISIBLE);
            rl_bottom.setVisibility(View.VISIBLE);
            iv_back.setVisibility(View.GONE);
            iv_pause.setVisibility(View.GONE);
            iv_screen.setVisibility(View.GONE);
            iv_gift.setVisibility(View.GONE);
            iv_more.setVisibility(View.GONE);

            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            int heightPixels = getResources().getDisplayMetrics().heightPixels;
            Log.e("TAG", "onConfigurationChanged: "+widthPixels+","+heightPixels );
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            layoutParams.height=heightPixels;
            layoutParams.width=widthPixels;
            relativeLayout.setLayoutParams(layoutParams);
            videoView.setLayoutParams(layoutParams);
            videoView.reSize(widthPixels,heightPixels);
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(touch){
                        rl_top.setVisibility(View.GONE);
                        rl_bottom.setVisibility(View.GONE);
                        touch=false;
                    }else{
                        rl_top.setVisibility(View.VISIBLE);
                        rl_bottom.setVisibility(View.VISIBLE);
                        touch=true;
                    }

                    return false;
                }
            });


        }
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //显示下面的布局和标题
            linearlayout.setVisibility(View.VISIBLE);
            viewpaper.setVisibility(View.VISIBLE);
            tablayout.setVisibility(View.VISIBLE);
            rl_top.setVisibility(View.GONE);
            rl_bottom.setVisibility(View.GONE);
            iv_back.setVisibility(View.VISIBLE);
            iv_pause.setVisibility(View.VISIBLE);
            iv_screen.setVisibility(View.VISIBLE);
            iv_gift.setVisibility(View.VISIBLE);
            iv_more.setVisibility(View.VISIBLE);
            int widthPixels = getResources().getDisplayMetrics().widthPixels;
            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            layoutParams.width=widthPixels;
            layoutParams.height= DensityUtil.dip2px(this,200);
            relativeLayout.setLayoutParams(layoutParams);
            videoView.setLayoutParams(layoutParams);
            videoView.reSize(widthPixels,DensityUtil.dip2px(this,200));
            videoView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(stouch){
                        iv_back.setVisibility(View.GONE);
                        iv_pause.setVisibility(View.GONE);
                        iv_screen.setVisibility(View.GONE);
                        iv_gift.setVisibility(View.GONE);
                        iv_more.setVisibility(View.GONE);
                        stouch=false;
                    }else{
                        iv_back.setVisibility(View.VISIBLE);
                        iv_pause.setVisibility(View.VISIBLE);
                        iv_screen.setVisibility(View.VISIBLE);
                        iv_gift.setVisibility(View.VISIBLE);
                        iv_more.setVisibility(View.VISIBLE);
                        stouch=true;
                    }
                    return false;
                }
            });

        }


    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void initTabData() {



        //往Fragment数组中装入数据
        list_fragment.add(chatFragment);
        list_fragment.add(rankFragment);

        //往TabLayout数组中放入名称
        list_title.add("聊天");
        list_title.add("排行");
        //设置TabLayout的模式
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tablayout.addTab(tablayout.newTab().setText(list_title.get(0)));
        tablayout.addTab(tablayout.newTab().setText(list_title.get(1)));
        mptAdapter = new MyPlayTablayoutAdapter(getSupportFragmentManager(),list_fragment,list_title);
        viewpaper.setAdapter(mptAdapter);
        tablayout.setupWithViewPager(viewpaper);
        viewpaper.setOffscreenPageLimit(2);
    }

    private void initTabLayout() {
        tablayout = (TabLayout) findViewById(R.id.tablayout_play);
        viewpaper = (ViewPager) findViewById(R.id.viewpager_play);

        list_fragment = new ArrayList<>();
        list_title = new ArrayList<>();

    }

    private void initData() {
        Glide.with(mContext).load(pic).transform(new BitmapCircleTransformation(mContext)).into(imageView);
        textView_name.setText(head);
        textView_content.setText(content);
        tv_full_title.setText(content);
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
        tv_full_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gift){
                    ToastHelper.showToast(mContext,"已屏蔽礼物特效");
                    tv_full_gift.setText("打开礼物");
                    gift=false;
                }else{
                    tv_full_gift.setText("屏蔽礼物");
                    ToastHelper.showToast(mContext,"已打开礼物特效");
                    gift=true;
                }

            }
        });
        tv_full_qxd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_qxd, null);
                tv_bq = (TextView) inflate.findViewById(R.id.tv_bq);
                tv_gq = (TextView) inflate.findViewById(R.id.tv_gq);
                tv_cq = (TextView) inflate.findViewById(R.id.tv_cq);
                tv_bq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_full_qxd.setText("标清");
                        tv_bq.setTextColor(Color.RED);
                        tv_gq.setTextColor(Color.GRAY);
                        tv_cq.setTextColor(Color.GRAY);
                        bqurl = String.format(Constant.BQURL, stringExtra);
                        videoView.setVideoPath(bqurl);
                        videoView.start();
                        pw_qxd.dismiss();
                    }
                });
                tv_gq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_full_qxd.setText("高清");
                        tv_bq.setTextColor(Color.GRAY);
                        tv_gq.setTextColor(Color.RED);
                        tv_cq.setTextColor(Color.GRAY);
                        gqurl = String.format(Constant.GQURL, stringExtra);
                        videoView.setVideoPath(gqurl);
                        videoView.start();
                        pw_qxd.dismiss();
                    }
                });
                tv_cq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv_full_qxd.setText("超清");
                        tv_bq.setTextColor(Color.GRAY);
                        tv_gq.setTextColor(Color.GRAY);
                        tv_cq.setTextColor(Color.RED);
                        videoView.setVideoPath(playurl);
                        videoView.start();
                        pw_qxd.dismiss();
                    }
                });

                pw_qxd = new PopupWindow(inflate,
                        ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
                pw_qxd.setTouchable(true);
                pw_qxd.showAsDropDown(view);
            }
        });
        iv_full_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pause){
                    videoView.pause();
                    iv_full_pause.setImageResource(R.mipmap.btn_live_play);
                    pause=false;
                }else{
                    videoView.start();
                    iv_full_pause.setImageResource(R.mipmap.btn_live_pause);
                    pause=true;
                }

            }
        });
        iv_full_refrsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv_full_qxd.getText().equals("标清")){
                    videoView.setVideoPath(bqurl);
                    videoView.start();
                }else if(tv_full_qxd.getText().equals("高清")){
                    videoView.setVideoPath(gqurl);
                    videoView.start();
                }else{
                    videoView.setVideoPath(playurl);
                    videoView.start();
                }
            }
        });
        iv_full_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dm){
                    iv_full_text.setImageResource(R.mipmap.fullscreen_close_commenting_unpressed);
                    danmakuView.pause();
                    danmakuView.clear();
                    ToastHelper.showToast(mContext,"弹幕已关闭");
                    dm=false;
                }else{
                    iv_full_text.setImageResource(R.mipmap.commenting_unpressed);
                    danmakuView.start();
                    ToastHelper.showToast(mContext,"弹幕已开启");
                    dm=true;
                }


            }
        });
        iv_full_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlvha = new MyListViewHotAdapter(mContext,hot,listener);
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_hot, null);
                pop_listView = (ListView) inflate.findViewById(R.id.lv_hot);
                pop_listView.setAdapter(mlvha);
                mlvha.notifyDataSetChanged();
                pw_qxd = new PopupWindow(inflate,
                        ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
                pw_qxd.setTouchable(true);
                int height = rl_bottom.getLayoutParams().height;
                pw_qxd.showAtLocation(relativeLayout,Gravity.BOTTOM|Gravity.LEFT,0,height);
            }
        });
        iv_full_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s = editText.getEditableText() + "";
                if (!TextUtils.isEmpty(s)) {


                    ((ChatFragment)chatFragment).getEditMsg(s);
                    ToastHelper.showToast(mContext,s);
                }

            }
        });



    }


    private void initURL() {
        chatFragment = new ChatFragment();
        rankFragment = new RankFragment();
        Intent intent = getIntent();
        stringExtra = intent.getStringExtra(Constant.UID);
        Bundle bundle = new Bundle();
        bundle.putString("room",stringExtra);
        rankFragment.setArguments(bundle);
        pic = intent.getStringExtra(Constant.AVATAR);
        view = intent.getStringExtra(Constant.VIEW);
        content = intent.getStringExtra(Constant.TITLE);
        head = intent.getStringExtra(Constant.NICK);
        playurl = String.format(Constant.PLAYERURL, stringExtra);

    }
//    public void onEventMainThread(HotEvent event){
//        hot = event.getmHot_List();
//    }

    private void initView() {
        editText = (EditText) findViewById(R.id.et_landscape);
        iv_landscape_back= (ImageView) findViewById(R.id.iv_landscape_back);
        rl_top = (RelativeLayout) findViewById(R.id.rl_landscape_top);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_landscape_bottom);
        danmakuView = (DanmakuView) findViewById(R.id.danmaku_view);
        relativeLayout = (RelativeLayout) findViewById(R.id.play_new_rl);
        linearlayout = (LinearLayout) findViewById(R.id.ll_play);
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
        tv_full_title = (TextView) findViewById(R.id.tv_landscape_title);
        tv_full_qxd = (TextView) findViewById(R.id.tv_landscape_qxd);
        tv_full_gift = (TextView) findViewById(R.id.tv_landscape_gift);
        iv_loading = (ImageView) findViewById(R.id.iv_loading);
        iv_full_pause = (ImageView) findViewById(R.id.iv_landscape_pause);
        iv_full_refrsh = (ImageView) findViewById(R.id.iv_landscape_refresh);
        iv_full_hot = (ImageView) findViewById(R.id.iv_landscape_hot);
        iv_full_send = (ImageView) findViewById(R.id.iv_landscape_send);
        iv_full_text = (ImageView) findViewById(R.id.iv_landscape_commenting);
        operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.tip);
        videoView.setVideoPath(playurl);
        mMediaController = new MediaController(this);
        mMediaController.show(5000);
        mMediaController.setVisibility(View.INVISIBLE);
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_MEDIUM);
        videoView.requestFocus();

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    //开始缓冲
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        iv_loading.setVisibility(View.VISIBLE);
                        iv_loading.startAnimation(operatingAnim);
                        mp.pause();
                        break;
                    //结束缓冲
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        iv_loading.setVisibility(View.GONE);
                        iv_loading.clearAnimation();
                        mp.start();
                        break;
                    //正在缓冲
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        //初始化动画

                        break;
                }
                return false;
            }
        });
        videoView.start();

        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
//                generateSomeDanmaku();
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

//    /**
//     * 随机生成一些弹幕内容以供测试
//     */
//    private void generateSomeDanmaku() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(showDanmaku) {
//                    int time = new Random().nextInt(300);
//                    String content = "" + time + time;
//                    addDanmaku(content, false);
//                    try {
//                        Thread.sleep(time);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }

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
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }

    public void getfragmentMsg(String string,boolean b){
        if(b){
            addDanmaku(editText.getEditableText()+"",b);
            editText.setText("");
        }else {
            addDanmaku(string,b);
        }

    }



}
