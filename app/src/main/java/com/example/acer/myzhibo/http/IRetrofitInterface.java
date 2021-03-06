package com.example.acer.myzhibo.http;

import com.example.acer.myzhibo.bean.AdBean;
import com.example.acer.myzhibo.bean.LanmuBean;
import com.example.acer.myzhibo.bean.LiveBean;
import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.bean.RankBean;
import com.example.acer.myzhibo.bean.RecomBean;
import com.example.acer.myzhibo.bean.SearchBean;
import com.example.acer.myzhibo.bean.TulingResponseBean;
import com.example.acer.myzhibo.bean.VersionBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;



/**
 * Created by acer on 2016/11/15.
 */

public interface IRetrofitInterface {

     @GET("json/categories/{type}/list.json?11212123&v=2.2.4&os=1&ver=4")
    Observable<QMBean> getQMBean(@Path("type") String type);

    @GET
    Observable<RankBean>  getRankBean(@Url String room);

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


//    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
//    @POST("api/FlyRoute/Add")
//    Call<FlyRouteBean> postFlyRoute(@Body RequestBody route);//传入的参数为RequestBody

//    @Headers({"Content-type:application/json",
//            "Content-Length:59"})*/
//    @POST("FundPaperTrade/AppUserLogin")
//    Observable<Response> getTransData(@Body TestBean str);


    @POST(UrlConfig.SEARCH_URL)
    Observable<SearchBean> getSearchReponse(@Body JsonObject jsonObject);


    @GET
    Observable<TulingResponseBean> getTulingData(@Url String string );

    @GET
    Observable<AdBean> getAdData(@Url String string);

    @GET
    Observable<VersionBean> getVersionbean(@Url String string);

    @GET
    Observable<ResponseBody> getAPK(@Url String string);
}
