package com.example.acer.myzhibo.http;

import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.config.UrlConfig;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;



/**
 * Created by acer on 2016/11/15.
 */

public interface IRetrofitInterface {

     @GET("json/categories/{type}/list.json?11212123&v=2.2.4&os=1&ver=4")
    Observable<QMBean> getQMBean(@Path("type") String type);

    @GET("{path}")
    Observable<QMBean> getMoreQMbean(@Path("path")String path);
}
