package com.lbl.networkframe.network;


//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;

import android.net.ParseException;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import com.lbl.networkframe.network.bean.basebean.ResultException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.adapter.rxjava2.HttpException;

/**
 * author：libilang
 * time: 17/10/31 20:00
 * 邮箱：libi_lang@163.com
 * 网络请求工具类    本来是想嵌套上下载的功能但是考虑后觉得下载没有比较放在这里
 */

public class NetWorkUtil {
    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    //Post方式
    public static <T> void requestPost(Observable observable, final OnResultListener resultListener) {

        setSubscriber(observable, new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T t) {
                if (resultListener != null) {//找一些接口试一下就好了
                    resultListener.onSuccess(t);
                }
//                if (t instanceof BaseResponseBean) {
//                    //后台可配置code码 根据不同的code可以做相应的操作 比如后台强制抛出错误等等 这里我注释掉，给大家个样板而已
//                    BaseResponseBean success = (BaseResponseBean) t;
//                    if (success.code == 0) {
//                        if (resultListener != null) {
//                            resultListener.onSuccess(success.data);
//                        }
//                    } else {
//                        if (resultListener != null) {
//                            resultListener.onError(success.errormessage);
//                        }
//                    }
//                } else {
//                    if (resultListener != null) {
//                        resultListener.onError("数据异常了");
//                    }
//                }
            }

            @Override
            public void onError(Throwable error) {
                if (error != null && resultListener != null) {
                    resultListener.onError(error.getMessage());
                } else if (resultListener != null) {
                    resultListener.onError("兄弟 网络不给力啊");
                    return;
                }
                String e = error.getMessage();
                if (error instanceof HttpException) {//HTTP错误
                    HttpException httpException = (HttpException) error;
                    switch (httpException.code()) {
                        case UNAUTHORIZED:
                        case FORBIDDEN:
                        case NOT_FOUND:
                        case REQUEST_TIMEOUT:
                        case GATEWAY_TIMEOUT:
                        case INTERNAL_SERVER_ERROR:
                        case BAD_GATEWAY:
                        case SERVICE_UNAVAILABLE:
                        default:
                            //Toast.makeText(App.getInstance(), "网络异常", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else if (error instanceof SocketTimeoutException) {
                } else if (error instanceof JsonParseException || error instanceof JSONException || error instanceof ParseException) {
                } else if (error instanceof ResultException) {//服务器返回的错误
                } else if (error instanceof ConnectException) {
                } else {//未知错误
                }
                resultListener.onError("兄弟 网络不给力啊");
            }

            @Override
            public void onComplete() {
//                Logger.d("request", "读取完成");
            }
        });

    }

    //Get方式
    public static void requestGet(Observable observable, final OnResultListener resultListener) {
        requestPost(observable, resultListener);
    }

    //订阅事件
    public static <T> void setSubscriber(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    //网络访问回调接口
    public interface OnResultListener<T> {

        void onSuccess(T t);

        void onError(String msg);
    }

}
