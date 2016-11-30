package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP;


import com.example.acer.myzhibo.bean.RecomBean;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import rx.Observable;

/**
 * Created by apple on 16/11/24.
 * Eemil:635727195@qq.com
 */

public class RecomFirstModel implements RecomFirstContract.IRoomModel {
    @Override
    public Observable<RecomBean> getBean(String url) {
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<RecomBean> observable = retrofitInterface.getRoom(url);
        return observable;
    }
}
