package com.lbl.networkframe.network.download;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * author：libilang
 * time: 17/11/9 2:59
 * 邮箱：libi_lang@163.com
 * 下载观察者 可以用于取消注册的监听者
 */
public  abstract class DownLoadObserver implements Observer<DownloadInfo> {
    protected Disposable d;
    protected DownloadInfo downloadInfo;
    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
    }

    @Override
    public void onNext(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }


}
