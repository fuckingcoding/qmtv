package com.example.acer.myzhibo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.ui.fragment.My.MineContract;
import com.example.acer.myzhibo.utils.CacheManagerHelper;
import com.example.acer.myzhibo.utils.ToastHelper;

public class SettingActivity extends AppCompatActivity {
    private Context mContext = this ;
    private  String totalCacheSize;
   private ImageView iv_back ;
    private TextView tv_huancun;
    private SwitchCompat switchview ;
    private RelativeLayout relativeLayout_download ,relativeLayout_about,relativeLayout_huancun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        initView();
    }

    private void initView() {
        boolean b = PreUtils.readBoolean(mContext, Constant.WIFIALERT);
        try {
           totalCacheSize = CacheManagerHelper.getTotalCacheSize(getApplicationContext());

        } catch (Exception e) {
            e.printStackTrace();
        }
        switchview = (SwitchCompat) findViewById(R.id.swich_setting);
        switchview.setChecked(b);
        iv_back = (ImageView) findViewById(R.id.iv_setting_back);
        relativeLayout_about = (RelativeLayout) findViewById(R.id.relative_setting_about);
        relativeLayout_download = (RelativeLayout) findViewById(R.id.relative_setting_download);
        relativeLayout_huancun = (RelativeLayout) findViewById(R.id.relative_setting_huancun);
        tv_huancun = (TextView) findViewById(R.id.tv_huancun);
        tv_huancun.setText(totalCacheSize);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  finish();
            }
        });

        relativeLayout_huancun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheManagerHelper.clearApplicationCache(getApplicationContext());
                try {
                    totalCacheSize = CacheManagerHelper.getTotalCacheSize(getApplicationContext());
                    tv_huancun.setText(totalCacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        relativeLayout_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,AboutUsActivity.class);
                startActivity(intent);
            }
        });

        relativeLayout_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        switchview.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    ToastHelper.showToast(mContext ,"open");
                    PreUtils.writeBoolean(mContext, Constant.WIFIALERT,true);
                    tv_huancun.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_launcher,0,0,0);
                }else{
                    tv_huancun.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
                    PreUtils.writeBoolean(mContext, Constant.WIFIALERT,false);
                }
            }
        });
    }
}
