package com.example.acer.myzhibo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.Constant;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class LandscapeActivity extends AppCompatActivity {
    private Context mContext=this;
    private VideoView videoView;
    private MediaController mMediaController;
    private String str;
    private String url;
    private ImageView iv_back,iv_share,iv_heart,iv_pause;
    private ImageView iv_refresh,iv_gift,iv_commenting;
    private ImageView iv_hot,iv_send;
    private TextView tv_title,tv_qxd,tv_gift;
    private EditText editText;
    private RelativeLayout rl_top,rl_bottom;
    private boolean touch=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);
        Intent intent = getIntent();
        str = intent.getStringExtra("1");
        url=String.format(Constant.PLAYERURL, str);
        initView();
        initData();
    }

    private void initData() {
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(touch){
                    rl_top.setVisibility(View.VISIBLE);
                    rl_bottom.setVisibility(View.VISIBLE);
                    touch=false;
                }else{
                    rl_top.setVisibility(View.GONE);
                    rl_bottom.setVisibility(View.GONE);
                    touch=true;
                }

                return false;
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandscapeActivity.this,PlayActivity.class);
                intent.putExtra(Constant.UID,str);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    private void initView() {
        rl_top = (RelativeLayout) findViewById(R.id.rl_landscape_top);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_landscape_bottom);
        iv_back = (ImageView) findViewById(R.id.iv_landscape_back);
        videoView = (VideoView) findViewById(R.id.vv_landscape);
        videoView.setVideoPath(url);
        mMediaController = new MediaController(this);
        mMediaController.show(5000);
        mMediaController.setVisibility(View.INVISIBLE);
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_MEDIUM);
        videoView.requestFocus();
        videoView.start();
    }
}
