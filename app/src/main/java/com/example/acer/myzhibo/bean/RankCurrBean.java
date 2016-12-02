package com.example.acer.myzhibo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class RankCurrBean {

    /**
     * send_uid : 6647644
     * score : 290
     * send_nick : 只会曙光女神
     * avatar : http://image.quanmin.tv/avatar/d80d44ba31a3c9cf48c8b69677777a24jpg?imageView2/2/w/300/
     * rank : 9
     * icon : /static/images/lv/9.png?v=2
     */

    @SerializedName("send_uid")
    private int sendUid;
    @SerializedName("score")
    private int score;
    @SerializedName("send_nick")
    private String sendNick;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("rank")
    private String rank;
    @SerializedName("icon")
    private String icon;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
