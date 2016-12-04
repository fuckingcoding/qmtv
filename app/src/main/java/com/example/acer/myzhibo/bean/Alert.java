package com.example.acer.myzhibo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Project Name:MyZhibo
 * Package Name:com.example.acer.myzhibo.bean
 * File    Name:Alert
 * Created by WYJ on 16/12/1.
 * Description :TODO
 */

public class Alert  extends BmobObject{
    private String name;
    private String avatar;
    private String title;
    private String nick;
    private String view;
    private String uid;
    private String thumb;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
