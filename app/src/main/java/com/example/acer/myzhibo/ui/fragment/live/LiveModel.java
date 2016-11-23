package com.example.acer.myzhibo.ui.fragment.live;

import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import rx.Observable;

/**
 * Created by acer on 2016/11/23.
 */

public class LiveModel implements LiveContract.ILiveModel {
    @Override
    public Observable<LiveBean> getZhiBoBean(String url) {
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<LiveBean> observable = retrofitInterface.getZhiBoBean(url);

        return observable;
    }
}
