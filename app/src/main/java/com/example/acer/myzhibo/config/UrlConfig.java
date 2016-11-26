package com.example.acer.myzhibo.config;

/**
 * Created by acer on 2016/11/15.
 */

public class UrlConfig {
    public static  final  String BASE_URL ="http://www.quanmin.tv/";
    public static final String COLUMN_URL="http://www.quanmin.tv/json/categories/list.json?11212119&v=2.2.4&os=1&ver=4";
    public static final  String PATH1= "json/categories/";
    public  static  final  String PATH2 ="/list.json?11212123&v=2.2.4&os=1&ver=4";
    public  static  final String  MOREQMBEANSTRING ="json/categories/%s/list_%d.json?11212128&v=2.2.4&os=1&ver=4";
    public  static final String TOTALURL ="http://www.quanmin.tv/json/play/list.json?11212157&v=2.2.4&os=1&ver=4";
    public static  final String [] URLSTRING = new String[] {
            "lol","beauty","heartstone","huwai","overwatch","love",
            "wangzhe","qqfeiche", "yys","tvgame", "cfpc","mobilegame",
            "fs","au", "blizzard","erciyuan","dota2","zhuangjiafengbao",
            "dnf","qiuqiu","webgame","chuanqi","war3","fifa","nba2k"
    };
    public  static  final  String []  ColumnName = new String[]{

            "英雄联盟","全民星秀","炉石传说","全民户外","守望先锋","颜值控","王者荣耀","QQ飞车",



            "阴阳师","单机主机","穿越火线","手游专区","街篮专区","劲舞团",
            "暴雪经典","二次元区","DOTA2","装甲风暴","DNF","球球大作战","网络游戏",
            "传奇专区","魔兽争霸3","FIFA","NBA2K"
    };
    public  static  final  String []  OtherName = new String[]{
            "全民户外","王者荣耀","QQ飞车",
            "阴阳师","单机主机","穿越火线","手游专区","街篮专区","劲舞团",
            "暴雪经典","DOTA2","装甲风暴","DNF","球球大作战","网络游戏",
            "传奇专区","魔兽争霸3","FIFA","NBA2K"
    };
    public static final String Other="全民户外,王者荣耀,QQ飞车,阴阳师,单机主机,穿越火线,手游专区,街蓝专区,劲舞团,暴雪经典,DOTA2,装甲风暴,DNF,球球大作战,网络游戏,传奇专区,魔兽争霸3,FIFA,NBA2K";
    public  static  final  String []  DefultName = new String[]{
            "颜值控","英雄联盟","全民星秀","炉石传说","守望先锋","二次元区"
    };
    public static final String Defult="颜值控,英雄联盟,全民星秀,炉石传说,守望先锋,二次元区";

  public static final String SEARCH_URL ="site/search?p=1&refer=search&device=AknYJ6H1svk5AhUoGvUAuBZRAkPW56PHz_AMlFX7FGHN&ch=baiduzhushou&uid=-1&rid=-1&rcat=-\n" +
          "1&net=0&screen=2&sw=720&sh=1280";


    //推荐页
    public static final String RECOMMEND="http://www.quanmin.tv/json/app/index/recommend/list-android.json?11232149&v=2.2.4&os=1&ver=4";

    //推荐页广告轮播
    public static final String ADURL="http://www.quanmin.tv/json/page/app-data/info.json?v=2.2.4&os=1&ver=4";
}
