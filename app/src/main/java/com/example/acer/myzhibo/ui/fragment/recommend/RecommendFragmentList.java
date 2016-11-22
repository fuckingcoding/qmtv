package com.example.acer.myzhibo.ui.fragment.recommend;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragmentList extends Fragment {
private TextView text;

    public RecommendFragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recommend_fragment_list, container, false);
        text=(TextView)view.findViewById(R.id.recomefragmenglist_text);
        Bundle arguments = getArguments();
        String string = arguments.getString(Constant.KEY_RECOMMEND_URL_KEY);
        text.setText(string);
        return view;

    }

}
