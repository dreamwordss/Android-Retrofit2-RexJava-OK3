package com.lbl.networkframe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 全局的静态常量
 * author：libilang
 * time: 16/7/29 15:13
 * 邮箱：libi_lang@163.com
 */
public final class ConstantS {
    /**
     * 网络检测 数据列表
     */
    public static List<String> CHECK_NETLIST = new ArrayList<String>();
    /**
     * 本地LOG 文件大小
     */
    public static int LOGSIZE = 1024 * 512;
    /**
     * 微信支付标记支付
     */
    public static boolean WECHAT_ISPAY = false;
    /**
     * 上传数据量
     */
    public static int UPLOAD_DATENUM = 1;
    /**
     * 录音地址 和 时间
     */
    public static ArrayList<Map<String, Map<String, String>>> luPathList = new ArrayList<>();

    public static final String LAST_ACTIVITY = "last_activity";

    public static final String IS_INSIDE_MALL = "isInsideMall";

    public static final String LAST_ACCOUNT = "last_login_account";//记录上次登录的账户
    /**
     * 视频上传时长限制
     */
    public static int UPLOAD_VIDEO_TIMEMAX = 120;

    /**
     * 连续崩溃次数
     */
    public static String CRASH_SIZE = "crash_size";

    /**
     * 图片最大数量
     */
    public static int MAX_PHOTO_SIZE = 0;

    public static String IMAGE_HOST = "https://i.xiaohongchun.com/";

    /**
     * 访问次数加+1
     */
    public static int USER_CONNECT_NETSZIE = 0;


    public static final String DOWNLOAD_HASH = "download_hash";
    public static final String MAIN_ACIVITY_TAB_INDEX = "main_acivity_tab_index";
    public static final String TRACK_INFO = "track_info";//埋点

    public static int SCREENWINDOWHIGNT = 0;


    public static final int add_tag = 1233;

    public static final String GROUP_BUY_WAY = "GROUP_BUY_WAY";//开团|参团
    public static final String FREE_TICKER_ID = "FREE_TICKER_ID";//团长免单id
    public static final int GROUP_BUY_OPEN = 1;//开团
    public static final int GROUP_BUY_JOIN = 2;//参团
    public static final String IS_NEW_GROUP_BUY = "IS_NEW_GROUP_BUY";
    public static final String GROUP_BUY_RLUE = "https://static.xiaohongchun.com/groupbuy/rule?xhc_share=0";
    public static final String GROUP_COMMANDERFREE_RLUE = "https://static.xiaohongchun.com/groupbuy/rule/free?xhc_share=0";


    public static String CHANNELNAME = "";
    public static boolean ISMAXScreenRatio = false;//表示全屏的手机


}
