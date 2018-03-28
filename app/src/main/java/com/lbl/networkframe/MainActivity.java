package com.lbl.networkframe;

import android.Manifest;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.lbl.networkframe.bean.LoopBean;
import com.lbl.networkframe.bean.SearchDataList;
import com.lbl.networkframe.live.LiveStremingActivity;
import com.lbl.networkframe.network.NetWorkUtil;
import com.lbl.networkframe.network.nethelper.RetrofitHelper;
import com.lbl.networkframe.network.netservice.ApiService;
import com.lbl.networkframe.view.viewpager.GlideManager;
import com.lbl.networkframe.view.viewpager.anim.MzTransformer;
import com.lbl.networkframe.view.viewpager.bean.PageBean;
import com.lbl.networkframe.view.viewpager.callback.PageListener;
import com.lbl.networkframe.view.viewpager.indicator.ZoomIndicator;
import com.lbl.networkframe.view.viewpager.view.BLCirImageView;
import com.lbl.networkframe.view.viewpager.view.BLViewPager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    BLViewPager viewPager, arcBannerViewPager;
    ZoomIndicator indicator;
    TextView textView;
    BLCirImageView cirImage;

    ImageView scallImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initListener();
        initData();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (viewPager != null)
            viewPager.onReusme();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (viewPager != null)
            viewPager.onPause();
    }

    private void initview() {
        cirImage = (BLCirImageView) findViewById(R.id.cir_image);
        textView = findViewById(R.id.text_tv);
        viewPager = findViewById(R.id.loop_viewpager);
        indicator = findViewById(R.id.bottom_indicator);
        viewPager.setPageTransformer(false, new MzTransformer());
        scallImage = findViewById(R.id.scallImage);
        findViewById(R.id.tx_liveing).setOnClickListener(this);

    }

    private void initListener() {
        textView.setOnClickListener(this);
    }
    private void initData() {
        getData();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_tv:
                textView.setText("接口响应是真的很快\n差不多在80-110ms");
//                ObjectAnimator nopeAnimator = tada(scallImage);
//                nopeAnimator.setRepeatCount(ValueAnimator.INFINITE);
//                nopeAnimator.start();
                break;
            case R.id.tx_liveing:
                checkPermissions();
                break;
        }
    }

    private void checkPermissions() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Log.e("onPermissionGranted", "权限都有了");
                startActivity(new Intent(MainActivity.this, LiveStremingActivity.class));
                //打开
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {

            }
        };

        TedPermission.with(MainActivity.this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("为了使用直播，请允许以下权限")
                .setRationaleConfirmText("下一步")
                .setDeniedMessage("如果你拒绝这个权限,将不能使用直播能哦\n\n请进入设置页面开启权限")
                .setGotoSettingButtonText("前去开启")
                .setDeniedCloseButtonText("返回")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA)
                .check();
    }

    public static ObjectAnimator tada(View view) {
        return tada(view, 1f);
    }

    public static ObjectAnimator tada(View view, float shakeFactor) {

        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0f, 1f),
                Keyframe.ofFloat(.1f, .9f),
                Keyframe.ofFloat(.2f, .9f),
                Keyframe.ofFloat(.3f, 1.1f),
                Keyframe.ofFloat(.4f, 1.1f),
                Keyframe.ofFloat(.5f, 1.1f),
                Keyframe.ofFloat(.6f, 1.1f),
                Keyframe.ofFloat(.7f, 1.1f),
                Keyframe.ofFloat(.8f, 1.1f),
                Keyframe.ofFloat(.9f, 1.1f),
                Keyframe.ofFloat(1f, 1f)
        );

        PropertyValuesHolder pvhRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(.1f, -5f * shakeFactor),
                Keyframe.ofFloat(.2f, -5f * shakeFactor),
                Keyframe.ofFloat(.3f, 5f * shakeFactor),
                Keyframe.ofFloat(.4f, -5f * shakeFactor),
                Keyframe.ofFloat(.5f, 5f * shakeFactor),
                Keyframe.ofFloat(.6f, -5f * shakeFactor),
                Keyframe.ofFloat(.7f, 5f * shakeFactor),
                Keyframe.ofFloat(.8f, -5f * shakeFactor),
                Keyframe.ofFloat(.9f, 5f * shakeFactor),
                Keyframe.ofFloat(1f, 0)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhScaleX, pvhScaleY, pvhRotate).
                setDuration(900);
    }

    public static ObjectAnimator nope(View view) {
        int delta = view.getResources().getDimensionPixelOffset(R.dimen.spacing_medium);

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(500);
    }


    public void getData() {
        //简单好用，看log就明白了
        Observable<SearchDataList.DataBean> searchVideo = RetrofitHelper.getService(ApiService.class).getSearchVideo("秋冬编发大全", "0", "20");
        //这个直接返回来数据对象
        NetWorkUtil.requestGet(searchVideo, new NetWorkUtil.OnResultListener() {
            @Override
            public void onSuccess(Object o) {
                SearchDataList.DataBean bean = (SearchDataList.DataBean) o;
                setdata(bean);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    private void setdata(SearchDataList.DataBean beans) {
        new GlideManager.Builder()
                .setContext(this)
                .setImgSource("https://i.xiaohongchun.com/0DDD6C3284CE3C74")
                .setImageView(cirImage)
                .builder();
        List<LoopBean> loopBeens = new ArrayList<>();
        if (beans.content.size() > 0)
            for (SearchDataList.DataBean.ContentBean bean : beans.content) {
                LoopBean bean1 = new LoopBean();
                if (bean.getType().endsWith("video")) {
                    bean1.url = bean.getVideo().getCover_url();
                    bean1.text = bean.getVideo().getTitle();
                } else {
                    bean1.url = bean.getSharebuy().getCover_url();
                    bean1.text = bean.getSharebuy().getTitle();
                }
                loopBeens.add(bean1);
            }
        List<LoopBean> loopBeans = loopBeens.subList(0, 5);
        PageBean pagebeans = new PageBean.Builder<LoopBean>()
                .setDataObjects(loopBeans)
                .setIndicator(indicator)
                .builder();
        viewPager.setPageListener(pagebeans, R.layout.arc_loop_layout, new PageListener() {
            @Override
            public void getItemView(View view, Object data) {
                BLCirImageView imageView = view.findViewById(R.id.cir_icon);
                LoopBean bean = (LoopBean) data;
                new GlideManager.Builder()
                        .setContext(MainActivity.this)
                        .setImgSource(bean.url)
                        .setLoadingBitmap(R.mipmap.ic_launcher)
                        .setImageView(imageView)
                        .builder();
            }
        });

    }


}
