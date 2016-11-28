package com.example.acer.myzhibo.ui.flush;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.ui.MainActivity;


public class FlushActivity extends AppCompatActivity {

    private static final long SLEEPTIME_TOTAL=2000;

    private  boolean IsGoToMain;

    private static final String KEY_ISGOTOMAIN="KEY_ISGOTOMAIN";

    private Context mContext=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flush);


        new MyAsyncTask().execute();
        initPre();
    }

    private void initPre() {
        PreUtils.writeString(mContext,"defult",UrlConfig.Defult);

        PreUtils.writeString(mContext,"other",UrlConfig.Other);
    }


    class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            long timeMillis = System.currentTimeMillis();

           //此处进行一些耗时操作


            long timeMillis2 = System.currentTimeMillis();


            long time = timeMillis2 - timeMillis;
            if(time<SLEEPTIME_TOTAL){
                try {
                    Thread.sleep(SLEEPTIME_TOTAL-time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(FlushActivity.this, MainActivity.class);
//            IsGoToMain = PreUtils.readBoolean(FlushActivity.this,KEY_ISGOTOMAIN);
//            if (IsGoToMain){
//                intent.setClass(FlushActivity.this,MainActivity.class);
//            }else{
//                intent.setClass(FlushActivity.this,GuideActivity.class);
//                  PreUtils.writeBoolean(FlushActivity.this,KEY_ISGOTOMAIN,true);
//            }


            startActivity(intent);
            //第一个参数为第二个activity进入效果  第二个参数：前一个activity退出效果
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

            finish();


        }
    }



}
