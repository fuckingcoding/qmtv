package com.example.acer.myzhibo.ui.fragment.My.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.acer.myzhibo.R;
import com.example.acer.myzhibo.database.PreUtils;
import com.example.acer.myzhibo.ui.MainActivity;
import com.example.acer.myzhibo.utils.BitmapCircleTransformation;
import com.liji.takephoto.TakePhoto;

public class SheZhiActivity extends AppCompatActivity {
    private Context mContext=this;
    private Button mButton,mOkButton;
    private RelativeLayout mRelativeLayout;
    private ImageView imageView;
    private  TakePhoto takePhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 PreUtils.writeBoolean(mContext,"login",false);

               // PreUtils.getSharedPreferences(mContext).edit().clear().commit();

                 Intent intent=new Intent(mContext, MainActivity.class);
                 startActivity(intent);
            }
        });

    }

    private void initView() {
        mButton=(Button)findViewById(R.id.tuichu_Shezhi);
        mRelativeLayout=(RelativeLayout)findViewById(R.id.relative_touxiang);
        imageView=(ImageView)findViewById(R.id.imageview_touxiang_Shezhi);

        mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhoto = new TakePhoto(SheZhiActivity.this);
                takePhoto.setOnPictureSelected(new TakePhoto.onPictureSelected() {
                    @Override
                    public void select(String path) {
                        Glide.with(SheZhiActivity.this).load("file://" + path).transform(new BitmapCircleTransformation(mContext)).into(imageView);
                    }
                });
                takePhoto.show();

            }


        });
    }

}
