package com.example.acer.myzhibo.ui.Search;

import com.example.acer.myzhibo.bean.SearchBean;
import com.example.acer.myzhibo.http.HttpUtils;
import com.google.gson.JsonObject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by acer on 2016/11/23.
 */

public class SearchPresenter implements SearchContract.ISearchPresenter {

    private SearchContract.ISearchModel model;
    private SearchContract.ISearchView view;

    public SearchPresenter(SearchContract.ISearchView view) {
        model = new SearchModel();
        this.view = view;
    }

    @Override
    public void getSearchReponse(JsonObject jsonObject) {

        Observable<SearchBean> observable = model.getSearchReponse(jsonObject);
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Subscriber<SearchBean>() {
                      @Override
                      public void onCompleted() {

                      }

                      @Override
                      public void onError(Throwable e) {
                           view.fail();
                      }

                      @Override
                      public void onNext(SearchBean bean) {
                          view.getSearchBean(bean);
                      }
                  });

    }
}
