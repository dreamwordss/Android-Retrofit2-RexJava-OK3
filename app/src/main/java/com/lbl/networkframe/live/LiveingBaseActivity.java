package com.lbl.networkframe.live;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lbl.networkframe.BaseActivity;

import me.lbl.librestreaming.ws.StreamLiveCameraView;

/**
 * author：libilang
 * time: 18/3/21 09:45
 * 邮箱：libi_lang@163.com
 */

public class LiveingBaseActivity extends BaseActivity{

    StreamLiveCameraView streamLiveCameraView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
