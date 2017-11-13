package com.lbl.networkframe.network.download;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 */
/**
 *
 */

/**
 * author：libilang
 * time: 17/11/9 3:50
 * 邮箱：libi_lang@163.com
 * 多线程断点续传 下载帮助类 采用rexjava+OK3配合异步线程调度 可以针对单个线程管理 好处是解耦后在任意地方调用比较方便
 */
public class DownloadUtil {
    //JUC原子类
    private static final AtomicReference<DownloadUtil> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> downCalls;//用来存放各个下载的请求
    private OkHttpClient mClient;           //OKHttpClient;

    public String parentFile = "";

    //单例类
    public static DownloadUtil getInstance() {
        for (; ; ) {
            DownloadUtil current = INSTANCE.get();
            if (current != null) {
                return current;
            }
            current = new DownloadUtil();
            if (INSTANCE.compareAndSet(null, current)) {
                return current;
            }
        }
    }

    private DownloadUtil() {
        downCalls = new HashMap<>();
        mClient = new OkHttpClient.Builder().build();
    }

    /**
     * 开始下载
     *
     * @param url              下载请求的网址
     * @param downLoadObserver 用来回调的接口
     */
    public void download(String url, String parentFiles, DownLoadObserver downLoadObserver) {
        parentFile = parentFiles;
        //创建下载
        Observable.just(url)
                .filter(s -> !downCalls.containsKey(s))//call的map已经有了,就证明正在下载,则这次不下载
                .flatMap(s -> Observable.just(createDownInfo(s)))
                .map(this::getRealFileName)//检测本地文件夹,生成新的文件名
                .flatMap(downloadInfo -> Observable.create(new DownloadSubscribe(downloadInfo)))//下载
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调
                .subscribeOn(Schedulers.io())//在子线程执行
                .subscribe(downLoadObserver);//添加下载观察者

        Log.e("123", "download finished");
    }

    /**
     * 下载授权lic文件
     *
     * @param url
     * @param parentFiles
     * @param loadlic
     * @param downLoadObserver
     */
    public void downloadLic(String url, String parentFiles, boolean loadlic, DownLoadObserver downLoadObserver) {
        parentFile = parentFiles;

        Observable.just(url)
                .filter(s -> !downCalls.containsKey(s))//call的map已经有了,就证明正在下载,则这次不下载
                .flatMap(s -> Observable.just(createDownInfolic(s, loadlic)))
                .map(this::getRealFileName)//检测本地文件夹,生成新的文件名
                .flatMap(downloadInfo -> Observable.create(new DownloadSubscribe(downloadInfo)))//下载
                .observeOn(AndroidSchedulers.mainThread())//在主线程回调
                .subscribeOn(Schedulers.io())//在子线程执行
                .subscribe(downLoadObserver);//添加观察者

        Log.e("123", "download finished");
    }

    public void cancel(String url) {
        Call call = downCalls.get(url);
        if (call != null) {
            call.cancel();//取消
        }
        downCalls.remove(url);
    }

    /**
     * 创建DownInfo 请求地址
     */
    private DownloadInfo createDownInfo(String url) {
        DownloadInfo downloadInfo = new DownloadInfo(url);
        long contentLength = getContentLength(url);//获得文件大小
        Log.e("123", "contentLength==" + contentLength);
        downloadInfo.setTotal(contentLength);

        String fileName = url.substring(url.lastIndexOf("/"));
        if (fileName.contains("music")) {
            fileName = fileName.replace("music", "mp3");
        }
        downloadInfo.setFileName(fileName);
        //这是我项目里面用的 先注释掉
//        String version = MeidaSPUtil.getString(BaseApplication.getInstance(), url, "1");
//        Log.e("bilang", "verson=" + version);
//        if (!version.contains("#")) {
//            MeidaSPUtil.putString(BaseApplication.getInstance(), url, version + "#" + contentLength);
//        }

        return downloadInfo;
    }

    private DownloadInfo createDownInfolic(String url, boolean loadlic) {
        DownloadInfo downloadInfo = new DownloadInfo(url);
        long contentLength = getContentLength(url);//获得文件大小
        Log.e("bilang", "contentLength==" + contentLength);
        downloadInfo.setTotal(contentLength);
        String fileName = url.substring(url.lastIndexOf("/"));
        downloadInfo.setFileName(fileName);
        return downloadInfo;
    }

    /**
     * 生成新的文件名
     *
     * @param downloadInfo
     * @return
     */

    private DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        String fileName = downloadInfo.getFileName();
        Log.e("bilang", "下载文件地址----" + fileName);
        long downloadLength = 0, contentLength = downloadInfo.getTotal();
        File file = new File(parentFile, fileName);
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            downloadLength = file.length();
        }
        //设置改变过的文件名/大小
        downloadInfo.setProgress(downloadLength);
        downloadInfo.setFileName(file.getName());
        return downloadInfo;
    }

    /**
     * 下载文件观察
     */
    private class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
        private DownloadInfo downloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        @Override
        public void subscribe(ObservableEmitter<DownloadInfo> e) throws Exception {
            String url = downloadInfo.getUrl();
            long downloadLength = downloadInfo.getProgress();//已经下载好的长度
            long contentLength = downloadInfo.getTotal();//文件的总长度
            //初始进度信息
            e.onNext(downloadInfo);

            Request request = new Request.Builder()
                    //确定下载的范围,添加此头,则服务器就可以跳过已经下载好的部分
                    .addHeader("RANGE", "bytes=" + downloadLength + "-" + contentLength)
                    .url(url)
                    .build();
            Call call = mClient.newCall(request);
            downCalls.put(url, call);//把这个添加到call里,方便取消
            Response response = call.execute();


            File file = new File(parentFile, downloadInfo.getFileName());
            Log.e("bilang", "文件最终下载地址=" + file.getAbsolutePath());
            InputStream is = null;
            FileOutputStream fileOutputStream = null;
            try {
                is = response.body().byteStream();
                fileOutputStream = new FileOutputStream(file, true);
                byte[] buffer = new byte[2048];//缓冲数组2kB
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    downloadLength += len;
                    downloadInfo.setProgress(downloadLength);
                    e.onNext(downloadInfo);
                }
                fileOutputStream.flush();
                downCalls.remove(url);
            } finally {
                //关闭IO流
                IOUtil.closeAll(is, fileOutputStream);

            }
            e.onComplete();//完成
        }
    }

    /**
     * 获取下载长度
     */
    private long getContentLength(String downloadUrl) {
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        try {
            Response response = mClient.newCall(request).execute();
            if (response != null && response.isSuccessful()) {
                long contentLength = response.body().contentLength();
                response.close();
                return contentLength == 0 ? DownloadInfo.TOTAL_ERROR : contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return DownloadInfo.TOTAL_ERROR;
    }

}
