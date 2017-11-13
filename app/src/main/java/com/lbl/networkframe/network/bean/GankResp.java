package com.lbl.networkframe.network.bean;

import java.util.List;

/**
 * author：libilang
 * time: 17/11/8 6:14
 * 邮箱：libi_lang@163.com
 * 样例
 */
public class GankResp {
    public boolean error;
    public List<GankEntry> results;

    public class GankEntry {

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
