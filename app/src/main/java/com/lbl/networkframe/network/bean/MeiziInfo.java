package com.lbl.networkframe.network.bean;

import java.util.List;

/**
 * author：libilang
 * time: 17/11/8 6:10
 * 邮箱：libi_lang@163.com
 * 样例
 */

public class MeiziInfo {


    public List<MeiziBean> results;

    public static class MeiziBean {

        public String objectId;
        public String url;
        public String type;
        public String desc;
        public String who;
        public boolean used;
        public boolean hasFadedIn = false;
        public int imageWidth;
        public int imageHeight;
    }

}
