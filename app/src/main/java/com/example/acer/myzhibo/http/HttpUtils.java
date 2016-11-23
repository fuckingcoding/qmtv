package com.example.acer.myzhibo.http;

import android.graphics.Bitmap;

import com.example.acer.myzhibo.R;
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



    public  static String getLoadMorePath(String type ,int page){

        String format = String.format(UrlConfig.MOREQMBEANSTRING, type, page);
        return  format;
    }


}
