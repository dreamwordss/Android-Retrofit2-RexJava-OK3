package com.lbl.networkframe.network.nethelper.gsoncoverterentry;

import com.google.gson.Gson;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author：libilang
 * time: 17/11/9 16:08
 * 邮箱：libi_lang@163.com
 */

public class JsonConverterEncryptionFactory extends Converter.Factory {
    private final Gson gson;
    public static JsonConverterEncryptionFactory create() {
        return create(new Gson());
    }


    public static JsonConverterEncryptionFactory create(Gson gson) {
        return new JsonConverterEncryptionFactory(gson);
    }


    private JsonConverterEncryptionFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type,
                                                            Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new JsonResponseBodyConverter(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return new JsonRequestBodyConverter<>(gson);
    }
}
