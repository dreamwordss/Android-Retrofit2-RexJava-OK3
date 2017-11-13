package com.lbl.networkframe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lbl.networkframe.network.NetWorkUtil;
import com.lbl.networkframe.network.nethelper.RetrofitHelper;
import com.lbl.networkframe.network.netservice.ApiService;

import io.reactivex.Observable;

//import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
    }

    private void initListener() {
        findViewById(R.id.text_tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_tv:
                getData();
                break;
        }
    }

    public void getData() {
        //简单好用，没有写界面，看log就明白了
        Observable<Object> searchVideo = RetrofitHelper.getService(ApiService.class).getSearchVideo("秋冬编发大全", "0", "20");
        //这个我返回来的是一个object 可以直接返回来数据对象
        NetWorkUtil.requestGet(searchVideo, new NetWorkUtil.OnResultListener() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(String msg) {

            }
        });

//        Observable<MeiziInfo> observable = RetrofitHelper.getService(ApiService.class).getMeizhiData("福利", 1);
//        NetWorkUtil.requestGet(observable, new NetWorkUtil.OnResultListener() {
//            @Override
//            public void onSuccess(Object T) {
//                MeiziInfo info = (MeiziInfo) T;
//                if (info != null && info.results.size() > 0) {
//                    Log.e("bilang-----------", info.results.toString());
//                }
//            }
//
//            @Override
//            public void onError(String msg) {
//
//            }
//        });

    }


}
