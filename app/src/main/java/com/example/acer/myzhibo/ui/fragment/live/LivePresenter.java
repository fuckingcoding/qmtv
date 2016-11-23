package com.example.acer.myzhibo.ui.fragment.live;

import android.util.Log;

import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.utils.ToastHelper;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by acer on 2016/11/23.
 */

public class LivePresenter implements LiveContract.ILivePresenter {
    private LiveContract.ILiveModel liveModel;
    private LiveContract.ILiveView  view;

    public LivePresenter(LiveContract.ILiveView view) {
        this.liveModel = new LiveModel();
        this.view = view;
    }

    @Override
    public void getZhiBoBean(String url) {

        Observable<LiveBean> observable = liveModel.getZhiBoBean(url);
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Subscriber<LiveBean>() {
                     @Override
                     public void onCompleted() {

                     }

                     @Override
                     public void onError(Throwable e) {

                     }

                     @Override
                     public void onNext(LiveBean bean) {
                         view.getZhiBoData(bean);
                         int size = bean.getData().size();
                         Log.e("TAG", "onNext: "+size );
                     }
                 });
    }
}
