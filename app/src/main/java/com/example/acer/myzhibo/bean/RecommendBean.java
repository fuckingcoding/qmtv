package com.example.acer.myzhibo.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by acer on 2016/11/22.
 */

public class RecommendBean {

    /**
     * id : 1
     * name : 英雄联盟
     * slug : lol
     * first_letter : L
     * status : 0
     * prompt : 1
     * image : http://image.quanmin.tv/4518e1f7c347c3e78fc5fd9bba6cb6b2png
     * thumb : http://image.quanmin.tv/ca3d8b85f3b19ef7171f4435ce03ebcapng
     * priority : 1
     * screen : 0
     */

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("slug")
    private String slug;
    @SerializedName("first_letter")
    private String firstLetter;
    @SerializedName("status")
    private int status;
    @SerializedName("prompt")
    private int prompt;
    @SerializedName("image")
    private String image;
    @SerializedName("thumb")
    private String thumb;
    @SerializedName("priority")
    private int priority;
    @SerializedName("screen")
    private int screen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrompt() {
        return prompt;
    }

    public void setPrompt(int prompt) {
        this.prompt = prompt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }
}
