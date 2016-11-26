package com.example.acer.myzhibo.ui.Search;

import com.example.acer.myzhibo.bean.SearchBean;
import com.google.gson.JsonObject;

import retrofit2.http.Body;
import rx.Observable;

/**
 * Created by acer on 2016/11/23.
 */

public class SearchContract {


    public interface ISearchModel{
        Observable<SearchBean> getSearchReponse(JsonObject jsonObject);
    }

    public interface  ISearchPresenter{
       void getSearchReponse(JsonObject jsonObject);
    }

    public interface  ISearchView{
        void getSearchBean(SearchBean bean);

        void fail();
    }
}
