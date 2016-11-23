package com.example.acer.myzhibo.ui.fragment.recommend;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragmentList extends Fragment {
    private Context mContext;
    private RecyclerView mRecycleView;
    private GridLayoutManager mlayoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recommend_fragment_list, container, false);

        Bundle arguments = getArguments();
        String string = arguments.getString(Constant.KEY_RECOMMEND_URL_KEY);
        initRecycleView(view);
        return view;
    }

    private void initRecycleView(View view) {
        mRecycleView=(RecyclerView)view.findViewById(R.id.recycleview_recommendfragList);
        mRecycleView.setHasFixedSize(true);
        //设置布局为竖直
        mlayoutManager=new GridLayoutManager(mContext,2);
        mRecycleView.setLayoutManager(mlayoutManager);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
