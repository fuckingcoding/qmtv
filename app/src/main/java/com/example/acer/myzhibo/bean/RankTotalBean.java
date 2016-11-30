package com.example.acer.myzhibo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class RankTotalBean {

    /**
     * send_uid : 3740965
     * score : 124999
     * send_nick : SS爽全
     * rank : 20
     * avatar : http://image.quanmin.tv/avatar/5ac3ebb964786c605bc5d5eb0af4521b?imageView2/2/w/300/
     * icon : /static/images/lv/20.png?v=2
     * icon_url : http://www.quanmin.tv/static/images/lv/20.png?v=2
     */

    @SerializedName("send_uid")
    private int sendUid;
    @SerializedName("score")
    private int score;
    @SerializedName("send_nick")
    private String sendNick;
    @SerializedName("rank")
    private String rank;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("icon")
    private String icon;
    @SerializedName("icon_url")
    private String iconUrl;

    public int getSendUid() {
        return sendUid;
    }

    public void setSendUid(int sendUid) {
        this.sendUid = sendUid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSendNick() {
        return sendNick;
    }

    public void setSendNick(String sendNick) {
        this.sendNick = sendNick;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
