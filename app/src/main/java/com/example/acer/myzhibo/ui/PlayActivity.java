package com.example.acer.myzhibo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.config.Constant;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class PlayActivity extends AppCompatActivity implements Runnable{
    private Context mContext=this;
    private VideoView videoView;
    private MediaController mMediaController;
    private String playurl ;
//    private String path = "http://flv.quanmin.tv/live/3043295.flv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("1");
        playurl = String.format(Constant.PLAYERURL, stringExtra);
        Log.e("TAG", "initDataWSND: "+playurl );
    }

    private void initView() {

        videoView = (VideoView) findViewById(R.id.videoview_play);
        videoView.setVideoPath(playurl);
        mMediaController = new MediaController(this);
        mMediaController.show(5000);
        videoView.setMediaController(mMediaController);
        videoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_MEDIUM);
        videoView.requestFocus();
        videoView.start();

    }

    @Override
    public void run() {

    }
}
