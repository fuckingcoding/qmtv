package com.example.acer.myzhibo.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.TulingAdapter;
import com.example.acer.myzhibo.bean.TulingChatBean;
import com.example.acer.myzhibo.bean.TulingResponseBean;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;
import com.example.acer.myzhibo.utils.RecyclerViewAdapterHelper;
import com.example.acer.myzhibo.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TulingActivity extends AppCompatActivity {
    private Context mContext =this;
    private RecyclerView recyclerView ;
    private EditText editText ;
    private Button btn_left ,btn_right;
    private TulingAdapter adapter ;
    private List<TulingChatBean> list;
    private String username = "master";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuling);
        list = new ArrayList<>();
        initView();
        initAdapter();

    }

    private void initAdapter() {

        adapter = new TulingAdapter(mContext,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void initView() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_tuling);
        btn_left = (Button) findViewById(R.id.btn_tuling_left);
        btn_right = (Button) findViewById(R.id.btn_tuling_right);
        editText = (EditText) findViewById(R.id.edit_tuling);

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getEditableText() + "";
                if(!TextUtils.isEmpty(content)){

                    TulingChatBean bean = new TulingChatBean(0,content,username);
                    list.add(bean);
                    adapter.notifyItemInserted(list.size()-1);
                    recyclerView.smoothScrollToPosition(list.size()-1);

                    getTulingAnswer();
                }
            }
        });

    }

    public void getTulingAnswer() {

        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        String format = String.format(UrlConfig.TULING_url, editText.getEditableText() + "");
        Observable<TulingResponseBean> observable = retrofitInterface.getTulingData(format);
        observable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TulingResponseBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(TulingResponseBean tulingResponseBean) {
                            String content = tulingResponseBean.getResult().getText();


                            TulingChatBean bean = new TulingChatBean(1,content,"小图");
                            list.add(bean);
                            ToastHelper.showToast(mContext,content);
                            adapter.notifyItemInserted(list.size()-1);
                            recyclerView.smoothScrollToPosition(list.size()-1);

                        }
                    });

    }
}
