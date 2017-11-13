package com.lbl.networkframe.network.nethelper.gsoncoverterentry;

import android.util.Base64;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * author：libilang
 * time: 17/11/9 16:11
 * 邮箱：libi_lang@163.com
 */

final class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;

    JsonRequestBodyConverter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String requestJson = value.toString();
        byte[] decode = Base64.decode(requestJson, Base64.DEFAULT);
        return RequestBody.create(MEDIA_TYPE, decode.toString());
    }
}