package com.example.acer.myzhibo.ui.fragment.My;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.acer.myzhibo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements MineContract.IMineView,View.OnClickListener{
   private Context mContext ;
   private LinearLayout linear1,linear2,linear3,linear4,linear5,linear6,linear_center;
    private ImageView iv_left, iv_right;
   // private
    public MyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext =context;
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

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
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
                break;
            case R.id.linear_center_mine:
                break;
            case  R.id.iv_mine_left:
                break;
            case R.id.iv_mine_right:
                break;

        }
    }
}
