package com.lbl.networkframe.network.nethelper;

import android.os.Build;

import com.lbl.networkframe.network.netservice.API;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * author：libilang
 * time: 17/10/31 20:00
 * 邮箱：libi_lang@163.com
 * Ok3帮助类
 */

public class OkHttpClientHelper {

    private final Cache cache;               //缓存
    private OkHttpClient mClient;            //cleant对象
    private final static long TIMEOUT = 60;  //超时时间 包括链接 设置读写 设置写的超时时间

    private OkHttpClientHelper() {
        cache = CacheHelper.getInstance().getCache();
    }

    private static OkHttpClientHelper clientHelper;

    public static OkHttpClientHelper getInstance() {
        if (clientHelper == null) {
            synchronized (OkHttpClientHelper.class) {
                if (clientHelper == null) {
                    clientHelper = new OkHttpClientHelper();
                }
            }
        }
        return clientHelper;
    }

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            //打印retrofit日志
            if (API.mode == API.Mode.debug || API.mode == API.Mode.prerelease) {
//                Logger.d("bilang", "Log ======================= ");
                Logger.json(message);
            }
        }
    });

    //创建OKHttpClicent对象 配置header头信息等
    public OkHttpClient getOkHttpClient() {
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mClient == null) {
            mClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .cache(cache)      //设置缓存
                    .addInterceptor(loggingInterceptor)     //配置log信息
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()//配置添加header信息
                                    .newBuilder()
                                    .addHeader("source-terminal", "Android")  //操作系统名称（ios,android）//设备型号
                                    .addHeader("device-model", Build.MODEL)         //设备型号
                                    .addHeader("os-version", Build.VERSION.RELEASE) //操作系统版本号
                                    //添加cookie 这里需要全局观察下用户信息--比如cookie可以是uid也可以是usertoken等
                                    .addHeader("Cookie", "add cookies here")  //这里可以添加cookie 也可以在设计的webview中设置cookie的存储
                                    .build();
                            if (API.mode == API.Mode.debug || API.mode == API.Mode.prerelease) {
                                Logger.e("header=>source-terminal", "Android--" + request.url());
                                Logger.e("header=>device-model", Build.MODEL);
                                Logger.e("header=>os-version", Build.VERSION.RELEASE);
                                Logger.e("header=>Cookie", "");
                            }
                            return chain.proceed(request);
                        }
                    })
                    .build();
        }
        return mClient;
    }

}
