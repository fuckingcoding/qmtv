package com.example.acer.myzhibo.utils;

import android.content.Context;
import android.content.Intent;

import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.ui.MainActivity;
import com.example.acer.myzhibo.ui.PlayActivity;


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

   public static void startPlayActivity(Context context,String avatar,String title,String nick,String view,String uid){

       Intent intent = new Intent();
       intent.setClass(context, PlayActivity.class);
       intent.putExtra(Constant.AVATAR,avatar);
       intent.putExtra(Constant.VIEW,view);
       intent.putExtra(Constant.TITLE,title);
       intent.putExtra(Constant.NICK,nick);
       intent.putExtra(Constant.UID,uid);
       context.startActivity(intent);

   }


    public static void startPlayActivity(Context context,String avatar,String title,String nick,String view,String uid,String thumb){

        Intent intent = new Intent();
        intent.setClass(context, PlayActivity.class);
        intent.putExtra(Constant.AVATAR,avatar);
        intent.putExtra(Constant.VIEW,view);
        intent.putExtra(Constant.TITLE,title);
        intent.putExtra(Constant.NICK,nick);
        intent.putExtra(Constant.UID,uid);
        intent.putExtra(Constant.Thumb,thumb);

        context.startActivity(intent);


    }


}
