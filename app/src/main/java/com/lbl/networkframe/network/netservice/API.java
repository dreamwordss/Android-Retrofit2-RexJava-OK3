package com.lbl.networkframe.network.netservice;

/**
 * author：libilang
 * time: 17/11/1 10:27
 * 邮箱：libi_lang@163.com
 * 系统访问的地址--debug prerelease release
 */

public class API {
    public static String DOMAIN;
    public static String H5DOMAIN;
    public static String HTTP_PROTOCOL;

    //域名前缀枚举
    public enum HttpsMode {
        on,
        off
    }

    //开发环境枚举
    public enum Mode {
        debug,      // 测试环境
        prerelease, // 灰度测试用,按正式发包方式打包
        release     // 正式环境
    }

    public final static Mode mode = Mode.prerelease;//当前url环境
    public static HttpsMode httpsMode = HttpsMode.on;//url前缀分判

    static {
        //讲道理到现在这个时候还是用http前缀的说明技术没有跟上时代脚步
        if (httpsMode == HttpsMode.off) {
            HTTP_PROTOCOL = "https://";
        } else {
            HTTP_PROTOCOL = "http://";
        }
        if (mode == Mode.debug) {
            DOMAIN = HTTP_PROTOCOL + "gank.io";
            H5DOMAIN = HTTP_PROTOCOL + "test.bl.com";

        } else if (mode == Mode.prerelease) {
            DOMAIN = HTTP_PROTOCOL + "napi.xiaohongchun.com";
            H5DOMAIN = HTTP_PROTOCOL + "napi.xiaohongchun.com";

        } else if (mode == Mode.release) {
            DOMAIN = HTTP_PROTOCOL + "www.xiaohongchun.com";
            H5DOMAIN = HTTP_PROTOCOL + "www.xiaohongchun.com";
        }
    }

    // 服务器接口返回码值 具体情况定一个规则就可以
    public static final String RETCODE_NORAML = "200"; // 请求正常
    public static final String RETCODE_PARAM_ERROR = "502"; // 参数错误


    // 银行卡认证信息
    public static final String URL_BANKCARD_IDENTIFIED_INFO = DOMAIN + "/userauth/authinfo/bankcard";
    /**
     * apk更新 https://trunk.xiaohongchun.com/api2/index/get_aup
     */
    public static final String API_APP_UPDATE = DOMAIN + "/api2/index/get_aup";


}
