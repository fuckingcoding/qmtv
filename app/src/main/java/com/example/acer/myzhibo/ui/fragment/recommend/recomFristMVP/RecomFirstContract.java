package com.example.acer.myzhibo.ui.fragment.recommend.recomFristMVP;

import com.example.acer.myzhibo.base.IBaseModel;
import com.example.acer.myzhibo.base.IBasePresenter;
import com.example.acer.myzhibo.base.IBaseView;
import com.example.acer.myzhibo.bean.RecomBean;

import rx.Observable;

/**
 * Created by apple on 16/11/24.
 * Eemil:635727195@qq.com
 */

public class RecomFirstContract {
    public    interface  IRoomModel extends IBaseModel {
        Observable<RecomBean> getBean(String url);
    }

    public    interface  IRoomPresenter extends IBasePresenter {
        void getBean(String url);
    }

    public    interface  IRoomView extends IBaseView {
        void getData(RecomBean bean);
    }
}
