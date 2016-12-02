package com.example.acer.myzhibo.ui.fragment.My;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.ui.HeadCropActivity;
import com.example.acer.myzhibo.ui.SettingActivity;
import com.example.acer.myzhibo.ui.login.LoginActivity;
import com.example.acer.myzhibo.bean.LoginBean;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;
import com.example.acer.myzhibo.ui.TulingActivity;
import com.example.acer.myzhibo.ui.login.LoginActivity;
import com.hyphenate.EMMessageListener;

import static com.example.acer.myzhibo.R.id.kaibo_normal;


//注册回调 加群退群监听
/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements MineContract.IMineView, View.OnClickListener {
    private Context mContext;
    private EMMessageListener msgListener;
    private LinearLayout linear1, linear2, linear3, linear4, linear5, linear6, linear_center;
    private ImageView iv_left, iv_right,kaibo;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    // private
    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        linear5.setOnClickListener(this);
        linear6.setOnClickListener(this);
        linear_center.setOnClickListener(this);
        iv_left.setOnClickListener(this);
        iv_right.setOnClickListener(this);
        kaibo.setOnClickListener(this);

    }

    private void initView(View view) {
        linear1 = (LinearLayout) view.findViewById(R.id.linear1_mine);
        linear2 = (LinearLayout) view.findViewById(R.id.linear2_mine);
        linear3 = (LinearLayout) view.findViewById(R.id.linear3_mine);
        linear4 = (LinearLayout) view.findViewById(R.id.linear4_mine);
        linear5 = (LinearLayout) view.findViewById(R.id.linear5_mine);
        linear6 = (LinearLayout) view.findViewById(R.id.linear6_mine);
        linear_center = (LinearLayout) view.findViewById(R.id.linear_center_mine);
        iv_left = (ImageView) view.findViewById(R.id.iv_mine_left);
        iv_right = (ImageView) view.findViewById(R.id.iv_mine_right);
        kaibo=(ImageView)view.findViewById(R.id.kaibo_normal);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.linear1_mine:

                break;
            case R.id.linear2_mine:

                break;
            case R.id.linear3_mine:


                break;
            case R.id.linear4_mine:

                break;
            case R.id.linear5_mine:

                break;
            case R.id.linear6_mine:
                 intent.setClass(mContext, HeadCropActivity.class);
                break;
            case R.id.linear_center_mine:
                intent.setClass(mContext, LoginActivity.class);
                break;
            case R.id.iv_mine_left:

                intent.setClass(mContext, TulingActivity.class);

                break;
            case R.id.iv_mine_right:

                intent.setClass(mContext, SettingActivity.class);

                break;
            case kaibo_normal:

                intent.setClass(mContext,LoginActivity.class);

                break;

        }
        startActivity(intent);


        }




    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
