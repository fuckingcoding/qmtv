package com.example.acer.myzhibo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by apple on 16/10/24.
 * Eemil:635727195@qq.com
 */
public class Users extends BmobObject {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
