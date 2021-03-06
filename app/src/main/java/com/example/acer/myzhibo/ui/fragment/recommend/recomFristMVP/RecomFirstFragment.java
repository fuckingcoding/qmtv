package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.MyViewPagerAdapter;
import com.example.acer.myzhibo.adapter.recommend.XRxcycleViewAdapter;
import com.example.acer.myzhibo.bean.AdBean;
import com.example.acer.myzhibo.bean.RecomBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP.AdContract;
import com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP.AdPresenter;
import com.example.acer.myzhibo.utils.UIManager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecomFirstFragment extends Fragment implements RecomFirstContract.IRoomView, AdContract.IAdView {
    private static final String TAG = "RecomFirstFragment";
    private Context mContext;

    private XRecyclerView mxRecyclerView;

    private List<View> adViews = new ArrayList<>();
    private MyViewPagerAdapter adAdapter;
    private ViewPager mAdViewpager;
    private LinearLayout adlayout;
    private LinearLayoutManager layout;
    private XRxcycleViewAdapter xrecycleviewadapter;
    private RecomFirstContract.IRoomPresenter presenter;
    private AdContract.IAdPresenter iAdPresenter;
    private List<RecomBean.RoomBean> data = new ArrayList<>();
    private List<AdBean.focusBean> aDdata = new ArrayList<>();
    private Handler adHandler = new Handler();
    private ADRunnable adRunnable;
    private View header;
    private TextView madtextview;
    //
    private int currentPosition;

    private ImageView adIv;

    private int i;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iAdPresenter = new AdPresenter(this);
        iAdPresenter.getAdBean(UrlConfig.ADURL);
        presenter = new RecomFirstPresenter(this);
        presenter.getBean(UrlConfig.RECOMMEND);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recom_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);

    }

    private void initView(View view) {

        mxRecyclerView = (XRecyclerView) view.findViewById(R.id.xRecycleview_first_fragment_ad);
        layout = new LinearLayoutManager(mContext);
        mxRecyclerView.setLayoutManager(layout);
        xrecycleviewadapter = new XRxcycleViewAdapter(mContext, data);
        mxRecyclerView.setHasFixedSize(true);
        mxRecyclerView.setAdapter(xrecycleviewadapter);
        LayoutInflater systemService = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        header = systemService.inflate(R.layout.ad_viewpager_recommed, null, false);
        mAdViewpager = (ViewPager) header.findViewById(R.id.recom_first_fragment_viewpager_ad);
        mxRecyclerView.addHeaderView(header);
    }

    private void initAd() {
        initAdItemView();
        initViewPager();
        initDot();
        adRunnable = new ADRunnable();
    }

    private void initAdItemView() {
        for (int i = 0; i < aDdata.size(); i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )); //设置布局
            iv.setImageResource(R.mipmap.ic_xin); //设置图片资源
            adViews.add(iv);
        }
    }

    private void initViewPager() {
        adAdapter = new MyViewPagerAdapter(adViews);
        mAdViewpager.setAdapter(adAdapter);
        //ViewPager滑动监听
        mAdViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

            @Override
            public void onPageSelected(int position) {
                //  处理圆点
                int itemCount = adlayout.getChildCount(); //获得Layout中子View的数量
                for (int i = 0; i < itemCount; i++) {
                    View view = adlayout.getChildAt(i);//提取子View
                    if (i == position) {
                        view.setSelected(true);
                    } else {
                        view.setSelected(false);
                    }
                }
                initADText(position);
            }
        });
        //  触摸监听，按下的时候取消handler回调，松手的时候，重新开启
        mAdViewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction(); //获得动作
                switch (action) {
                    case MotionEvent.ACTION_DOWN: //按下
                        adHandler.removeCallbacks(adRunnable);
                        break;
                    case MotionEvent.ACTION_UP: //提前
                        adHandler.postDelayed(adRunnable, 2000);

                        break;
                }
                return false;
            }
        });
    }

    private void initADText(int position) {
        madtextview = (TextView) header.findViewById(R.id.text_recom_first_ad);
        String title = aDdata.get(position).getTitle();
        if (title != null) {
            madtextview.setText(title);
        } else {
            madtextview.setText("暂无标题");
        }
    }


    private void initDot() {
        adlayout = (LinearLayout) header.findViewById(R.id.ad_dot_layout11);
        for (int i = 0; i < adViews.size(); i++) {
            ImageView iv = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            iv.setLayoutParams(params);
            iv.setImageResource(R.drawable.ad_selector);
            iv.setScaleType(ImageView.ScaleType.FIT_XY); //设置填充属性
            if (i == 0) {
                iv.setSelected(true);
            }
            //TODO 轮播条的点击
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
            adlayout.addView(iv);
        }
    }

    @Override
    public void getData(RecomBean bean) {
        data.clear();
        data.addAll(bean.getRoom());
        xrecycleviewadapter.reloadRecyclerView(data, false);
    }

    @Override
    public void getAdData(AdBean bean) {

        aDdata.addAll(bean.getFocus());
        initADText(0);
        Log.e("ooo", "getAdData: " + aDdata.size());
        initAd();

        initADImage();


    }

    //加载广告图片
    private void initADImage() {
        for (i = 0; i < adViews.size(); i++) {
            //获得广告的ImageView
            adIv = (ImageView) adViews.get(i);
            //TODO 使用Picasso加载资
            Glide.with(this)
                    .load(aDdata.get(i).getThumb())
                    .centerCrop()
                    .into(adIv);
            adIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    UIManager.startPlayActivity(mContext,
                            aDdata.get(i - 1).getLink_object().getAvatar(),
                            aDdata.get(i - 1).getLink_object().getTitle(),
                            aDdata.get(i - 1).getLink_object().getNick(),
                            aDdata.get(i - 1).getLink_object().getView(),
                            String.valueOf(aDdata.get(i - 1).getLink_object().getUid()));
                }
            });
        }
    }

    //广告轮播的任务
    class ADRunnable implements Runnable {
        @Override
        public void run() {
            currentPosition = mAdViewpager.getCurrentItem(); //获得当前的位置
            currentPosition++;
            if (currentPosition > aDdata.size() - 1) {
                currentPosition = 0;
            }
            mAdViewpager.setCurrentItem(currentPosition);//重新设置位置
            adHandler.postDelayed(adRunnable, 2000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        adHandler.removeCallbacks(adRunnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adHandler.postDelayed(adRunnable, 2000);
    }

    @Override
    public void onResume() {
        super.onResume();
        adRunnable = new ADRunnable();
        adHandler.postDelayed(adRunnable, 2000);
    }


}
