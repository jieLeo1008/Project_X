package com.jieleo.projecta.website;

/**
 * Created by yuyongjie on 17/2/13.
 */


public class WebsiteInter {

    //公共接口部分
    public  static String BASIC_URL="http://api.liwushuo.com/v2/";
    //频道列表的接口
    public    static    String CHANNELS_URL=BASIC_URL+"channels/preset?gender=1&generation=3";

    //精选轮播图接口
    public static String BANNER="http://api.liwushuo.com/v2/banners?channel=IOS";

    public static final String MODULE = BASIC_URL + "secondary_banners?gender=1&generation=3";

    public static String part1="channels/";
    protected static String part2="/items_v2?gender=1&generation=3&limit=20&offset=0";
    public static final String LIST = "ranks_v3/ranks/";
    public static final String LIST_OTHER = "?limit=20&offset=0";

    public static final String GIFT="http://api.liwushuo.com/v2/ranks_v2/ranks?";


    public static final String MALLDOWN="http://api.liwushuo.com/v2/shop/items?limit=20&offset=0";

    public static final String MallUP = "http://api.liwushuo.com/v2/shopitem_collections";


    // 分类页
    // 攻略接口
    public static final String STRATEGY_UP_TITLE = BASIC_URL + "columns?limit=20&offset=0";
    public static final String STRATEGY = BASIC_URL + "channel_groups/all?";
    //单品页接口
    public static final String SINGLE = "http://api.liwushuo.com/v2/item_categories/tree";

    //单品页二级页面
    public static final String SINGE_SECOND_UP = "http://api.liwushuo.com/v2/item_subcategories/";
    public static final String SINGE_SECOND_DOWN = "/items?limit=20&offset=0";
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
