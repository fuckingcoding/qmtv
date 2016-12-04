package com.example.acer.myzhibo.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

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
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuling);
        list = new ArrayList<>();
        initView();
        initAdapter();
        TulingChatBean bean = new TulingChatBean(0,"你好我是小图",username);
        list.add(bean);
        adapter.notifyItemInserted(list.size()-1);

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

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View rootview = inflater.inflate(R.layout.popup_tuling,null,false);
                popupWindow = new PopupWindow(rootview, LinearLayoutCompat.LayoutParams.WRAP_CONTENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT);
                popupWindow.setAnimationStyle(R.style.anim_pop_bottombar);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                setBackgroundAlpha(0.5f);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(btn_left,0,20);
                View id = rootview.findViewById(R.id.tv_poptuling1);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setBackgroundAlpha(1.0f);
                    }
                });

                final TextView tv1 = (TextView) rootview.findViewById(R.id.tv_poptuling1);
                final TextView tv2 = (TextView) rootview.findViewById(R.id.tv_poptuling2);
                final TextView tv3 = (TextView) rootview.findViewById(R.id.tv_poptuling3);
                tv1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editText.setText(tv1.getText());
                        popupWindow.dismiss();
                    }
                });

                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editText.setText(tv2.getText());
                        popupWindow.dismiss();
                    }
                });
                   tv3.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           editText.setText(tv3.getText());
                           popupWindow.dismiss();
                       }
                   });

            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editText.getEditableText() + "";
                if(!TextUtils.isEmpty(content)){

                    TulingChatBean bean = new TulingChatBean(1,content,username);
                    list.add(bean);
                    adapter.notifyItemInserted(list.size()-1);
                    recyclerView.smoothScrollToPosition(list.size()-1);

                    getTulingAnswer();
                    editText.setText("");
                }
            }
        });

    }

    public void getTulingAnswer() {

        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        String format = String.format(UrlConfig.TULING_url, editText.getEditableText() + "");
        Log.e("TAG", "getTulingAnswer: "+format );
        Observable<TulingResponseBean> observable = retrofitInterface.getTulingData(format);
        observable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TulingResponseBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                             ToastHelper.showToast(mContext,"网络异常");
                            Log.i("TAG", "onError: "+e );
                        }

                        @Override
                        public void onNext(TulingResponseBean tulingResponseBean) {
                            String content = tulingResponseBean.getResult().getText();
                            TulingChatBean bean = new TulingChatBean(0,content,"小图");
                            list.add(bean);
                            ToastHelper.showToast(mContext,content);
                            adapter.notifyItemInserted(list.size()-1);
                            recyclerView.smoothScrollToPosition(list.size()-1);

                        }
                    });

    }


    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }
}
