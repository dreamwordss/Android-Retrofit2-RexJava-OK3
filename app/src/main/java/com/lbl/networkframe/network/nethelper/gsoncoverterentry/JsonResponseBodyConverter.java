package com.lbl.networkframe.network.nethelper.gsoncoverterentry;

import android.util.Base64;

import com.google.gson.Gson;
import com.lbl.networkframe.network.bean.basebean.ErrResponse;
import com.lbl.networkframe.network.bean.basebean.ResultException;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author：libilang
 * time: 17/11/9 16:10
 * 邮箱：libi_lang@163.com
 */

final class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    JsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        try {
            // 设计响应体解密算法，下面用了Base64解码
            String value = Base64.decode(responseBody.bytes(), Base64.DEFAULT).toString();
            JSONObject jsonObject = new JSONObject(value);
            if (jsonObject.getInt("code") == 0) {//访问成功
                //code==0表示成功返回
                String data = jsonObject.getString("data");
                return gson.fromJson(data, type);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                ErrResponse errResponse = gson.fromJson(value, ErrResponse.class);
                throw new ResultException(0, errResponse.msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
