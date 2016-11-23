package com.example.acer.myzhibo.ui.zbactivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.DataBean;
import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReusedActivity extends AppCompatActivity {
    private Context mContext = this;
    private ImageView imageView;
    private TextView textView;
    private RecyclerView recyclerView;
    private List<DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reused);
        initView();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IRetrofitInterface iRetrofitInterface = retrofit.create(IRetrofitInterface.class);
        Observable<QMBean> qmBean = iRetrofitInterface.getQMBean("");
        qmBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QMBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(QMBean qmBean) {
                        data = qmBean.getData();

                    }
                });
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.iv_toolbar_back);
        textView = (TextView) findViewById(R.id.tv_toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.rv_reused);
    }
}
