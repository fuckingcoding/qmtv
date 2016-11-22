package com.example.acer.myzhibo.utils;

import android.content.Context;
import android.content.Intent;

import com.example.acer.myzhibo.ui.MainActivity;


/**
 * Created by wanggang on 2016/11/14.
 */

public class UIManager {

    //跳转界面工具类
    public static void startMain(Context context,
                                 int code,
                                 String name){
        Intent intent=new Intent();
        intent.setClass(context,MainActivity.class);
        intent.putExtra("code",code);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }

//    public static void startSelectCity(Context context,
//                                 String cityname){
//        Intent intent=new Intent();
//        intent.setClass(context,SelectCityActivity.class);
//        intent.putExtra("name",cityname);
//        context.startActivity(intent);
//    }

//    public static void startWebActivity(Context context ,String urlstring,@Nullable String title){
//        Intent intent=new Intent();
//
//        intent.setClass(context,WebActivity.class);
//        intent.putExtra(Constant.WEB_URL,urlstring);
//        if(title!=null){
//            intent.putExtra(Constant.WEB_TITLE,title);
//        }
//        context.startActivity(intent);
//    }



}
