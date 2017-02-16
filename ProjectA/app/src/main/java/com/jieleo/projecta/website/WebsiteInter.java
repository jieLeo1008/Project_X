package com.jieleo.projecta.website;

/**
 * Created by yuyongjie on 17/2/13.
 */


public class WebsiteInter {

    //公共接口部分
    public  static String BASIC_URL="http://api.liwushuo.com/v2/";
    //频道列表的接口
    public    static    String CHANNELS_URL=BASIC_URL+"channels/preset?gender=2&generation=1";


    public static String BANNER="http://api.liwushuo.com/v2/banners?channel=IOS";

    public static final String MODULE = BASIC_URL + "secondary_banners?gender=2&generation=1";

    public static String part1="channels/";
    protected static String part2="/items_v2?gender=2&generation=1&limit=20&offset=0";
    public static final String LIST = "ranks_v3/ranks/";
    public static final String LIST_OTHER = "?limit=20&offset=0";

    public static final String GIFT="http://api.liwushuo.com/v2/ranks_v2/ranks?";


    public static final String MALLDOWN="http://api.liwushuo.com/v2/shop/items?limit=20&offset=0";

    public static final String MallUP = "http://api.liwushuo.com/v2/shopitem_collections";
    public  static  String getHomePageDetailsUrl(int id){
        String Id=id+"";
        String url=BASIC_URL+part1+Id+part2;
        return url;
    }

    public static String getGiftDetailsUrl(int id){
        String Id=id+"";
        String url=BASIC_URL+LIST+Id+LIST_OTHER;
        return url;
    }



}
