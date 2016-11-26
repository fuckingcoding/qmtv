package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.MyVpInfiniteAdapter;
import com.example.acer.myzhibo.adapter.recommend.XRxcycleViewAdapter;
import com.example.acer.myzhibo.bean.RecomBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecomFirstFragment extends Fragment implements RecomFirstContract.IRoomView{
    private static final String TAG = "RecomFirstFragment";
    private Context mContext;
    //上方View的集合
    private XRecyclerView mxRecyclerView;
    private ImageView iv;
    private List<ImageView> adViews=new ArrayList<>();
    private MyVpInfiniteAdapter adAdapter;
    private ViewPager mAdViewpager;
    private LinearLayout adlayout;
   private LinearLayoutManager layout;
    private XRxcycleViewAdapter xrecycleviewadapter;
    private RecomFirstContract.IRoomPresenter presenter;

     private List<RecomBean.RoomBean> data=new ArrayList<>();

    private Handler adHandler = new Handler();

    // private ADRunnable adRunnable;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recom_first, container, false);
        initView(view);


        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=new RecomFirstPresenter(this);
        presenter.getBean(UrlConfig.RECOMMEND);
    }

    private void initView(View view) {
        mxRecyclerView = (XRecyclerView) view.findViewById(R.id.xRecycleview_first_fragment_ad);

       layout=new LinearLayoutManager(mContext);
       mxRecyclerView.setLayoutManager(layout);
       // View header = LayoutInflater.from(mContext).inflate(R.layout.ad_viewpager_recommed, (ViewGroup) view.findViewById(android.R.id.content), false);
        xrecycleviewadapter=new XRxcycleViewAdapter(mContext,data);
        mxRecyclerView.setHasFixedSize(true);
        mxRecyclerView.setAdapter(xrecycleviewadapter);
      //  mxRecyclerView.addHeaderView(header);


       // initAd(view);
    }

    private void initAd(View view) {
        initAdItemView();
        initViewPager(view);
      // initDot(view);
//        adRunnable = new ADRunnable();
    }




    private void initViewPager(View view) {
        mAdViewpager = (ViewPager) view.findViewById(R.id.recom_first_fragment_viewpager_ad);
        adAdapter = new MyVpInfiniteAdapter(adViews);
        //mAdViewpager.setOffscreenPageLimit(6);

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
                for (int i = 0; i < adViews.size(); i++) {
                    if (i == (position % adViews.size())) {
                        //Log.e("TAG", "onPageSelected() returned: " + position%6);
                        adlayout.getChildAt(i % (adViews.size())).setSelected(true);
                    } else {
                        adlayout.getChildAt(i % (adViews.size())).setSelected(false);
                    }
                }
                //处理文字
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
                        //   adHandler.removeCallbacks(adRunnable);
                        break;
                    case MotionEvent.ACTION_UP: //提前
                        //  adHandler.postDelayed(adRunnable, 2000);

                        break;
                }
                return false;
            }
        });
    }

    private void initADText(int position) {

    }

    private void initAdItemView() {

        for (int i = 0; i < 5; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )); //设置布局
            iv.setImageResource(R.mipmap.ic_launcher); //设置图片资源
            adViews.add(iv);
        }
    }

    private void initDot(View view) {
        adlayout = (LinearLayout) view.findViewById(R.id.ad_dot_layout);
        for (int i = 0; i < adViews.size(); i++) {
            ImageView iv = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 10;
            iv.setLayoutParams(params);
            iv.setImageResource(R.drawable.ad_selector);
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
        xrecycleviewadapter.reloadRecyclerView(data,false);
    }


//    //广告轮播的任务
//    class ADRunnable implements Runnable {
//        @Override
//        public void run() {
//            int currentPosition = mAdViewpager.getCurrentItem(); //获得当前的位置
//            currentPosition++;
//
//            mAdViewpager.setCurrentItem(currentPosition);//重新设置位置
//            //            adRunnable = new ADRunnable();
//            adHandler.postDelayed(adRunnable, 2000);
//        }
//    }
//    @Override
//    public void onPause() {
//        super.onPause();
//        adHandler.removeCallbacks(adRunnable);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        adHandler.postDelayed(adRunnable, 2000);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adRunnable = new ADRunnable();
//        adHandler.postDelayed(adRunnable,2000);
//    }
}
