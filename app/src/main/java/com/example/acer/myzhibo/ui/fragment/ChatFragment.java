package com.example.acer.myzhibo.ui.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.ChatAdapter;
import com.example.acer.myzhibo.adapter.zycadapter.HotEvent;
import com.example.acer.myzhibo.adapter.zycadapter.MyListViewHotAdapter;
import com.example.acer.myzhibo.bean.ChatBean;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.ui.Listener;
import com.example.acer.myzhibo.ui.PlayActivity;
import com.example.acer.myzhibo.utils.ToastHelper;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMGroupChangeListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.chat.adapter.message.EMAMessage;
import com.hyphenate.exceptions.HyphenateException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {
    private Context mContext ;
    private ListView listView ;
    private ChatAdapter adapter;
    private ImageView iv_hot;
    private List<ChatBean> list ;
    private ImageView iv_3;
    private EditText editText;
    private String username ;
    private ListView pop_listView;
    private PopupWindow pw_qxd;
    private MyListViewHotAdapter mlvha;
    private List<String> strings=new ArrayList<>();
    private  EMMessageListener msgListener;
    private Activity mActivity;
    private EMMessage message;
    private String danmuString;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0 :
                    adapter.notifyDataSetChanged();
                    listView.setSelection(list.size()-1);
                    editText.setText("");
                    break;
                case 1 :
                    ((PlayActivity)mActivity).getfragmentMsg("",true);
                    break;
            }
        }
    };
    private Listener listener =new Listener() {
        @Override
        public void send(String str) {
            editText.setText(str);
            pw_qxd.dismiss();
        }
    };

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }
    @Subscribe
    public void onEventMainThread(HotEvent event){
        strings = event.getmHot_List();
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
        username = PreUtils.readStrting(mContext,"loginname");
        EventBus.getDefault().register(this);
        list = new ArrayList<>();
        initView(view);
        initAdapter();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        joinGroup();
        sendmsglistener();
        grouplistener();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getEditableText()!=null){


                Log.e("TAG", "onClick: chatfragment -------------" );
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                         message = EMMessage.createTxtSendMessage(editText.getEditableText()+"", "1480043615023");
                        //如果是群聊，设置chattype，默认是单聊
                        //if (chatType == CHATTYPE_GROUP)
                        message.setChatType(EMMessage.ChatType.GroupChat);
                        EMClient.getInstance().chatManager().sendMessage(message);
                        Log.e("TAG", "onClickrunnnnn------ chatfragment -" );
                        message.setMessageStatusCallback(new EMCallBack(){

                            @Override
                            public void onSuccess() {
                                Log.e("TAG", "onSuccess: " );
                                if(editText.getEditableText()!=null){

                                    ChatBean bean = new ChatBean(username,editText.getEditableText()+"");
                                    list.add(bean);
                                    handler.sendEmptyMessage(0);

                                }
                            }
                            @Override
                            public void onError(int i, String s) {
                                Log.e("TAG", "onError: "+s);
                            }
                            @Override
                            public void onProgress(int i, String s) {
                                Log.e("TAG", "onProgress: "+s );
                            }
                        });

                    }
                }).start();
                }
            }
        });
        iv_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlvha = new MyListViewHotAdapter(mContext,strings,listener);
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_hot, null);
                pop_listView = (ListView) inflate.findViewById(R.id.lv_hot);
                pop_listView.setAdapter(mlvha);
                mlvha.notifyDataSetChanged();
                pw_qxd = new PopupWindow(inflate,
                        ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
                pw_qxd.setTouchable(true);
                pw_qxd.setOutsideTouchable(true);
                pw_qxd.showAtLocation(inflate, Gravity.BOTTOM|Gravity.LEFT,0,0);

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
        iv_3 = (ImageView) view.findViewById(R.id.btn_3_chat);
        iv_hot = (ImageView) view.findViewById(R.id.iv_chat_hot);
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
                       // ((PlayActivity)mActivity).getfragmentMsg(body.getMessage(),false);
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
                    if(e.toString().equals("com.hyphenate.exceptions.HyphenateException: User already joined the group")){
                        Log.e("TAG", "run: "+e);
                        Log.e("TAG", "run: 失败");
                    }

                }
            }
        }).start();
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


     public  void getEditMsg( String string){
         if(!TextUtils.isEmpty(string)){


         danmuString =string;
         new Thread(new Runnable() {
             @Override
             public void run() {

                 //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                 EMMessage message = EMMessage.createTxtSendMessage(danmuString, "1480043615023");
                 //如果是群聊，设置chattype，默认是单聊
                 //if (chatType == CHATTYPE_GROUP)
                 message.setChatType(EMMessage.ChatType.GroupChat);
                 EMClient.getInstance().chatManager().sendMessage(message);
                 message.setMessageStatusCallback(new EMCallBack(){
                     @Override
                     public void onSuccess() {
                         Log.e("TAG", "onSuccess: " );
                         if(editText.getEditableText()!=null){
                             ChatBean bean = new ChatBean(username,danmuString);
                             list.add(bean);
                             handler.sendEmptyMessage(0);
                             handler.sendEmptyMessage(1);
                         }
                     }
                     @Override
                     public void onError(int i, String s) {
                         Log.e("TAG", "onError: "+s);
                     }
                     @Override
                     public void onProgress(int i, String s) {
                         Log.e("TAG", "onProgress: "+s );
                     }
                 });
             }
         }).start();

     }
     }
}
