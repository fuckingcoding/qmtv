package com.example.acer.myzhibo.http;

import java.util.Map;

import mdzz.com.first_of_mdzz.bean.fun.PlayBean;
import mdzz.com.first_of_mdzz.bean.hot.HotBean;
import mdzz.com.first_of_mdzz.bean.position.PositionBean;
import mdzz.com.first_of_mdzz.bean.subscibe.FashionBean;
import mdzz.com.first_of_mdzz.bean.week.WeekBean;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by acer on 2016/11/15.
 */

public interface IRetrofitInterface {

   //http://wl.myzaker.com/?_appid=AndroidPhone&_v=7.0.2&_version=7.02&c=city_list&lat=39.913249&lng=116.403625
    //http://wl.myzaker.com/?_appid=AndroidPhone&_v=7.0.2&_version=7.02&c=columns&city=%E5%8C%97%E4%BA%AC
    @GET("?")
    Observable<PlayBean> getPlayBean(@QueryMap Map<String, String> map);




    //http://iphone.myzaker.com/zaker/news.php?_appid=AndroidPhone&_bsize=1080_1920&_version=7.02&app_id=12
    @GET
    Observable<FashionBean> getFashionBean(@Url String s);


    @GET("?")
     Observable<PositionBean> getPositionBean(@QueryMap Map<String, String> map);


   //http://wl.myzaker.com/?c=activity_list&city=beijing&p=0&size=20&category=4

     @GET("?")
     Observable<WeekBean> getWeekBean(@QueryMap Map<String, String> map);


   // http://wl.myzaker.com/?c=movie_list
    //@GET(?)

 //http://hotphone.myzaker.com/daily_hot_new.php?_appid=AndroidPhone&_bsize=1080_1920&_city=beijing&_dev=18&_lat=38.895039&_lbs_city=%E5%A4%A7%E8%BF%9E&_lbs_province=%E8%BE%BD%E5%AE%81%E7%9C%81&_lng=121.544741&_mac=c0%3Aee%3Afb%3A47%3Ae1%3Aa7&_mcode=DF8F0C00&_net=wifi&_nudid=8912d2847acc785d&_os=5.1.1_ONEA2001&_os_name=ONEA2001&_province=22&_udid=867271028756970&_v=7.0.2&_version=7.02&act=pre&last_time=1479563489
     @GET
     Observable<HotBean> getHotBean(@Url String s);
}
