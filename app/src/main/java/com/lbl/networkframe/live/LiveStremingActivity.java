package com.lbl.networkframe.live;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lbl.networkframe.R;

import java.util.LinkedList;

import jp.co.cyberagent.android.gpuimage.GPUImageAddBlendFilter;
import me.lake.librestreaming.core.listener.RESConnectionListener;
import me.lake.librestreaming.filter.hardvideofilter.BaseHardVideoFilter;
import me.lake.librestreaming.filter.hardvideofilter.HardVideoGroupFilter;
import me.lake.librestreaming.ws.StreamAVOption;
import me.lake.librestreaming.ws.StreamLiveCameraView;
import me.lake.librestreaming.ws.filter.hardfilter.extra.GPUImageCompatibleFilter;

/**
 * author：libilang
 * time: 18/3/21 10:59
 * 邮箱：libi_lang@163.com
 * 直播端直播推流到服务器
 */

public class LiveStremingActivity extends LiveingBaseActivity {

    public StreamLiveCameraView mLiveCameraView;
    private StreamAVOption streamAVOption;
    private String rtmpUrl = "rtmp://ossrs.net/" + StatusBarUtils.getRandomAlphaString(3) + '/' + StatusBarUtils.getRandomAlphaDigitString(5);
    public LiveStremingCell stremingCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livestreming);
        initLiveConfig();
        Log.e("bilang",rtmpUrl);
        stremingCell = new LiveStremingCell(this, mLiveCameraView, rtmpUrl);
    }

    /**
     * 设置推流参数
     */
    public void initLiveConfig() {
        mLiveCameraView = findViewById(R.id.stream_previewView);

        //参数配置 start
        streamAVOption = new StreamAVOption();
        streamAVOption.streamUrl = rtmpUrl;
        //参数配置 end

        mLiveCameraView.init(LiveStremingActivity.this, streamAVOption);
        mLiveCameraView.addStreamStateListener(resConnectionListener);
        LinkedList<BaseHardVideoFilter> files = new LinkedList<>();
//        files.add(new GPUImageCompatibleFilter(new GPUImageBeautyFilter()));
        files.add(new GPUImageCompatibleFilter(new GPUImageAddBlendFilter()));
        mLiveCameraView.setHardVideoFilter(new HardVideoGroupFilter(files));
    }

    RESConnectionListener resConnectionListener = new RESConnectionListener() {
        @Override
        public void onOpenConnectionResult(int result) {
            //result 0成功  1 失败
            Toast.makeText(LiveStremingActivity.this, "打开推流连接 状态：" + result + " 推流地址：" + rtmpUrl, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWriteError(int errno) {
            Toast.makeText(LiveStremingActivity.this, "推流出错,请尝试重连", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCloseConnectionResult(int result) {
            //result 0成功  1 失败
            Toast.makeText(LiveStremingActivity.this, "关闭推流连接 状态：" + result, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //二次打开的时候去做最好
//        if (mLiveCameraView!=null)
//            mLiveCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (mLiveCameraView!=null)
//            mLiveCameraView.onpause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLiveCameraView != null)
            mLiveCameraView.destroy();
    }
}
