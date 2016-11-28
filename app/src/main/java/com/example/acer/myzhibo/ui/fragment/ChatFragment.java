package com.example.acer.myzhibo.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.ChatAdapter;
import com.example.acer.myzhibo.bean.ChatBean;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private Context mContext ;
    private ListView listView ;
    private ChatAdapter adapter;
    private List<ChatBean> list ;
    private Button btn1,btn2,btn3;
    private EditText editText;
    private String username ;
    private  EMMessageListener msgListener;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0 :
                    adapter.notifyDataSetChanged();
                    listView.setSelection(list.size()-1);
                    break;
            }
        }
    };

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = new ArrayList<>();
        initView(view);
        initAdapter();
        sendmsglistener();
        grouplistener();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username ="jhksljalsdfasf";
                EMClient.getInstance().login("lalala", "123123", new EMCallBack() {
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
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                username = "aaa123546sdfsf";
                EMClient.getInstance().login("aaa123546sdfsf","ccc",new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.e("main", "登录聊天服务器成功！");


                    }
                    @Override
                    public void onProgress(int progress, String status) {
                    }
                    @Override
                    public void onError(int code, String message) {
                        Log.d("main", "登录聊天服务器失败！");
                    }
                });
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                        EMMessage message = EMMessage.createTxtSendMessage(editText.getEditableText()+"", "1480043615023");
                        //如果是群聊，设置chattype，默认是单聊
                        //if (chatType == CHATTYPE_GROUP)
                        message.setChatType(EMMessage.ChatType.GroupChat);
                        EMClient.getInstance().chatManager().sendMessage(message);
                        message.setMessageStatusCallback(new EMCallBack(){
                            @Override
                            public void onSuccess() {
                                Log.e("TAG", "onSuccess: " );
                                ChatBean bean = new ChatBean(username,editText.getEditableText()+"");
                                list.add(bean);
                                handler.sendEmptyMessage(0);
                            }
                            @Override
                            public void onError(int i, String s) {
                            }
                            @Override
                            public void onProgress(int i, String s) {
                            }
                        });
                    }
                }).start();
            }
        });
    }
    private void initAdapter() {
        adapter = new ChatAdapter(mContext,list);
        listView.setDividerHeight(0);
        listView.setAdapter(adapter);
    }
    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview_chat);
        btn1 = (Button) view.findViewById(R.id.btn_1_chat);
        btn2 = (Button) view.findViewById(R.id.btn_2_chat);
        btn3 = (Button) view.findViewById(R.id.btn_3_chat);
        editText = (EditText) view.findViewById(R.id.edit_tv_chat);
    }
    void sendmsglistener(){
         msgListener = new EMMessageListener() {
            @Override
            public void onMessageReceived(List<EMMessage> messages) {
                //收到消息
                for (int i = 0; i <messages.size() ; i++) {
                    Log.e("TAG", "onMessageReceived: "+messages );
                    Log.e("TAG", "onMessageReceived: "+messages.get(i)+messages.get(i).getMsgId() );
                    EMMessage message = messages.get(i);
                    String from = message.getFrom();
                    Log.e("TAG", "onMessageReceived: "+from );
                    if(message.getType()== EMMessage.Type.TXT){
                        EMTextMessageBody body = (EMTextMessageBody) message.getBody();
                        Log.e("TAG", "onMessageReceived: "+body.getMessage()+message.getUserName() );
                        ChatBean bean = new ChatBean(message.getUserName(),body.getMessage());
                        list.add(bean);
                        handler.sendEmptyMessage(0);
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
    @Override
    public void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        leaveGroup();
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
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

    void joinGroup(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().groupManager().joinGroup("1480043615023");

                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.e("TAG", "run: "+e);
                    Log.e("TAG", "run: 失败");
                    //com.hyphenate.exceptions.HyphenateException: User already joined the group
                }
            }
        }).start();;
    }


    void grouplistener(){
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

}
