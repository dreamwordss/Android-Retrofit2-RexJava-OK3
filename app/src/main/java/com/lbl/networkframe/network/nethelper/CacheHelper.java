package com.lbl.networkframe.network.nethelper;


import com.lbl.networkframe.MyApplication;

import java.io.File;

import okhttp3.Cache;

/**
 * 缓存帮助类
 * author：libilang
 * time: 17/10/31 20:00
 * 邮箱：libi_lang@163.com
 */


public class CacheHelper {
    private static CacheHelper helper;
    private Cache mCache;
    private static File cacheFile; //设置缓存目录
    private static long maxSize = 15 * 1024 * 1024;//设置缓存大小

    private CacheHelper() {
        if (cacheFile == null) {
            cacheFile = new File(MyApplication.getContext().getCacheDir().getAbsolutePath(), "mycache");
            if (!cacheFile.exists()) {
                cacheFile.mkdir();
            }
        }
    }

    public static CacheHelper getInstance() {
        if (helper == null) {
            synchronized (CacheHelper.class) {
                if (helper == null) {
                    helper = new CacheHelper();
                }
            }
        }
        return helper;
    }

    //返回缓存对象
    public Cache getCache() {
        if (mCache == null)
            mCache = new Cache(cacheFile, maxSize);
        return mCache;
    }
}
