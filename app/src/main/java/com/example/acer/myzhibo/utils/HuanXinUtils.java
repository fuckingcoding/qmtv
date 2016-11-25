package com.example.acer.myzhibo.utils;

import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroupManager;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by acer on 2016/11/25.
 */

public class HuanXinUtils {

    void HxLogin() {

        EMClient.getInstance().login("jhksljalsdfasf", "123456", new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.e("main", "登录聊天服务器成功！");

            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {
                Log.d("main", "登录聊天服务器失败！");
            }//回调
        });
    }

     void register(){
         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     EMClient.getInstance().createAccount("aaa123546sdfsf", "ccc");
                 } catch (HyphenateException e) {
                     e.printStackTrace();
                     Log.e("TAG", "run: "+e );
                     if(e.toString().equals("com.hyphenate.exceptions.HyphenateException: User already exist")){
                         Log.e("TAG", "run: "+"用户已经存在" );
                     }
                 }
             }
         }).start();
     }
    void createGroup(){
        EMGroupManager.EMGroupOptions option = new EMGroupManager.EMGroupOptions();
        option.maxUsers = 200;
        option.style = EMGroupManager.EMGroupStyle.EMGroupStylePublicOpenJoin;
        try {
            EMClient.getInstance().groupManager().createGroup("aaa", "miaoshu", new String[] {} ,"ceshi", option);
        } catch (HyphenateException e) {
            e.printStackTrace();
            Log.e("TAG", "onCreate: "+e );
        }
    }

    void joinGroup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().groupManager().joinGroup("1480043615023");
                    group();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("TAG", "run: "+e);
                    Log.e("TAG", "run: 失败");
                    //com.hyphenate.exceptions.HyphenateException: User already joined the group
                }
            }
        }).start();;
    }

    void sendMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage("askjlasdf", "1480043615023");
                //如果是群聊，设置chattype，默认是单聊
                //if (chatType == CHATTYPE_GROUP)
                message.setChatType(EMMessage.ChatType.GroupChat);
                EMClient.getInstance().chatManager().sendMessage(message);
                message.setMessageStatusCallback(new EMCallBack(){
                    @Override
                    public void onSuccess() {
                        Log.e("TAG", "onSuccess: " );
                    }

                    @Override
                    public void onError(int i, String s) {

                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                sendmsg();
            }
        }).start();
    }


    void leaveGroup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().groupManager().leaveGroup("1480043615023");//需异步处理
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    void group(){
        EMClient.getInstance().groupManager().addGroupChangeListener(new EMGroupChangeListener() {
            @Override
            public void onUserRemoved(String groupId, String groupName) {
                //当前用户被管理员移除出群组
            }
            @Override
            public void onGroupDestroyed(String groupId, String groupName) {

            }
            @Override
            public void onAutoAcceptInvitationFromGroup(String s, String s1, String s2) {

            }
            @Override
            public void onInvitationReceived(String groupId, String groupName, String inviter, String reason) {
                //收到加入群组的邀请
            }
            @Override
            public void onInvitationDeclined(String groupId, String invitee, String reason) {
                //群组邀请被拒绝
            }
            @Override
            public void onApplicationReceived(String groupId, String groupName, String applyer, String reason) {
                //收到加群申请
            }
            @Override
            public void onApplicationAccept(String groupId, String groupName, String accepter) {
                //加群申请被同意
            }
            @Override
            public void onApplicationDeclined(String groupId, String groupName, String decliner, String reason) {
                // 加群申请被拒绝
            }

            @Override
            public void onInvitationAccepted(String groupId, String inviter, String reason) {

            }
        });
    }


    void sendmsg(){
        EMMessageListener msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                Log.e("TAG", "xiaoshi shoudao : " );
                for (int i = 0; i <messages.size() ; i++) {
                    Log.e("TAG", "onMessageReceived: "+messages );
                    Log.e("TAG", "onMessageReceived: "+messages.get(i)+messages.get(0).getMsgId() );
                    EMMessage message = messages.get(i);
                    if(message.getType()== EMMessage.Type.TXT){
                        EMTextMessageBody body = (EMTextMessageBody) message.getBody();
                        Log.e("TAG", "onMessageReceived: "+body.getMessage()+message.getUserName() );
                    }
                }
            }
            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }
            @Override
            public void onMessageReadAckReceived(List<EMMessage> messages) {
                //收到已读回执
            }
            @Override
            public void onMessageDeliveryAckReceived(List<EMMessage> message) {
                //收到已送达回执
            }
            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        //  记得在不需要的时候移除listener，如在activity的onDestroy()时
        // EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }


}
