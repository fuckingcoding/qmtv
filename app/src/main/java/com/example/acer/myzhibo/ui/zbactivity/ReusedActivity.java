package com.example.acer.myzhibo.ui.zbactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.zycadapter.MyReusedAdapter;
import com.example.acer.myzhibo.bean.DataBean;
import com.example.acer.myzhibo.bean.QMBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.http.IRetrofitInterface;

import java.util.ArrayList;
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
    private List<DataBean> data = new ArrayList<>();
    private MyReusedAdapter mrAdapter;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reused);

        initData();
        initView();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initData() {
        Intent intent =getIntent();
        i = intent.getIntExtra("1", 1);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IRetrofitInterface iRetrofitInterface = retrofit.create(IRetrofitInterface.class);
        Observable<QMBean> qmBean = iRetrofitInterface.getQMBean(UrlConfig.URLSTRING[i]);
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
                        Log.i("TAG", "onNext: "+qmBean.getData().size());
                        data.addAll(qmBean.getData());
                        mrAdapter.notifyDataSetChanged();

                    }
                });
    }

    private void initView() {
        mrAdapter = new MyReusedAdapter(mContext,data);
        imageView = (ImageView) findViewById(R.id.iv_toolbar_back);
        textView = (TextView) findViewById(R.id.tv_toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.rv_reused);
        textView.setText(UrlConfig.ColumnName[i]);
        GridLayoutManager glm = new GridLayoutManager(mContext,2, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(glm);
        recyclerView.setAdapter(mrAdapter);
    }
}
