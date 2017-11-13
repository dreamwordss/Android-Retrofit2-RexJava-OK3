package com.lbl.networkframe.network.bean.basebean;

/**
 * author：libilang
 * time: 17/11/8 20:00
 * 邮箱：libi_lang@163.com
 * 用于捕获服务器约定的错误类型
 */
public class ResultException extends RuntimeException {
    private int errCode = 0;
    public String errmessgage;

    public ResultException(int errCode, String msg) {
        this.errCode = errCode;
        this.errmessgage = msg;
    }

}
