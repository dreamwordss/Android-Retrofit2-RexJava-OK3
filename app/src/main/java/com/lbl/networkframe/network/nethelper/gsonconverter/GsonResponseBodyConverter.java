package com.lbl.networkframe.network.nethelper.gsonconverter;

import android.util.Log;

import com.google.gson.Gson;
import com.lbl.networkframe.network.bean.basebean.ErrResponse;
import com.lbl.networkframe.network.bean.basebean.ResultException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * author：libilang
 * time: 17/10/31 20:00
 * 邮箱：libi_lang@163.com
 */
public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
//        Log.d("bilang", "数据返回响应 response--->  " + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getInt("code") == 0) {//访问成功
                //code==0表示成功返回
                String data = jsonObject.getString("data");
                return gson.fromJson(data, type);
            } else {
                //ErrResponse 将msg解析为异常消息文本
                ErrResponse errResponse = gson.fromJson(response, ErrResponse.class);
                throw new ResultException(0, errResponse.msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("bilang", e.getMessage());
            return null;
        }
    }
}
