package com.example.acer.myzhibo.bean;

/**
 * Created by acer on 2016/11/26.
 */

public class TulingChatBean {

    private String image ;
    private String name ;
    private String content;
    private int type ;

    public TulingChatBean(String name, String content, int type, String image) {
        this.name = name;
        this.content = content;
        this.type = type;
        this.image = image;
    }

    public TulingChatBean(int type, String content, String name) {
        this.type = type;
        this.content = content;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TulingChatBean{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                '}';
    }
}
