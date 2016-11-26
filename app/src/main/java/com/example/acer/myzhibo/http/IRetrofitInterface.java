package com.example.acer.myzhibo.http;

import com.example.acer.myzhibo.bean.LanmuBean;
import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.bean.RecomBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;



/**
 * Created by acer on 2016/11/15.
 */

public interface IRetrofitInterface {

     @GET("json/categories/{type}/list.json?11212123&v=2.2.4&os=1&ver=4")
    Observable<QMBean> getQMBean(@Path("type") String type);

    @GET("{path}")
    Observable<QMBean> getMoreQMbean(@Path("path")String path);

    @GET
    Observable<LiveBean> getZhiBoBean(@Url String url);

    @GET
    Observable<List<LanmuBean>> getaaa(@Url String url);


    @GET
    Observable<List<LanmuBean>> getColumn(@Url String url);

    @GET
    Observable<RecomBean> getRoom(@Url String url);
}
