package com.example.acer.myzhibo.ui.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.bean.Users;
import com.example.acer.myzhibo.utils.EncryptUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    private EditText mUserNameEditText,mPassWordEditText,mRepeatPassWordEditText;

    private Button mButtonNext;

    //21

    private EditText phoneEdt, pswEdt;
    private ImageView clearBtn, showBtn;
    private Button loginBtn;

    private  boolean isShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>=21){
            setContentView(R.layout.activity_register);
            ButterKnife.inject(this);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ShowEnterAnimation();
            }

            initEditText();
        }else {
            setContentView(R.layout.activity_register_21);

            initView();
        }

    }

    private void initView() {
        phoneEdt = (EditText) findViewById(R.id.register_phone_edt);
        pswEdt = (EditText) findViewById(R.id.register_psw_edt);
        clearBtn = (ImageView) findViewById(R.id.register_clear_btn);
        showBtn = (ImageView) findViewById(R.id.register_show_btn);
        loginBtn = (Button) findViewById(R.id.register_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //一键清空的操作
                phoneEdt.setText("");
            }
        });
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置密码是否可见
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

        //当有输入内容的时候，清空按钮可见，当没有输入内容的时候，清空按钮不可见
        //对phoneEdt输入框进行输入监听
        phoneEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {}
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

        String psw = pswEdt.getEditableText().toString();
        final String username = phoneEdt.getEditableText().toString();

        String pswRegEx="^\\d{8}$";
        Pattern pattern = Pattern.compile(pswRegEx);
        Matcher matcher = pattern.matcher(psw);
        boolean b = matcher.matches();
        Users user=new Users();
        user.setName(username);
        user.setPassword(EncryptUtils.md5(psw));

        if(b) {
            Users users = new Users();
            users.setName(username);
            users.setPassword(EncryptUtils.md5(psw));
            users.save(new SaveListener<String>() {
                @Override
                public void done(String objectId, BmobException e) {
                    if (e == null) {
                       // Toast.makeText(RegisterActivity.this, "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    EMClient.getInstance().createAccount(username, "123456");
                                } catch (HyphenateException e) {
                                    e.printStackTrace();
                                    Log.e("TAG", "run: "+e );
                                    if(e.toString().equals("com.hyphenate.exceptions.HyphenateException: User already exist")){
                                        Log.e("TAG", "huanxin: "+"用户已经存在" );
                                    }
                                }
                            }
                        }).start();

                    } else {
                        Toast.makeText(RegisterActivity.this, "用户名已存在" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(RegisterActivity.this,"密码不符合要求", Toast.LENGTH_SHORT).show();
        }

    }




    private void initEditText() {
        mUserNameEditText=(EditText)findViewById(R.id.et_username);
        mPassWordEditText=(EditText)findViewById(R.id.et_password);
        mRepeatPassWordEditText=(EditText)findViewById(R.id.et_repeatpassword);
        mButtonNext=(Button)findViewById(R.id.bt_go);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        final String username21=mUserNameEditText.getEditableText().toString();
        Log.e("q", "login: "+username21 );
        String userpsw21=mPassWordEditText.getEditableText().toString();
        Log.e("q", "login: "+userpsw21 );
        String repeatpsw=mRepeatPassWordEditText.getEditableText().toString();

        String pswRegEx="^\\d{8}$";
        Pattern pattern = Pattern.compile(pswRegEx);
        Matcher matcher = pattern.matcher(userpsw21);
        boolean b = matcher.matches();
        Users user=new Users();
        user.setName(username21);
        user.setPassword(EncryptUtils.md5(userpsw21));

                if(b) {
                    Users users = new Users();
                    users.setName(username21);
                    users.setPassword(EncryptUtils.md5(userpsw21));
                    users.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegisterActivity.this, "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            EMClient.getInstance().createAccount(username21, "123456");
                                        } catch (HyphenateException e) {
                                            e.printStackTrace();
                                            Log.e("TAG", "run: "+e );
                                            if(e.toString().equals("com.hyphenate.exceptions.HyphenateException: User already exist")){
                                                Log.e("TAG", "huanxin: "+"用户已经存在" );
                                            }
                                        }
                                    }
                                }).start();
                            } else {
                                Toast.makeText(RegisterActivity.this, "用户名已存在" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"密码不符合要求.8位以上数字", Toast.LENGTH_SHORT).show();
                }

            }

    @TargetApi(19)
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        if(Build.VERSION.SDK_INT>=21) {
            getWindow().setSharedElementEnterTransition(transition);
        }
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        if(Build.VERSION.SDK_INT>=21) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    cvAdd.setVisibility(View.VISIBLE);
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }

    public void animateRevealClose() {
        if(Build.VERSION.SDK_INT>=21) {
            Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
            mAnimator.setDuration(500);
            mAnimator.setInterpolator(new AccelerateInterpolator());
            mAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    cvAdd.setVisibility(View.INVISIBLE);
                    super.onAnimationEnd(animation);
                    fab.setImageResource(R.drawable.plus);
                    RegisterActivity.super.onBackPressed();
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            mAnimator.start();
        }
    }
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }


}
