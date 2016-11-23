package com.example.acer.myzhibo.ui.Search;

import com.example.acer.myzhibo.bean.SearchBean;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;
import com.google.gson.JsonObject;

import rx.Observable;

/**
 * Created by acer on 2016/11/23.
 */

public class SearchModel implements SearchContract.ISearchModel {
    @Override
    public Observable<SearchBean> getSearchReponse(JsonObject jsonObject) {

        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<SearchBean> observable = retrofitInterface.getSearchReponse(jsonObject);
        return observable;
    }
}
