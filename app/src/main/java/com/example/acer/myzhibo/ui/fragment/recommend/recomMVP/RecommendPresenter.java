package com.example.acer.myzhibo.ui.fragment.recommend.recomMVP;

import android.util.Log;

import com.example.acer.myzhibo.bean.QMBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/11/23.
 * Eemil:635727195@qq.com
 */

public class RecommendPresenter implements RecommendContract.IRecommendPresenter {
    private RecommendContract.IRwcommendModel model;
    private RecommendContract.IRecommendView view;

    public RecommendPresenter(RecommendContract.IRecommendView view) {
        this.model = new RecommendModel();
        this.view = view;
    }
    @Override
    public void getQMBean(String type) {
        Observable<QMBean> qmBean = model.getQMBean(type);
        qmBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QMBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("www", "onError: "+e);
                    }

                    @Override
                    public void onNext(QMBean bean) {

                        view.getQMData(bean);
                    }
                });
    }
}
