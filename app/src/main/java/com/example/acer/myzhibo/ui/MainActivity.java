package com.example.acer.myzhibo.ui;

import android.content.Intent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.Mainadapter;
import com.example.acer.myzhibo.bean.VersionBean;
import com.example.acer.myzhibo.config.Constant;
import com.example.acer.myzhibo.config.UrlConfig;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.http.HttpUtils;
import com.example.acer.myzhibo.http.IRetrofitInterface;
import com.example.acer.myzhibo.service.MyService;
import com.example.acer.myzhibo.ui.fragment.ColumnFragment;
import com.example.acer.myzhibo.ui.fragment.My.MyFragment;
import com.example.acer.myzhibo.ui.fragment.RecommendFragment;
import com.example.acer.myzhibo.ui.fragment.live.LiveFragment;

import com.example.acer.myzhibo.utils.ToastHelper;
import com.example.acer.myzhibo.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{
    //自定义viewpager
    private NoScrollViewPager mViewPager;
    //底部导航
    private RadioGroup mRadioGroup;
    //未选中图片资源
    private int[] unselectedIconIds = {R.mipmap.ic_main_tuijian,
            R.mipmap.ic_main_lanmu, R.mipmap.ic_main_live,
            R.mipmap.ic_mian_my};
    //选中图片资源
    private int[] selectedIconIds = {R.mipmap.ic_main_tuijian2,
            R.mipmap.ic_main_lanmu2, R.mipmap.ic_main_live2,
            R.mipmap.ic_main_my2};
    private List<Fragment> fragmentList = new ArrayList<>();
    private Mainadapter mainadapter;

    private  RecommendFragment recommendFragment;
    private String vsersion_url;
     private int version;
    private Subscription subscription;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();

        initView();
        getNewVersionCode();

    }



    //初始化radiobutton，viewpager
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mViewPager = (NoScrollViewPager) findViewById(R.id.noscrollviewPager_main);
        mRadioGroup.setOnCheckedChangeListener(this);

        mViewPager.addOnPageChangeListener(this);
    }
    //初始化fragment
    private void initFragment() {
      recommendFragment = new RecommendFragment();
        ColumnFragment columnFragment = new ColumnFragment();
        LiveFragment liveFragment = new LiveFragment();
        MyFragment myFragment = new MyFragment();

        fragmentList.add(recommendFragment);
        fragmentList.add(columnFragment);
        fragmentList.add(liveFragment);
        fragmentList.add(myFragment);

        mainadapter = new Mainadapter(getSupportFragmentManager(),fragmentList);
        mViewPager = (NoScrollViewPager) findViewById(R.id.noscrollviewPager_main);

        mViewPager.setAdapter(mainadapter);
        mViewPager.setOffscreenPageLimit(3);

    }
    //选中对应radiobutton时的颜色及图片变化
    private void selectPage(int position) {
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            Drawable gray = getResources().getDrawable(unselectedIconIds[i]);

            gray.setBounds(0, 0, gray.getMinimumWidth(),
                    gray.getMinimumHeight());
            RadioButton child = (RadioButton) mRadioGroup.getChildAt(i);
            child.setCompoundDrawables(null, gray, null, null);
            child.setTextColor(getResources().getColor(
                    R.color.notouchcolor));
        }
        mViewPager.setCurrentItem(position, false);
        Drawable yellow = getResources().getDrawable(selectedIconIds[position]);
        yellow.setBounds(0, 0, yellow.getMinimumWidth(),
                yellow.getMinimumHeight());
        RadioButton select = (RadioButton) mRadioGroup.getChildAt(position);
        select.setCompoundDrawables(null, yellow, null, null);
        select.setTextColor(getResources().getColor(
                R.color.touchcolor));
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.btn_main_tuijian:
                selectPage(0);
                break;
            case R.id.btn_main_lanmu:
                selectPage(1);
                break;
            case R.id.btn_main_live: //
                selectPage(2);
                break;
            case R.id.btn_main_me: //
                selectPage(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recommendFragment.Refush();
    }



    public static int getVersionCode(Context context) {
        int version = 0;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TAG", "Package name not found", e);
        };
        return version;
    }

    void getNewVersionCode(){
        IRetrofitInterface retrofitInterface = HttpUtils.getInstance().getRetrofitInterface();
        Observable<VersionBean> observable = retrofitInterface.getVersionbean(UrlConfig.VERSION_CHECK);

         subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<VersionBean>() {
                    @Override
                    public void onCompleted() {
                        int versionCode = getVersionCode(MainActivity.this);
                        if(versionCode<version){
                            PreUtils.writeBoolean(MainActivity.this,Constant.NEADUPDATE,true);
                            PreUtils.writeString(MainActivity.this,Constant.KEY_NEWVERSION_URL,vsersion_url);
                            showMyDialog();
                        }else{
                            PreUtils.writeBoolean(MainActivity.this,Constant.NEADUPDATE,false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(VersionBean versionBean) {
                        version = versionBean.getData().getVersion();
                        vsersion_url = versionBean.getData().getVsersion_url();
                        PreUtils.writeString(MainActivity.this, Constant.KEY_NEWVERSION_URL, vsersion_url);
                        PreUtils.writeInt(MainActivity.this, Constant.KEY_NEWVERSION_CODE, version);
                    }
                });

    }

    private void showMyDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("检测到新版本")
                .setIcon(R.mipmap.ic_channel_edit)
                .setCancelable(false)
                .setMessage("是否更新")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent  = new Intent(MainActivity.this, MyService.class);
                        intent.putExtra(Constant.SERVICEDOWNLOAD,vsersion_url);
                        startService(intent);
                    }
                })
                .setNegativeButton("none",null)
                .create()
                .show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!subscription.isUnsubscribed()){

            subscription.unsubscribe();
        }
    }
    private long mLastPressBackTime = 0;
    private static final int INTERVAL_OF_TWO_CLICK_TO_QUIT = 1000; // 1 seconde
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mLastPressBackTime < INTERVAL_OF_TWO_CLICK_TO_QUIT) {
            finish();
        } else {
            ToastHelper.showToast(this, "再次按下返回键将退出应用！");
            mLastPressBackTime = System.currentTimeMillis();
        }
    }



}
