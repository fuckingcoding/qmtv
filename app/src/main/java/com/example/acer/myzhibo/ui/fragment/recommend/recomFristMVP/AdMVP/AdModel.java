package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP;

import com.example.acer.myzhibo.bean.AdBean;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import rx.Observable;

/**
 * Project Name:MyZhibo
 * Package Name:com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP
 * File    Name:AdModel
 * Created by WYJ on 16/12/1.
 * Description :TODO
 */

public class AdModel implements AdContract.IAdModel {
    @Override
    public Observable<AdBean> getAdBean(String url) {
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<AdBean> observable=retrofitInterface.getAdData(url);
        return observable;
    }
}
