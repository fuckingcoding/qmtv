package com.example.acer.myzhibo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Project Name:MyZhibo
 * Package Name:com.example.acer.myzhibo.bean
 * File    Name:AdBean
 * Created by WYJ on 16/12/1.
 * Description :TODO
 */

public class AdBean {

    /**
     * id : 616
     * title : 国服最强诺克 一区带水友灵车飘逸
     * thumb : http://image.quanmin.tv/7ddcf9f4cb54704d890650efd3b18209jpg
     * link :
     * create_at : 2016-04-29 12:41:37
     * status : 1
     * fk : 3610730
     * subtitle :
     * content :
     * ext : {"type":"play"}
     * slot_id : 113
     * priority : 1
     * link_object : {"recommend_image":"","announcement":"12点半到下午5点，2点半开始打SOLO","title":"国服最强诺克 一区带水友灵车飘逸！！！","create_at":"2016-12-01 12:37:28","intro":"直播中奖的兄弟 请加QQ群 206726556 联系木初老狗 管理员兑奖。 带上你全民TV名字截图~5点前不来领奖报废~新浪微博：全民TV木初老狗 房管请加140115989","video":"http://thumb.quanmin.tv/3610730.mp4?t=1480581000","screen":0,"love_cover":"","category_id":"1","video_quality":"234","like":"0","default_image":"","slug":"","weight":"1741640","status":"1","level":"0","avatar":"http://image.quanmin.tv/avatar/f58c3eba9190fd8b616ee63ee47fee33?imageView2/2/w/300/","uid":"3610730","play_at":"2016-12-01 12:37:28","view":"44568","category_slug":"lol","nick":"木初老狗QAQ","beauty_cover":"","app_shuffling_image":"","follow":"27225","start_time":"2016-12-01 12:37:28","category_name":"英雄联盟","thumb":"http://image.quanmin.tv/7ddcf9f4cb54704d890650efd3b18209jpg","grade":"","year_type":5,"hidden":false}
     */

    @SerializedName("app-focus")
    private List<focusBean> focus;

    public List<focusBean> getFocus() {
        return focus;
    }

    public void setFocus(List<focusBean> focus) {
        this.focus = focus;
    }

    public static class focusBean {
        private int id;
        private String title;
        private String thumb;
        private String link;
        private String create_at;
        private int status;
        private String fk;
        private String subtitle;
        private String content;
        /**
         * type : play
         */

        private ExtBean ext;
        private int slot_id;
        private int priority;
        /**
         * recommend_image :
         * announcement : 12点半到下午5点，2点半开始打SOLO
         * title : 国服最强诺克 一区带水友灵车飘逸！！！
         * create_at : 2016-12-01 12:37:28
         * intro : 直播中奖的兄弟 请加QQ群 206726556 联系木初老狗 管理员兑奖。 带上你全民TV名字截图~5点前不来领奖报废~新浪微博：全民TV木初老狗 房管请加140115989
         * video : http://thumb.quanmin.tv/3610730.mp4?t=1480581000
         * screen : 0
         * love_cover :
         * category_id : 1
         * video_quality : 234
         * like : 0
         * default_image :
         * slug :
         * weight : 1741640
         * status : 1
         * level : 0
         * avatar : http://image.quanmin.tv/avatar/f58c3eba9190fd8b616ee63ee47fee33?imageView2/2/w/300/
         * uid : 3610730
         * play_at : 2016-12-01 12:37:28
         * view : 44568
         * category_slug : lol
         * nick : 木初老狗QAQ
         * beauty_cover :
         * app_shuffling_image :
         * follow : 27225
         * start_time : 2016-12-01 12:37:28
         * category_name : 英雄联盟
         * thumb : http://image.quanmin.tv/7ddcf9f4cb54704d890650efd3b18209jpg
         * grade :
         * year_type : 5
         * hidden : false
         */

        private LinkObjectBean link_object;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getFk() {
            return fk;
        }

        public void setFk(String fk) {
            this.fk = fk;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        public int getSlot_id() {
            return slot_id;
        }

        public void setSlot_id(int slot_id) {
            this.slot_id = slot_id;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public LinkObjectBean getLink_object() {
            return link_object;
        }

        public void setLink_object(LinkObjectBean link_object) {
            this.link_object = link_object;
        }

        public static class ExtBean {
            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class LinkObjectBean {
            private String recommend_image;
            private String announcement;
            private String title;
            private String create_at;
            private String intro;
            private String video;
            private int screen;
            private String love_cover;
            private String category_id;
            private String video_quality;
            private String like;
            private String default_image;
            private String slug;
            private String weight;
            private String status;
            private String level;
            private String avatar;
            private String uid;
            private String play_at;
            private String view;
            private String category_slug;
            private String nick;
            private String beauty_cover;
            private String app_shuffling_image;
            private String follow;
            private String start_time;
            private String category_name;
            private String thumb;
            private String grade;
            private int year_type;
            private boolean hidden;

            public String getRecommend_image() {
                return recommend_image;
            }

            public void setRecommend_image(String recommend_image) {
                this.recommend_image = recommend_image;
            }

            public String getAnnouncement() {
                return announcement;
            }

            public void setAnnouncement(String announcement) {
                this.announcement = announcement;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreate_at() {
                return create_at;
            }

            public void setCreate_at(String create_at) {
                this.create_at = create_at;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public int getScreen() {
                return screen;
            }

            public void setScreen(int screen) {
                this.screen = screen;
            }

            public String getLove_cover() {
                return love_cover;
            }

            public void setLove_cover(String love_cover) {
                this.love_cover = love_cover;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getVideo_quality() {
                return video_quality;
            }

            public void setVideo_quality(String video_quality) {
                this.video_quality = video_quality;
            }

            public String getLike() {
                return like;
            }

            public void setLike(String like) {
                this.like = like;
            }

            public String getDefault_image() {
                return default_image;
            }

            public void setDefault_image(String default_image) {
                this.default_image = default_image;
            }

            public String getSlug() {
                return slug;
            }

            public void setSlug(String slug) {
                this.slug = slug;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getPlay_at() {
                return play_at;
            }

            public void setPlay_at(String play_at) {
                this.play_at = play_at;
            }

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }

            public String getCategory_slug() {
                return category_slug;
            }

            public void setCategory_slug(String category_slug) {
                this.category_slug = category_slug;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getBeauty_cover() {
                return beauty_cover;
            }

            public void setBeauty_cover(String beauty_cover) {
                this.beauty_cover = beauty_cover;
            }

            public String getApp_shuffling_image() {
                return app_shuffling_image;
            }

            public void setApp_shuffling_image(String app_shuffling_image) {
                this.app_shuffling_image = app_shuffling_image;
            }

            public String getFollow() {
                return follow;
            }

            public void setFollow(String follow) {
                this.follow = follow;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public int getYear_type() {
                return year_type;
            }

            public void setYear_type(int year_type) {
                this.year_type = year_type;
            }

            public boolean isHidden() {
                return hidden;
            }

            public void setHidden(boolean hidden) {
                this.hidden = hidden;
            }
        }
    }
}
