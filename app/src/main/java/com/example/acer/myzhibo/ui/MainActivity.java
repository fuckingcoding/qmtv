package com.example.acer.myzhibo.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.adapter.Mainadapter;
import com.example.acer.myzhibo.ui.fragment.ColumnFragment;
import com.example.acer.myzhibo.ui.fragment.LiveFragment;
import com.example.acer.myzhibo.ui.fragment.MyFragment;
import com.example.acer.myzhibo.ui.fragment.RecommendFragment;
import com.example.acer.myzhibo.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
    }
    //初始化radiobutton，viewpager
    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mViewPager = (NoScrollViewPager) findViewById(R.id.noscrollviewPager_main);
        mRadioGroup.setOnCheckedChangeListener(this);

        mViewPager.setOnPageChangeListener(this);
    }
    //初始化fragment
    private void initFragment() {
        RecommendFragment recommendFragment = new RecommendFragment();
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
}
