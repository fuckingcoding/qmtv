package com.example.acer.myzhibo.ui.fragment.live;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.utils.ToastHelper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveFragment extends Fragment  implements LiveContract.ILiveView{
   private Context mContext ;
   private LivePresenter presenter ;
    public LiveFragment() {
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

        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new LivePresenter(this);
        presenter.getZhiBoBean(UrlConfig.TOTALURL);

    }


    //直播BEAN的回调
    @Override
    public void getZhiBoData(LiveBean bean) {
        int follow = bean.getData().get(0).getFollow();
        String view = bean.getData().get(0).getView();
        Log.e("TAG", "getZhiBoData: "+follow);
    }
}
