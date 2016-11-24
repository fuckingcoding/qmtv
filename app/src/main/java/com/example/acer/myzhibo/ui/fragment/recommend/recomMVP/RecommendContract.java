package com.example.acer.myzhibo.ui.fragment.recommend.recomMVP;

import com.example.acer.myzhibo.bean.QMBean;

import rx.Observable;

/**
 * Created by apple on 16/11/23.
 * Eemil:635727195@qq.com
 */

public interface RecommendContract {
    interface IRwcommendModel{

        Observable<QMBean> getQMBean(String type);
    }

    interface  IRecommendPresenter{
        void getQMBean(String type);
    }

    interface  IRecommendView{
        void getQMData(QMBean bean);
    }
}
