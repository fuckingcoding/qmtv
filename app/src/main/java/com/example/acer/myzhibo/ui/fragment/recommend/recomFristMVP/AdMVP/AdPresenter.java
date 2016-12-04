package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP;

import com.example.acer.myzhibo.bean.AdBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Project Name:MyZhibo
 * Package Name:com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP
 * File    Name:AdPresenter
 * Created by WYJ on 16/12/1.
 * Description :TODO
 */

public class AdPresenter implements AdContract.IAdPresenter {
    private AdContract.IAdModel model;
    private AdContract.IAdView view;

    public AdPresenter(AdContract.IAdView view) {
        this.model = new AdModel();
        this.view = view;
    }

    @Override
    public void getAdBean(String url) {
        Observable<AdBean> observable = model.getAdBean(url);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AdBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AdBean adBean) {
                            view.getAdData(adBean);
                    }
                });
    }
}
