package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP;

import android.util.Log;

import com.example.acer.myzhibo.bean.RecomBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by apple on 16/11/24.
 * Eemil:635727195@qq.com
 */

public class RecomFirstPresenter implements RecomFirstContract.IRoomPresenter {
    private RecomFirstContract.IRoomModel model;
    private RecomFirstContract.IRoomView view;
    private static final String TAG = "RecomFirstPresenter";

    public RecomFirstPresenter(RecomFirstContract.IRoomView view) {
        this.model =new RecomFirstModel();
        this.view = view;
    }

    @Override
    public void getBean(String url) {
        Observable<RecomBean> observable = model.getBean(url);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecomBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: "+e);
                    }

                    @Override
                    public void onNext(RecomBean recomBean) {
                        view.getData(recomBean);
                        Log.e(TAG, "onNext: "+recomBean.getRoom().size() );
                    }
                });
    }

}
