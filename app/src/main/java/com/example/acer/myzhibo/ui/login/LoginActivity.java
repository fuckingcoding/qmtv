package com.example.acer.myzhibo.ui.login;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.transition.Explode;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.Users;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.ui.MainActivity;
import com.example.acer.myzhibo.utils.EncryptUtils;
import com.example.acer.myzhibo.utils.HuanXinUtils;
import com.hyphenate.EMCallBack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.R.attr.name;

public class LoginActivity extends AppCompatActivity {

    private Context mContext=this;

    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.bt_go)
    Button btGo;
    @InjectView(R.id.cv)
    CardView cv;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    private String pssw21;
    private String name21;
//21
private EditText phoneEdt, pswEdt;
    private ImageView clearBtn, showBtn;
    private Button loginBtn,mzhuce;

    private boolean isShow;

    private String pssw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21) {
            setContentView(R.layout.activity_login);
            ButterKnife.inject(this);
        }else{
            setContentView(R.layout.activity_login_21);
            initView();
           // iniBomb();
        }


    }
    private void iniBomb() {
        //提供以下两种方式进行初始化操作：

        //第一：默认初始化
      //  Bmob.initialize(this, "643a383f5bb4d365e8d37692ae1fd8d5");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
    }

    private void initView() {
        phoneEdt = (EditText) findViewById(R.id.login_phone_edt);
        pswEdt = (EditText) findViewById(R.id.login_psw_edt);
        clearBtn = (ImageView) findViewById(R.id.login_clear_btn);
        showBtn = (ImageView) findViewById(R.id.login_show_btn);
        loginBtn = (Button) findViewById(R.id.login_btn);
        mzhuce=(Button)findViewById(R.id.login_zhuce) ;
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneEdt.setText("");
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isShow = !isShow;
                setPassworldIsVisible(isShow, pswEdt);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginlow();
            }
        });
        mzhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        //当有输入内容的时候，清空按钮可见，当没有输入内容的时候，清空按钮不可见
        //对phoneEdt输入框进行输入监听
        phoneEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //获得当前输入内容
                String phone = phoneEdt.getEditableText().toString();
                if (TextUtils.isEmpty(phone)) { //当没有输入内容的时候，清空按钮不可见
                    clearBtn.setVisibility(View.INVISIBLE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        //焦点改变监听
        phoneEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String phone = phoneEdt.getEditableText().toString();
                if (b && !TextUtils.isEmpty(phone)) { //有焦点且有内容，清空按钮可见
                    clearBtn.setVisibility(View.VISIBLE);
                } else {
                    clearBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        //监听登录的软键盘
        pswEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                Log.e("Tag", "actionId = " + actionId);
                loginlow();
                return false; //ruturn true，软键盘不消失
            }
        });

    }





    /**
     * 设置输入框密码是否可见
     *
     * @param isShow 输入框密码是否可见
     * @param pswEdt 输入框
     */
    private void setPassworldIsVisible(boolean isShow, EditText pswEdt) {
        if (isShow) { //可见
            showBtn.setImageResource(R.mipmap.switch_in_show);
            pswEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            showBtn.setImageResource(R.mipmap.switch_in_hide);
            pswEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        //切换后将EditText光标置于末尾
        CharSequence charSequence = pswEdt.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    private void loginlow() {

        pssw = pswEdt.getEditableText().toString();
        final String name1 = phoneEdt.getEditableText().toString();

        String pswRegEx = "^\\d{8}$";
        Pattern pattern = Pattern.compile(pswRegEx);
        Matcher matcher = pattern.matcher(pssw);
        boolean b = matcher.matches();

        //输入内容非空判断
        if (b) {
            BmobQuery<Users> bmobQuery = new BmobQuery<>();
            bmobQuery.addWhereEqualTo("name", name1);
            bmobQuery.findObjects(new FindListener<Users>() {
                @Override
                public void done(List<Users> list, BmobException e) {
                    if (e == null) {
                        if (list.get(0).getPassword().equals(EncryptUtils.md5(pssw))) {
                            Log.e("TAG", "done: "+ Thread.currentThread());
                           // new Thread(new Runnable() {
                             //  @Override
                             //  public void run() {

                                   HuanXinUtils.HuanxinLogin(name1, new EMCallBack() {
                                       @Override
                                       public void onSuccess() {
                                           Log.e("TAG", "onSuccesslogin: " );

                                           PreUtils.writeString(LoginActivity.this,"loginname",name1);
                                           //Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                       }

                                       @Override
                                       public void onError(int i, String s) {
                                           //Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                       }

                                       @Override
                                       public void onProgress(int i, String s) {
                                          // Toast.makeText(LoginActivity.this, "登录中", Toast.LENGTH_SHORT).show();
                                       }
                                   });

                              // }
                          // }).start();
                            PreUtils.writeBoolean(LoginActivity.this, "login", true);
                            String username=list.get(0).getName();
                            PreUtils.writeString(LoginActivity.this, "username", username);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "密码不匹配" + list.get(0).getPassword(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名无效" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }








    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        if(Build.VERSION.SDK_INT>=21) {
            switch (view.getId()) {
                case R.id.fab:
                    getWindow().setExitTransition(null);
                    getWindow().setEnterTransition(null);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options =
                                ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                        startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
                    } else {
                        startActivity(new Intent(this, RegisterActivity.class));
                    }
                    break;
                case R.id.bt_go:
                    Explode explode = new Explode();
                    explode.setDuration(500);

                    getWindow().setExitTransition(explode);
                    getWindow().setEnterTransition(explode);
                    // ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                    // Intent i2 = new Intent(this,LoginSuccessActivity.class);
                    //startActivity(i2, oc2.toBundle());
                    login();
                    break;
            }
        }
    }


    private void login() {

        pssw21 = etPassword.getEditableText().toString();
        Log.e("q", "login: "+pssw21 );
        name21 = etUsername.getEditableText().toString();
        Log.e("q", "login: "+name );

        String pswRegEx = "^\\d{8}$";
        Pattern pattern = Pattern.compile(pswRegEx);
        Matcher matcher = pattern.matcher(pssw21);
        boolean b = matcher.matches();

        //输入内容非空判断
        if (b) {
            BmobQuery<Users> bmobQuery = new BmobQuery<>();
            bmobQuery.addWhereEqualTo("name", name21);
            bmobQuery.findObjects(new FindListener<Users>() {
                @Override
                public void done(List<Users> list, BmobException e) {
                    if (e == null) {
                        if (list.get(0).getPassword().equals(EncryptUtils.md5(pssw21))) {

                            HuanXinUtils.HuanxinLogin(name21, new EMCallBack() {
                                @Override
                                public void onSuccess() {
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    mContext.getMainLooper();
                                    PreUtils.writeString(LoginActivity.this,"loginname",name21);
                                }

                                @Override
                                public void onError(int i, String s) {
                                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onProgress(int i, String s) {
                                    Toast.makeText(LoginActivity.this, "登录中", Toast.LENGTH_SHORT).show();
                                }
                            });

                            PreUtils.writeBoolean(LoginActivity.this, "login", true);
                            String username=list.get(0).getName();
                            PreUtils.writeString(LoginActivity.this, "username", username);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "密码不匹配" + list.get(0).getPassword(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名无效" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }





}


