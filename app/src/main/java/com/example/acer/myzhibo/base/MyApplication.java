package com.example.acer.myzhibo.base;

import android.app.Application;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.Bmob;

/**
 * Created by acer on 2016/11/15.
 */

public class MyApplication extends Application{




    public static MyApplication app;

    public MyApplication() {
         app =this;
    }

    public static MyApplication getInstance() {
        if (app == null) {
            app = new MyApplication();
        }
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bomb云
        Bmob.initialize(this, "24ff0409f444abe2e89021c28df839cc");

        //环信
        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        options.setAutoLogin(false);

//初始化
        EMClient.getInstance().init(getApplicationContext(), options);
    //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        EMGroupManager.EMGroupOptions option = new EMGroupManager.EMGroupOptions();
        option.maxUsers = 200;
        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;

        try {
            EMClient.getInstance().groupManager().createGroup("aaa", "miaoshu", new String[] {} ,"ceshi", option);
        } catch (HyphenateException e) {
            e.printStackTrace();
            Log.e("TAG", "onCreate: "+e );
        }

//        Glide.get(this).register(GlideUrl.class
//                , InputStream.class, new OkHttpUrlLoader.Factory(OkHttpClientHelper.getOkHttpSingletonInstance()));

    }
}
