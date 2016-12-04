package com.example.acer.myzhibo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.example.acer.myzhibo.utils.NetworkHelper;

/**
 * Created by acer on 2016/11/15.
 */

public class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        boolean networkConnected = NetworkHelper.isNetworkConnected(this);



    }
}
