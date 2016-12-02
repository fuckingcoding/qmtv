package com.example.acer.myzhibo.ui.fragment.live;

import com.example.acer.myzhibo.bean.LiveBean;

import rx.Observable;

/**
 * Created by acer on 2016/11/23.
 */

public class LiveContract {

    interface ILiveModel{

        Observable<LiveBean> getZhiBoBean(String url);
    }

    interface  ILivePresenter{
       void getZhiBoBean(String url);
    }

    public interface  ILiveView{
         void getZhiBoData(LiveBean bean);
    }
}
