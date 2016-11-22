package com.example.acer.myzhibo.http;

import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.utils.FileManger;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by acer on 2016/11/15.
 */

public class HttpUtils {
    private static HttpUtils instance;
    private Retrofit retrofit ;
    private IRetrofitInterface retrofitInterface;
    private HttpUtils( ){
         init();
    }

    private void init( ) {
        retrofit =  new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(getClinet())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static HttpUtils getInstance( ) {
        if (instance == null) {
            instance = new HttpUtils();
        }
        return instance;
    }
    private OkHttpClient getClinet() {
        Cache cache = new Cache(FileManger.getHttpCache(), 20 * 1024 * 1024);

        OkHttpClient  mOkHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();


        //开启响应缓存
       // mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

         return  mOkHttpClient;

    }



    private   Retrofit getRetrofit(){
           retrofit = new Retrofit.Builder()
                  .baseUrl(UrlConfig.BASE_URL)
                  .client(getClinet())
                  .addConverterFactory(GsonConverterFactory.create())
                  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                  .build();
          return  retrofit;
      }

    public IRetrofitInterface  getRetrofitInterface(){
        if(retrofitInterface ==null){
             retrofitInterface = retrofit.create(IRetrofitInterface.class);
        }
        return retrofitInterface;
    }


    public static Map<String,String>getPlayMap(String city){
        //http://wl.myzaker.com/?_appid=AndroidPhone&_v=7.0.2&_version=7.02&c=columns&city=%E5%8C%97%E4%BA%AC
        Map<String,String> map = new Hashtable<>();
        map.put("_appid","AndroidPhone");
        map.put("_v","7.0.2");
        map.put("_version","7.02");
        map.put("c","columns");
        map.put("city",city);
        return map;
    }
    public static Map<String,String>getPositionMap(String city){
        //http://wl.myzaker.com/?_appid=AndroidPhone&_v=7.0.2&_version=7.02&c=city_list&lat=39.913249&lng=116.403625
        //可不拼接经纬度
        Map<String,String> map = new Hashtable<>();
        map.put("_appid","AndroidPhone");
        map.put("_v","7.0.2");
        map.put("_version","7.02");
        map.put("c","city_list");
        return map;
    }

    public static Map<String,String>getFunItemsMap(String city,int page,int category){
// public static final String URL_YANYI ="http://wl.myzaker.com/?c=activity_list&city=beijing&p=0&size=20&category=1";
// public static final String URL_DUJIA ="http://wl.myzaker.com/?c=activity_list&p=0&city=beijing&size=20&category=4";
//public static  final String URL_DIANYING ="http://wl.myzaker.com/?_appid=AndroidPhone&_v=7.0.2&_version=7.02&c=movie_list&city=beijing";
//public static final String URL_MEISHI ="http://wl.myzaker.com/?c=activity_list&p=0&city=beijing&size=20&category=9";
//public static final String URL_HUODONG ="http://wl.myzaker.com/?c=activity_list&p=0&city=beijing&size=20&category=3";
        Map<String,String> map = new Hashtable<>();
        //map.put("_appid","AndroidPhone");
        //map.put("_v","7.0.2");
        //map.put("_version","7.02");
        //c=activity_list&city=beijing&p=0&size=20&category=1
        map.put("c","activity_list");
        map.put("category",category+"");
        map.put("city",city);
        map.put("p",page+"");
        map.put("size",20+"");

        return map;
    }

    public static Map<String,String>getFunMoviewMap(String city){

        Map<String,String> map = new Hashtable<>();
        //http://wl.myzaker.com/?_appid=AndroidPhone&_v=7.0.2&_version=7.02&c=movie_list&city=beijing"
        map.put("_appid","AndroidPhone");
        map.put("_v","7.0.2");
        map.put("_version","7.02");
        map.put("c","movie_list");
        map.put("city",city);
        return map;
    }


}
