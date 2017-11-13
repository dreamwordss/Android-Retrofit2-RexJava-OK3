package com.lbl.networkframe.network.bean;

import java.util.List;

/**
 * author：libilang
 * time: 17/11/1 10:19
 * 邮箱：libi_lang@163.com
 * 样例---更新数据bean对象
 */
public class CheckUpdateBean {
    /**
     * 类型这样的api  url : https://o5xqzr3t3.qnssl.com/android/20160425/shark1_q1_v39_20160424_xhc_23.apk
     */
    public String url;
    public boolean log;
    public int size = 1024 * 512;
    public List<String> address;
    public String hash;

}
