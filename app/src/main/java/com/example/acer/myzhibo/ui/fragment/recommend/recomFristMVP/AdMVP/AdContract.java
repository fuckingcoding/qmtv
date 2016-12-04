package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP;

import com.example.acer.myzhibo.base.IBaseModel;
import com.example.acer.myzhibo.base.IBasePresenter;
import com.example.acer.myzhibo.base.IBaseView;
import com.example.acer.myzhibo.bean.AdBean;

import rx.Observable;

/**
 * Project Name:MyZhibo
 * Package Name:com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP.AdMVP
 * File    Name:AdContract
 * Created by WYJ on 16/12/1.
 * Description :TODO
 */

public class AdContract {
    public    interface  IAdModel extends IBaseModel {
        Observable<AdBean> getAdBean(String url);
    }

    public    interface  IAdPresenter extends IBasePresenter {
        void getAdBean(String url);
    }

    public    interface  IAdView extends IBaseView {
        void getAdData(AdBean bean);
    }
}
