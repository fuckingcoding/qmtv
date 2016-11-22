package com.example.acer.myzhibo.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColumnFragment extends Fragment {
    private Context mContent ;
    private RecyclerView recyclerView;



    public ColumnFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContent = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_column, container, false);
        initData();
        initView(rootView);
        return rootView;
    }

    private void initData() {
    }

    private void initView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_column_recyclerview);
        GridLayoutManager gridlayout = new GridLayoutManager(mContent,3, LinearLayoutManager.HORIZONTAL,false);
    }

}
