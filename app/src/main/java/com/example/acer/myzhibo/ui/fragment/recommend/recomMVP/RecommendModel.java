package com.example.acer.myzhibo.ui.fragment.recommend.recomMVP;

import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import rx.Observable;

/**
 * Created by apple on 16/11/23.
 * Eemil:635727195@qq.com
 */

public class RecommendModel implements RecommendContract.IRwcommendModel {

    @Override
    public Observable<QMBean> getQMBean(String type) {
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<QMBean> observer = retrofitInterface.getQMBean(type);
        return observer;
    }
}
