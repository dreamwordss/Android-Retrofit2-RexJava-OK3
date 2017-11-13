package com.lbl.networkframe.network.download;

import java.io.Closeable;
import java.io.IOException;

/**
 * author：libilang
 * time: 17/11/9 2:33
 * 邮箱：libi_lang@163.com
 * 关闭下载流
 */
public class IOUtil {
    public static void closeAll(Closeable... closeables){
        if(closeables == null){
            return;
        }
        for (Closeable closeable : closeables) {
            if(closeable!=null){
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
