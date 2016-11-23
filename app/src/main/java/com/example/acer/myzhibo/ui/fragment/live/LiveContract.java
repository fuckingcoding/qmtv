package com.example.acer.myzhibo.ui.fragment.live;

import com.example.acer.myzhibo.bean.LiveBean;

import retrofit2.http.GET;
import retrofit2.http.Url;
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

    interface  ILiveView{
         void getZhiBoData(LiveBean bean);
    }
}
