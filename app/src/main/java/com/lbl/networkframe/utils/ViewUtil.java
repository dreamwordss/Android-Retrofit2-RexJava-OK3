package com.lbl.networkframe.utils;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.lbl.networkframe.MainActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * author：libilang
 * time: 16/5/3 10:01
 * 邮箱：libi_lang@163.com
 * <p/>
 * ViewUtil 用于创建公共的diaolog和一些公共显示
 */
public class ViewUtil {
    private static Toast mToast;
    private static Dialog joincodeDialog;
    static boolean isTure = false;
    private static WifiManager my_wifiManager;
    private static WifiInfo wifiInfo;
    private static DhcpInfo dhcpInfo;
    private static boolean isAnimation;//动画是否执行
    private static Dialog inditifyDialog = null;


    //显示头部动画
    public static void showAlphaAnimation(View view) {
        view.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        view.startAnimation(alphaAnimation);
    }

    //隐藏头部动画
    public static void hideAlphaAnimation(View view) {
        view.setVisibility(View.GONE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        view.startAnimation(alphaAnimation);
    }

//    public static void updateFansTips(TextView view, int count) {
//        if (count > 0) {
//            if (count < 10) {
//                view.setText(count + "");
//                view.setBackgroundResource(R.drawable.circle_layout_chose);
//            }
//            else if (count < 100) {
//                view.setText(count + "");
//                view.setBackgroundResource(R.drawable.rectangle_layout_chose);
//            }
//            else {
//                view.setText("99+");
//                view.setBackgroundResource(R.drawable.rectangle_layout_chose);
//            }
//            view.setVisibility(View.VISIBLE);
//        }
//        else
//            view.setVisibility(View.GONE);
//
//    }

    public static String showNum(int count) {
        String ss = "0";
        try {
            if (count > 0 && count < 9999) {
                ss = String.valueOf(count);
            }
            else if (count > 9999) {
                double s = (double) count / 10000;
                BigDecimal bg = new BigDecimal(s).setScale(1, RoundingMode.UP);
                ss = bg.doubleValue() + "万";
            }
            else {
                ss = "0";
            }
        }
        catch (Exception e) {
        }
        return ss;
    }


//    public static void updateFansTipsWhite(TextView view, int count) {
//        if (count > 0) {
//            if (count < 10) {
//                view.setText(count + "");
//                view.setBackgroundResource(R.drawable.circle_white_layout_chose);
//            }
//            else if (count < 100) {
//                view.setText(count + "");
//                view.setBackgroundResource(R.drawable.rectangle_white_layout_chose);
//            }
//            else {
//                view.setText("99+");
//                view.setBackgroundResource(R.drawable.rectangle_white_layout_chose);
//            }
//            view.setVisibility(View.VISIBLE);
//        }
//        else
//            view.setVisibility(View.GONE);
//
//    }

    //判断有没有安装微博
    public static boolean isWeiboInstalled(Context context) {
        PackageManager pm;
        if ((pm = context.getApplicationContext().getPackageManager()) == null) {
            return false;
        }
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo info : packages) {
            String name = info.packageName.toLowerCase(Locale.ENGLISH);
            if ("com.sina.weibo".equals(name)) {
                return true;
            }
        }
        return false;
    }
//
//    /**
//     * @param bitmap     原图
//     * @param edgeLength 希望得到的正方形部分的边长
//     * @return 缩放截取正中部分后的位图。
//     */
//    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
//        if (null == bitmap || edgeLength <= 0) {
//            return null;
//        }
//
//        Bitmap result = bitmap;
//        int widthOrg = bitmap.getWidth();
//        int heightOrg = bitmap.getHeight();
//
//        if (widthOrg > edgeLength && heightOrg > edgeLength) {
//            //压缩到一个最小长度是edgeLength的bitmap
//            int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
//            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
//            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
//            Bitmap scaledBitmap;
//
//            try {
//                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
//            }
//            catch (Exception e) {
//                return null;
//            }
//
//            //从图中截取正中间的正方形部分。
//            int xTopLeft = (scaledWidth - edgeLength) / 2;
//            int yTopLeft = (scaledHeight - edgeLength) / 2;
//
//            try {
//                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
//                scaledBitmap.recycle();
//            }
//            catch (Exception e) {
//                return null;
//            }
//        }
//
//        return result;
//    }
//
//    /**
//     * textview的某几个文字的点击事件
//     *
//     * @param view          TextView
//     * @param clickableText 可点击的的某几个文字
//     * @param listener      点击事件
//     */
////    public static void clickiNotify(TextView view, final String clickableText, final ClickSpan.OnClickListener listener) {
////        CharSequence text = view.getText();
////        String string = text.toString();
////        ClickSpan span = new ClickSpan(listener);
////        int start = string.indexOf(clickableText);
////        int end = start + clickableText.length();
////        if (start == -1) return;
////        if (text instanceof Spannable) {
////            ((Spannable) text).setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        }
////        else {
////            SpannableString s = SpannableString.valueOf(text);
////            s.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////            view.setText(s);
////        }
////        MovementMethod m = view.getMovementMethod();
////        if ((m == null) || !(m instanceof LinkMovementMethod)) {
////            view.setMovementMethod(LinkMovementMethod.getInstance());
////        }
////    }
//
//    /**
//     * textview的某几个文字的点击事件
//     *
//     * @param view          TextView
//     * @param clickableText 可点击的的某几个文字
//     * @param listener      点击事件
//     */
////    public static void clickNotifyRemoveLine(TextView view, final String clickableText, final ClickRomveLineSpan.OnClickListener listener) {
////        CharSequence text = view.getText();
////        String string = text.toString();
////        ClickRomveLineSpan span = new ClickRomveLineSpan(listener);
////        int start = string.indexOf(clickableText);
////        int end = start + clickableText.length();
////        if (start == -1) return;
////        if (text instanceof Spannable) {
////            ((Spannable) text).setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////        }
////        else {
////            SpannableString s = SpannableString.valueOf(text);
////            s.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
////            view.setText(s);
////        }
////        MovementMethod m = view.getMovementMethod();
////        if ((m == null) || !(m instanceof LinkMovementMethod)) {
////            view.setMovementMethod(LinkMovementMethod.getInstance());
////        }
////    }
//
//    public interface sureClickListener {
//        void sureJoinCode(String code);
//    }
//
//    /**
//     * 没有连接网络
//     */
//    private static final int NETWORK_NONE = -1;
//    /**
//     * 移动网络
//     */
//    private static final int NETWORK_MOBILE = 0;
//    /**
//     * 无线网络
//     */
//    private static final int NETWORK_WIFI = 1;
//
////    public static void onNetChange(int netWorkState) {
////        if (netWorkState == 1) {
////            BaseApplication.isWify = true;
////            BaseApplication.ishaveNet = true;
////        }
////        else if (netWorkState == 0) {
////            BaseApplication.ishaveNet = true;
////            BaseApplication.isWify = false;
////        }
////        else if (netWorkState == -1) {
////            BaseApplication.isWify = false;
////            BaseApplication.ishaveNet = false;
////        }
////    }
//
//    public static int getNetWorkState(Context context) {
//        // 得到连接管理器对象
//        ConnectivityManager connectivityManager = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetworkInfo = connectivityManager
//                .getActiveNetworkInfo();
//        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
//
//            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
//                return NETWORK_WIFI;
//            }
//            else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
//                return NETWORK_MOBILE;
//            }
//        }
//        else {
//            return NETWORK_NONE;
//        }
//        return NETWORK_NONE;
//    }
//
//    //判断是不是wifi
//    public static boolean isWifi(Context context) {
//        ConnectivityManager cm = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkINfo = cm.getActiveNetworkInfo();
//        if (networkINfo != null
//                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
//            return true;
//        }
//        return false;
//    }
//
    //获取手机硬件信息
    public static String getMobileInfo(Context context) {
        StringBuffer sb = new StringBuffer();
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String mtyb = android.os.Build.BRAND;// 手机品牌
            String mtype = android.os.Build.MODEL; // 手机型号
            String imei = tm.getDeviceId();
            String imsi = tm.getSubscriberId();
            String numer = tm.getLine1Number(); // 手机号码
            String serviceName = tm.getSimOperatorName(); // 运营商  //分表率
            String fenbiaolv = "";
            DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
            fenbiaolv = dm2.widthPixels + "/" + dm2.heightPixels;
            sb.append("品牌: " + mtyb + "\n" + "型号: " + mtype + "\n" + "版本: Android "
                    + android.os.Build.VERSION.RELEASE + "\n" + "IMEI: " + imei
                    + "\n" + "IMSI: " + imsi + "\n" + "手机号码: " + numer + "\n"
                    + "运营商: " + serviceName + "\n" + "分辨率: " + fenbiaolv + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //获取wifi的IP，子网掩码，网关，dns等信息
    public static String getWifiDNSInfo(Context context) {
        my_wifiManager = ((WifiManager) context.getSystemService(context.WIFI_SERVICE));
        dhcpInfo = my_wifiManager.getDhcpInfo();
        wifiInfo = my_wifiManager.getConnectionInfo();
        StringBuilder sb = new StringBuilder();
//        sb.append("网络信息：");
        sb.append("\nipAddress：" + intToIp(dhcpInfo.ipAddress));
        sb.append("\nnetmask：" + intToIp(dhcpInfo.netmask));
        sb.append("\ngateway：" + intToIp(dhcpInfo.gateway));
        sb.append("\nserverAddress：" + intToIp(dhcpInfo.serverAddress));
        sb.append("\ndns1：" + intToIp(dhcpInfo.dns1));
        sb.append("\ndns2：" + intToIp(dhcpInfo.dns2));
        sb.append("\n");

        sb.append("Wifi信息：");
        sb.append("\nIpAddress：" + intToIp(wifiInfo.getIpAddress()));
        sb.append("\nMacAddress：" + wifiInfo.getMacAddress());
        return sb.toString();
    }
//
    private static String intToIp(int paramInt) {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "."
                + (0xFF & paramInt >> 24);
    }
//
//    public static int dip2px(Context context, float dpValue) {
//        if (dpValue < 1) {
//            return 0;
//        }
//        final float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dpValue * scale + 0.5f);
//    }
//
//    public static int dip2px2(Context context, float dipValue) {
//        Resources r = context.getResources();
//        return (int) TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
//    }
//
//    // 转换dip为px
//    public static int convertDIP2PX(Context context, int dip) {
//        float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
//    }
//
//    // 转换px为dip
//    public static int convertPX2DIP(Context context, int px) {
//        float scale = context.getResources().getDisplayMetrics().density;
//        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
//    }
//
//    //获取/第三次出现的position
//    public static int getUrlNoProtoca(String subUrl, int size) {
//        char ssArr[] = subUrl.toCharArray();
//        char ss = '/';
//        int count = 0;
//        for (int j = 0; j < ssArr.length; j++) {
//            if (ss == ssArr[j]) {
//                count++;
//            }
//            if (count == size) {
//                return j;
//            }
//        }
//        return 0;
//    }
//
//    //获取/第三次出现的position
//    public static int getUrlNoProtocapo(String subUrl, int size) {
//        char ssArr[] = subUrl.toCharArray();
//        char ss = '.';
//        int count = 0;
//        for (int j = 0; j < ssArr.length; j++) {
//            if (ss == ssArr[j]) {
//                count++;
//            }
//            if (count == size) {
//                return j;
//            }
//        }
//        return 0;
//    }
//
//    //显示和修改 点赞数量 type1显示 type2数量加一
//    public static void showUpdateNumber(TextView view, int type, int oldNumber) {
//        double result = 0;
//        double newNumber = Double.valueOf(oldNumber + "");
//        if (1 == type) {
//            if (oldNumber > 999) {
//                result = newNumber / 1000;
//                java.text.DecimalFormat df = new java.text.DecimalFormat("0.0");
//                view.setText(df.format(result) + "k");
//            }
//            else {
//                view.setText(oldNumber + "");
//            }
//        }
//        else {
//            oldNumber += 1;
//            double result1 = oldNumber;
//            if (oldNumber < 1000) {
//                view.setText(oldNumber + "");
//            }
//            else if (oldNumber == 1000) {
//                java.text.DecimalFormat df = new java.text.DecimalFormat("0.0");
//                view.setText(df.format(result1) + "k");
//            }
//        }
//    }
//
//    /**
//     * @param info
//     * @param time 1 代表长时间 , 0 代表短时间
//     * @author libilanbg
//     * @Title: show吐司
//     * @Description: TODO
//     */
//    public static void showToast(Context context, String info, int time) {
////
////        if (mToast == null) {
////            mToast = ToastUtils.showAtCenter(context, info, time);
////        } else {
////            mToast.setText(info);
////            mToast.setDuration(time);
////        }
////        mToast.show();
//        ToastUtils.showAtCenter(context, info, time);
//    }
//
//    /**
//     * @param context
//     * @return
//     * @Title: checkNet
//     * @Description: TODO 检测网络链接
//     */
//    public static boolean checkNet(Context context) {
//        if (context == null) {
//            return false;
//        }
//        ConnectivityManager con = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = con.getActiveNetworkInfo();
//        if (networkInfo == null || !networkInfo.isAvailable()) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }
//
//    //质量压缩
//    public static Bitmap compressImage(Bitmap image) {
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        //质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        int options = 30;
//        //循环判断如果压缩后图片是否大于1M,大于继续压缩
//        while (baos.toByteArray().length / 1024 / 1024 > 1) {
//            //重置baos即清空baos
//            baos.reset();
//            //这里压缩options%，把压缩后的数据存放到baos中
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
//            if (options > 10) {
//                options -= 10;//每次都减少10
//            }
//        }
//        //把压缩后的数据baos存放到ByteArrayInputStream中
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
//        //把ByteArrayInputStream数据生成图片
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
//        return bitmap;
//    }
//
//    //图片按比例大小压缩方法
//    public static Bitmap getimage(String srcPath, float with, float height) {
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;
//        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
//        //此时返回bm为空
//        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
//        float hh = height;//这里设置高度为800f
//        float ww = with;//这里设置宽度为480f
//        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        }
//        else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//        if (be <= 0)
//            be = 1;
//        newOpts.inSampleSize = be;//设置缩放比例
//        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
////        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
//        return bitmap;
//    }
//
//    //压缩方式三、图片按比例大小压缩方法（根据bitmap图片压缩）
//    public static Bitmap comp(Bitmap image, float with, float height) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        //判断如果图片大于1M,进行压缩避免在生成图片
//        //（BitmapFactory.decodeStream）时溢出
//        if (baos.toByteArray().length / 1024 > 1024) {
//            baos.reset();//重置baos即清空baos
//            //这里压缩50%，把压缩后的数据存放到baos中
//            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
//        }
//        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
//        BitmapFactory.Options newOpts = new BitmapFactory.Options();
//        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
//        newOpts.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
//        newOpts.inJustDecodeBounds = false;
//        int w = newOpts.outWidth;
//        int h = newOpts.outHeight;
//        //设置800*480分辨率，所以高和宽我们设置为
//        float hh = height;//这里设置高度为800f
//        float ww = with;//这里设置宽度为480f
//        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
//        int be = 1;//be=1表示不缩放
//        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
//            be = (int) (newOpts.outWidth / ww);
//        }
//        else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
//            be = (int) (newOpts.outHeight / hh);
//        }
//        if (be <= 0)
//            be = 1;
//        newOpts.inSampleSize = be;//设置缩放比例
//        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        isBm = new ByteArrayInputStream(baos.toByteArray());
//        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
//        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
//    }
//
//
//    //保留两位小数
//    public static String douckumenNumberString(String price) {
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        double number = Double.valueOf(price);
//        return decimalFormat.format(number);
//    }
//
//    public static String douckumenNumberDouble(Double price) {
//        DecimalFormat decimalFormat = new DecimalFormat("0.00");
//        return decimalFormat.format(price);
//    }
//
//    //用户访问接口唯一标识
//    public static String UserOnlyOne(Context context) {
//        ConstantS.USER_CONNECT_NETSZIE++;
//        String diveceid = "";
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        if (tm != null && !TextUtils.isEmpty(tm.getDeviceId())) {
//            diveceid = tm.getDeviceId() + System.currentTimeMillis() + ConstantS.USER_CONNECT_NETSZIE + "";
//        }
//        return diveceid;
//    }
//
//    //检测APP是否在前台运行
//    public static boolean isBackground(Context context) {
//        ActivityManager activityManager = (ActivityManager) context
//                .getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
//                .getRunningAppProcesses();
//        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
//            if (appProcess.processName.equals(context.getPackageName())) {
//                /*
//                BACKGROUND=400 EMPTY=500 FOREGROUND=100
//                GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
//                 */
//                Log.i(context.getPackageName(), "此appimportace ="
//                        + appProcess.importance
//                        + ",context.getClass().getName()="
//                        + context.getClass().getName());
//                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//                    Logger.e(context.getPackageName(), "处于后台"
//                            + appProcess.processName);
//                    return true;
//                }
//                else {
//                    Logger.e(context.getPackageName(), "处于前台"
//                            + appProcess.processName);
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    //获取状态栏高度
//    public static int getStatusBarHeight(Context context) {
//        int result = 0;
//        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = context.getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }
//
//    //获取是否存在NavigationBar 系统虚拟按键
//    public static boolean checkDeviceHasNavigationBar(Context context) {
//        boolean hasNavigationBar = false;
//        Resources rs = context.getResources();
//        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
//        if (id > 0) {
//            hasNavigationBar = rs.getBoolean(id);
//        }
//        try {
//            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
//            Method m = systemPropertiesClass.getMethod("get", String.class);
//            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
//            if ("1".equals(navBarOverride)) {
//                hasNavigationBar = false;
//            }
//            else if ("0".equals(navBarOverride)) {
//                hasNavigationBar = true;
//            }
//        }
//        catch (Exception e) {
//
//        }
//        return hasNavigationBar;
//
//    }
//
//    /**
//     * 得到popuwindow实际显示的位置 由于NavigationBarShown(虚拟键盘)导致popuwindow显示不正常
//     *
//     * @param context
//     * @param bottomView 最底部控件(最后贴近屏幕底部)
//     * @return 实际显示的y值
//     */
//    public static int getRealHeight(Activity context, View bottomView) {
//        int height = 0;
//        int windowHeight = context.getWindow().getDecorView().getHeight();
//        int[] location = new int[2];
//        bottomView.getLocationInWindow(location);
//        if (bottomView.getHeight() + location[1] != windowHeight) {//出现键盘
//            if (ViewUtil.checkDeviceHasNavigationBar(context)) {
//                height += getBottomStatusHeight(context);
//            }
//        }
//        return height;
//    }
//
//    //获取屏幕原始尺寸高度，包括虚拟功能键高度
//    public static int getDpi(Context context) {
//        int dpi = 0;
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = windowManager.getDefaultDisplay();
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        @SuppressWarnings("rawtypes")
//        Class c;
//        try {
//            c = Class.forName("android.view.Display");
//            @SuppressWarnings("unchecked")
//            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
//            method.invoke(display, displayMetrics);
//            dpi = displayMetrics.heightPixels;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dpi;
//    }
//
//    /**
//     * 获取 虚拟按键的高度
//     *
//     * @param context
//     * @return
//     */
//    public static int getBottomStatusHeight(Context context) {
//        if (ConstantS.ISMAXScreenRatio) {
//            return 0;
//        } else {
//            int totalHeight = getDpi(context);
//
//            int contentHeight = getScreenHeight(context);
//
//            return totalHeight - contentHeight;
//        }
//    }
//
//    public static int getBottomStatusHeightNew(Context context) {
//        int totalHeight = getDpi(context);
//
//        int contentHeight = getScreenHeight(context);
//
//        return totalHeight - contentHeight;
//    }
//    public static double calculateDpi(int width, int height, double inch) {
//        double diagonal = Math.sqrt(width * width + height * height);
//        return diagonal / inch;
//    }
//    /**
//     * 获得屏幕高度
//     *
//     * @param context
//     * @return
//     */
//    public static int getScreenHeight(Context context) {
//        WindowManager wm = (WindowManager) context
//                .getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(outMetrics);
//        return outMetrics.heightPixels;
//    }
//
//    //系统导航栏高度
//    public static int getStatusHeight(Context context) {
//        int statusHeight = -1;
//        try {
//            Class<?> clazz = Class.forName("com.android.internal.R$dimen.xml");
//            Object object = clazz.newInstance();
//            int height = Integer.parseInt(clazz.getField("status_bar_height")
//                    .get(object).toString());
//            statusHeight = context.getResources().getDimensionPixelSize(height);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return statusHeight;
//    }
//
//
//    //上传log文件 检测 异常log大小 上传服务器
//    public static void upLoadXHClogFile(boolean noCheckSize, String logText) {
//        File logFile = new File(Environment.getExternalStorageDirectory() + "/encodeTmp/log/xiaohongchun.log");
//        long fileS = 0;
//        Logger.d("upload", "------1----------");
//        try {
//            if (!noCheckSize) {
//                FileInputStream fis = null;
//                if (logFile.exists()) {
//                    try {
//                        fis = new FileInputStream(logFile);
//                        fileS = fis.available();
//                    }
//                    catch (Exception e) {
//                        e.printStackTrace();
//                        return;
//                    }
//                }
//                else {
//                    return;
//                }
//
//                if (fileS >= ConstantS.LOGSIZE) {
//                    //大于512K上传 然后删除log文件
//                    //上传
//                    //读取 log文件把内容转成string
//                    try {
//                        //创建File对象，确定需要读取文件的信息 */
////                    File file = new File(Environment.getExternalStorageDirectory() + "/encodeTmp/log/xiaohongchun.log");
//                        //FileInputSteam 输入流的对象， */
////                    FileInputStream fiss = new FileInputStream(file);
//                        //准备一个字节数组用户装即将读取的数据 */
//                        byte[] buffer = new byte[fis.available()];
//                        //开始进行文件的读取
//                        fis.read(buffer);
//                        //关闭流
//                        fis.close();
//                        //将字节数组转换成字符窜， 并转换编码的格式
//                        String res = EncodingUtils.getString(buffer, "UTF-8");
//                        logText = res;
//                        Logger.d("Logshangchuan", "---" + logText);
//                        Logger.e("Log文件读取成功", "文件读取成功");
//                    }
//                    catch (Exception ex) {
//                        LogUtils.e("Log文件读取失败", "文件读取失败");
//                        return;
//                    }
//                    if (StringUtil.isStringEmpty(logText)) {
//                        return;
//                    }
//                    Logger.e("upload", "------2----------");
//                    if (!StringUtil.isStringEmpty(logText) && logText.length() > 20) {
//                        uploadLogFile(logText);
//                    }
//                    Logger.e("upload", "------3----------");
//                }
//                else {
//                    return;
//                }
//            }
//            else {
//                //直接上传
//                try {
//                    if (!StringUtil.isStringEmpty(logText) && logText.length() > 20) {
//                        Logger.e("upload", "------4----------");
//                        uploadLogFile(logText);
//                    }
//                }
//                catch (Exception e) {
//                }
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//
//    }
//
//    //
//    public static void uploadLogFile(String logText) {
//        Logger.e("upload", logText);
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("logtext", logText));
//        Logger.e("upload", "上传日志----" + logText);
//        NetWorkManager.getInstance().request(Api.API_UPLOAD_ERRORLOG, params,
//                HttpRequest.HttpMethod.POST, new NetWorkManager.OnRequestListener() {
//
//                    @Override
//                    public void onSuccess(SuccessRespBean resp) {
//                        Logger.e("upload", "------5----------");
//                        deleteLogFile();
//                    }
//
//                    @Override
//                    public void onFailure(ErrorRespBean resp) {
//                        deleteLogFile();
//                        Logger.e("upload", "------6----------");
//                    }
//                });
//    }
//
//    public static void deleteLogFile() {
//        String logPath = Environment.getExternalStorageDirectory() + "/encodeTmp/log/xiaohongchun.log";
//        try {
//            ViewUtil.deleteFile(new File(logPath));
//        }
//        catch (IOException e) {
//            LogUtils.e("删除log文件失败", e.toString());
//            e.printStackTrace();
//        }
//    }
//
//    //判断文件是否存在
//    public static boolean fileIsExist(String filePath) {
//        try {
//            File f = new File(filePath);
//            if (!f.exists()) {
//                return false;
//            }
//        }
//        catch (Exception e) {
//            // TODO: handle exception
//            return false;
//        }
//        return true;
//    }
//
//    //获取本地未发送的视频 传一个视频的父路径
//    public static List<File> getLocalViedoFile(String filePath) {
//        List<File> videoList = new ArrayList<>();
//        File farFile = new File(filePath);
//        File[] files = farFile.listFiles();
//        if (files != null) {
//            for (File videoFile : files) {
//                videoList.add(videoFile);
//            }
//        }
//        return videoList;
//    }
//
//    //发布图片的集合地址  一个大的文件夹下的文件夹集合地址
//    public static List<Map<String, Map<String, List<String>>>> getUnPublishPhotoUrlFirst(String photoFilePath) {
//        //文件夹的地址 对应 文件夹下的图片和描述
//        List<Map<String, Map<String, List<String>>>> listMap = new ArrayList<>();
//        HashMap<String, List<String>> maps = null;
//        HashMap<String, Map<String, List<String>>> fileMaps = null;//图片文件夹路径 和描述和地址
//        List<String> photoUrls = null;
//
//        File farFile = new File(photoFilePath);
//        //是文件夹的话
//        if (farFile.isDirectory() && farFile != null) {
//            File[] files = farFile.listFiles();
//            if (files != null) {
//
//                for (int i = 0; i < files.length; i++) {
//                    File photoFile = files[i];
//                    String mapPath = "";//图片的文件夹地址
//                    if (photoFile.isDirectory()) {
//                        mapPath = photoFile.getAbsolutePath();
//                        File[] photos = photoFile.listFiles();
//                        String Phototext = "";//图片的描述
//
//                        fileMaps = new HashMap<>();
//                        maps = new HashMap<>();//描述和图片地址
//                        photoUrls = new ArrayList<>();
//                        for (File photoImage : photos) {
//                            String firstPhotoUrl = photoImage.getAbsolutePath();
//                            if (!firstPhotoUrl.endsWith(".txt")) {
//                                //图片地址
//                                photoUrls.add(firstPhotoUrl);
//                            }
//                            if (firstPhotoUrl.endsWith(".txt")) {
//                                //图片描述文件
//                                Phototext = getTextFileContent(photoImage.getParentFile().getAbsolutePath() + "/content.txt");
//                                Logger.e("vvvvvv", "Phototext==" + Phototext);
//                            }
//                        }
//                        maps.put(Phototext, photoUrls);
//                        //放文件
//                        fileMaps.put(mapPath, maps);
//                    }
//                    listMap.add(fileMaps);
//                }
//            }
//        }
//        return listMap;
//    }

//    //保存文字到指定目录下
//    public static void saveTextFile(String strContent, String photoTag) {
//        String filePath = null;
//        String result = Environment.getExternalStorageDirectory()
//                + "/encodeTmp/editpic/" + photoTag + "/";
//        filePath = result + "content.txt";
//        Logger.e("vvvv", "文字地址----" + filePath);
//
//        try {
//            File file = new File(filePath);
//            if (!file.exists()) {
//                File dir = new File(file.getParent());
//                dir.mkdirs();
//                file.createNewFile();
//            }
//            OutputStreamWriter write = null;
//            BufferedWriter out = null;
//            if (filePath != null) {
//                try {   // new FileOutputStream(fileName, true) 第二个参数表示追加写入
//                    write = new OutputStreamWriter(new FileOutputStream(
//                            filePath), Charset.forName("gbk"));//一定要使用gbk格式
//                    out = new BufferedWriter(write, 1024 * 512);
//                }
//                catch (Exception e) {
//                }
//            }
//            out.write(strContent);
//            out.flush();
//            out.close();
//        }
//        catch (Exception e) {
//            Log.e(TAG, e.getMessage(), e);
//        }
//    }
//
//    //读取指定目录下的所有TXT文件的文件内容
//    public static String getTextFileContent(String path) {
//        File file = null;
//        try {
//            file = new File(path);
//        }
//        catch (Exception e) {
//            return "";
//        }
//        String content = "";
//        if (file.isDirectory()) {  //检查此路径名的文件是否是一个目录(文件夹)
//            Log.i("lbl", "The file not exist "
//                    + file.getName().toString() + file.getPath().toString());
//        }
//        else {
//            if (file.getName().endsWith(".txt")) {//文件格式为txt文件
//                try {
//                    InputStream instream = new FileInputStream(file);
//                    if (instream != null) {
//                        InputStreamReader inputreader
//                                = new InputStreamReader(instream, "GBK");
//                        BufferedReader buffreader = new BufferedReader(inputreader);
//                        String line = "";
//                        //分行读取
//                        while ((line = buffreader.readLine()) != null) {
//                            content += line + "\n";
//                        }
//                        instream.close();       //关闭输入流
//                    }
//                }
//                catch (java.io.FileNotFoundException e) {
//                    Log.d("TestFile", "The File doesn't not exist.");
//                }
//                catch (IOException e) {
//                    Log.d("TestFile", e.getMessage());
//                }
//            }
//        }
//        return content;
//    }

    /**
     * 删除文件
     *
     * @param file path
     * @throws IOException
     */
    public static void deleteFile(File file) throws IOException {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            }
            else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                if (file.listFiles() != null) {
                    for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                        deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                    }
                }
            }
            file.delete();
        }
    }

    /**
     * 删除该目录下的所有文件包括自身目录
     *
     * @param folderFullPath 目录文件位置
     * @return
     */
    public static boolean deleteAllFile(String folderFullPath) {
        boolean ret = false;
        File file = new File(folderFullPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                if (fileList != null) {
                    for (int i = 0; i < fileList.length; i++) {
                        String filePath = fileList[i].getPath();
                        deleteAllFile(filePath);
                    }
                }
            }
            if (file.isFile()) {
                file.delete();
            }
        }
        file.delete();// 加上这一行，就可以不用deleteAllFullFolder方法了
        return ret;
    }

    //删除文件夹下的所有的文件 除开文件夹
    public static boolean deleteAllFileButDirectory(String folderFullPath) {
        boolean ret = false;
        File file = new File(folderFullPath);
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                if (fileList != null && fileList.length > 0) {
                    for (int i = 0; i < fileList.length; i++) {
                        String filePath = fileList[i].getPath();
                        File file1 = new File(filePath);
                        if (file1.isFile()) {
                            file1.delete();
                        }
                        deleteAllFileButDirectory(folderFullPath);
                    }
                }
            }
        }
        return ret;
    }


    //创建一个按钮的dialog
//    public static Dialog createDialog2Btn(Context c, String title, String content,
//                                          String btnSure, final View.OnClickListener sure) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.customdialog, null);
//
//        TextView mTitle = (TextView) mDialogView
//                .findViewById(R.id.title);
//        TextView mContent = (TextView) mDialogView
//                .findViewById(R.id.message);
//        TextView sureBtn = (TextView) mDialogView
//                .findViewById(R.id.confirm_btn);
//        TextView cannelBtn = (TextView) mDialogView.findViewById(R.id.cancel_btn);
//
//        mTitle.setText(title);
//        mContent.setText(content);
//        sureBtn.setText(btnSure);
//
//        final Dialog updateDialog = new Dialog(c, R.style.shopDialog);
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (sure != null)
//                    sure.onClick(v);
//            }
//        });
//        cannelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//            }
//        });
//        updateDialog.setContentView(mDialogView);
//        updateDialog.setCanceledOnTouchOutside(false);
//        updateDialog.show();
//
//        return updateDialog;
//    }

    //创建没有title的提示框
//    public static Dialog createDialog2BtnNorTitle(Context c, String content,
//                                                  String btnSure, String btnCannel, final View.OnClickListener sure, final View.OnClickListener cannel) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.customdialog_notitle, null);
//
//        TextView mContent = (TextView) mDialogView
//                .findViewById(R.id.message);
//        TextView sureBtn = (TextView) mDialogView
//                .findViewById(R.id.confirm_btn);
//        TextView cannelBtn = (TextView) mDialogView.findViewById(R.id.cancel_btn);
//
//        mContent.setText(content);
//        sureBtn.setText(btnSure);
//        cannelBtn.setText(btnCannel);
//        final Dialog updateDialog = new Dialog(c, R.style.shopDialog);
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (sure != null)
//                    sure.onClick(v);
//            }
//        });
//        cannelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (cannel != null)
//                    cannel.onClick(v);
//            }
//        });
//        updateDialog.setContentView(mDialogView);
//        updateDialog.setCanceledOnTouchOutside(false);
//        updateDialog.show();
//
//        return updateDialog;
//    }


    //视频编辑恢复的dialog
//    public static Dialog createDialog2BtnRevert(Context c, String title, String content,
//                                                String btnSure, final View.OnClickListener sure) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.dialog_rever, null);
//
//        TextView mTitle = (TextView) mDialogView
//                .findViewById(R.id.title);
//        TextView mContent = (TextView) mDialogView
//                .findViewById(R.id.message);
//        TextView sureBtn = (TextView) mDialogView
//                .findViewById(R.id.confirm_btn);
//        TextView cannelBtn = (TextView) mDialogView.findViewById(R.id.cancel_btn);
//
//        mTitle.setText(title);
//        mContent.setText(content);
//        sureBtn.setText(btnSure);
//
//        final Dialog updateDialog = new Dialog(c, R.style.shopDialog);
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (sure != null)
//                    sure.onClick(v);
//            }
//        });
//        cannelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//            }
//        });
//        updateDialog.setContentView(mDialogView);
//        updateDialog.setCanceledOnTouchOutside(false);
//        updateDialog.show();
//
//        return updateDialog;
//    }
//
//    //创建一个按钮的dialog
//    public static Dialog createDialog1BtnNew(Context c, CharSequence content, String title,
//                                             String btnSure, final View.OnClickListener sure) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.layout_onebutton_dialog, null);
//
//        TextView mTitle = (TextView) mDialogView
//                .findViewById(R.id.tv_dialog_title);
//        Button sureBtn = (Button) mDialogView
//                .findViewById(R.id.btn_dialog_findpsw_email_sure);
//        LtTextView mSuccess = (LtTextView) mDialogView
//                .findViewById(R.id.tv_findpsw_success);
//
//        mTitle.setText(title);
//        mSuccess.setText(content);
//        sureBtn.setText(btnSure);
//
//        final Dialog updateDialog = new Dialog(c, R.style.shopDialog);
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (sure != null)
//                    sure.onClick(v);
//            }
//        });
//        updateDialog.setContentView(mDialogView);
//        updateDialog.setCanceledOnTouchOutside(false);
//        updateDialog.show();
//
//        return updateDialog;
//    }
//
//    //发布视频删除标签dialog
//    public static Dialog createDialogPublishDeleteTag(Context c, final View.OnClickListener sure) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.customdialog, null);
//
//        TextView title = (TextView) mDialogView.findViewById(R.id.title);
//        TextView message = (TextView) mDialogView.findViewById(R.id.message);
//        TextView cancel_btn = (TextView) mDialogView.findViewById(R.id.cancel_btn);
//        TextView sureBtn = (TextView) mDialogView.findViewById(R.id.confirm_btn);
//        title.setText("提示");
//        message.setText("确定删除此标签?");
//
//        LinearLayout layout_content = (LinearLayout) mDialogView.findViewById(R.id.layout_content);
//        layout_content.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.bg_white_shape_r20));
//
//        final Dialog updateDialog = new Dialog(c, R.style.customDialogStyle);
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (sure != null)
//                    sure.onClick(v);
//            }
//        });
//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//            }
//        });
//        updateDialog.setContentView(mDialogView);
//        updateDialog.setCanceledOnTouchOutside(false);
//        updateDialog.show();
//
//        return updateDialog;
//    }
//
//    //拍摄视频部分的弹窗
//    public static Dialog createDialogForVideEdit(Context c, CharSequence content,
//                                                 String btnSure, final View.OnClickListener sure) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.layout_onebutton_dialog_videoedit, null);
//
//        Button sureBtn = (Button) mDialogView
//                .findViewById(R.id.btn_dialog_findpsw_email_sure);
//        TextView mSuccess = (TextView) mDialogView
//                .findViewById(R.id.tv_findpsw_success);
//
//        mSuccess.setText(content);
//        sureBtn.setText(btnSure);
//
//        final Dialog updateDialog = new Dialog(c, R.style.shopDialog);
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                updateDialog.dismiss();
//                if (sure != null)
//                    sure.onClick(v);
//            }
//        });
//        updateDialog.setContentView(mDialogView);
//        updateDialog.setCanceledOnTouchOutside(false);
//        updateDialog.show();
//
//        return updateDialog;
//    }
//
//    //创建参团口令的dialog
//    public static Dialog createDialogJoinCode(final Context c, final String gbr_id, final sureClickListener sure) {
//        View mDialogView = LayoutInflater.from(c).inflate(
//                R.layout.dialog_tuan_joincode, null);
//
//        final EditText joinCode = (EditText) mDialogView
//                .findViewById(R.id.et_gift);
//        TextView sureBtn = (TextView) mDialogView
//                .findViewById(R.id.gift_ok);
//        TextView cannelBtn = (TextView) mDialogView
//                .findViewById(R.id.gift_cancel);
//        joincodeDialog = new Dialog(c, R.style.shopDialog);
//        cannelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                joincodeDialog.dismiss();
//            }
//        });
//        sureBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                //先掉接口验证参团口令 正确就回调成功，失败就吐司
//                checkJoinCode(c, gbr_id, joinCode.getText().toString().trim(), sure);
//            }
//        });
//        joincodeDialog.setContentView(mDialogView);
//        joincodeDialog.setCanceledOnTouchOutside(false);
//        joincodeDialog.show();
//
//        return joincodeDialog;
//    }
//
//    public static void checkJoinCode(final Context context, String gbr_id, final String code, final sureClickListener sure) {
//        if (StringUtil.isStringEmpty(code)) {
//            showToast(context, "请输入参团码", 0);
//            return;
//        }
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("code", code));
//        params.add(new BasicNameValuePair("gbr_id", gbr_id));
//        NetWorkManager.getInstance().nOldRequestGetU8(Api.API_TUAN_CHECKTUAN_CODE, params, new NetWorkManager.OnRequestListener() {
//            @Override
//            public void onSuccess(SuccessRespBean resp) {
//                Logger.e("lbl", "11111111");
//                boolean ss = JSONObject.parseObject(resp.data).getBoolean("is_legal");
//                isTure = ss;
//                if (false == isTure) {
//                    showToast(context, "口令有误或团已结束", 0);
//                    return;
//                }
//                else {
//                    if (isTure) {
//                        joincodeDialog.dismiss();
//                        if (sure != null) {
//                            sure.sureJoinCode(code);
//                            isTure = false;
//                        }
//                        Logger.e("lbl", "22222222");
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(ErrorRespBean resp) {
//                isTure = false;
//            }
//        });
//    }

    /**
     * @param time  时间戳
     * @param match 想要的时间格式
     * @return
     * @Title: formatTime
     * @Description: TODO 把时间戳转换成自己想要的格式
     */
    public static String formatTime(String time, String match) {
        Date date = null;
        String reTime = "";
        try {
            date = new Date(Long.parseLong(time));
            reTime = new SimpleDateFormat(match).format(date);
        }
        catch (Exception e) {
            reTime = time;
        }
        return reTime;
    }


    //高斯模糊
    public static Bitmap doBlur(Bitmap sentBitmap, int radius,
                                boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }
        try {

            if (radius < 1) {
                return (null);
            }

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            int[] pix = new int[w * h];
            bitmap.getPixels(pix, 0, w, 0, 0, w, h);

            int wm = w - 1;
            int hm = h - 1;
            int wh = w * h;
            int div = radius + radius + 1;

            int r[] = new int[wh];
            int g[] = new int[wh];
            int b[] = new int[wh];
            int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
            int vmin[] = new int[Math.max(w, h)];

            int divsum = (div + 1) >> 1;
            divsum *= divsum;
            int dv[] = new int[256 * divsum];
            for (i = 0; i < 256 * divsum; i++) {
                dv[i] = (i / divsum);
            }

            yw = yi = 0;

            int[][] stack = new int[div][3];
            int stackpointer;
            int stackstart;
            int[] sir;
            int rbs;
            int r1 = radius + 1;
            int routsum, goutsum, boutsum;
            int rinsum, ginsum, binsum;

            for (y = 0; y < h; y++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                for (i = -radius; i <= radius; i++) {
                    p = pix[yi + Math.min(wm, Math.max(i, 0))];
                    sir = stack[i + radius];
                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);
                    rbs = r1 - Math.abs(i);
                    rsum += sir[0] * rbs;
                    gsum += sir[1] * rbs;
                    bsum += sir[2] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                }
                stackpointer = radius;

                for (x = 0; x < w; x++) {

                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                    }
                    p = pix[yw + vmin[x]];

                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[(stackpointer) % div];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi++;
                }
                yw += w;
            }
            for (x = 0; x < w; x++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                yp = -radius * w;
                for (i = -radius; i <= radius; i++) {
                    yi = Math.max(0, yp) + x;

                    sir = stack[i + radius];

                    sir[0] = r[yi];
                    sir[1] = g[yi];
                    sir[2] = b[yi];

                    rbs = r1 - Math.abs(i);

                    rsum += r[yi] * rbs;
                    gsum += g[yi] * rbs;
                    bsum += b[yi] * rbs;

                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }

                    if (i < hm) {
                        yp += w;
                    }
                }
                yi = x;
                stackpointer = radius;
                for (y = 0; y < h; y++) {
                    // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                    pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                            | (dv[gsum] << 8) | dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * w;
                    }
                    p = x + vmin[y];

                    sir[0] = r[p];
                    sir[1] = g[p];
                    sir[2] = b[p];

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi += w;
                }
            }

            bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        } catch (Exception e) {

        }


        return (bitmap);
    }

    //秒数转成时分秒
    public static String Int2Date(int time) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        java.sql.Date dt = new java.sql.Date(time);
        String sDateTime = sdf.format(dt);
        return sDateTime;
    }

    public static String formartDate(int time) {//毫秒转时间
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");//初始化Formatter的转换格式。
        String hms = formatter.format(time);
        return hms;
    }


    /**
     * 点赞动画
     *
     * @param orignalView
     * @param destView
     * @param destViewY   底部位置(有时候测量准确需要使用父布局来计算)
     */
    public static void startZanAnimation(final View orignalView, View destView, float destViewY) {
        if (isAnimation)
            return;
        isAnimation = true;
        orignalView.setVisibility(View.VISIBLE);
        final float orignalX = orignalView.getX();
        final float orignalY = orignalView.getY();
        PropertyValuesHolder pvScaleX = PropertyValuesHolder.ofFloat("scaleX",
                1, 4, 3.5f, 4, 3);
        PropertyValuesHolder pvScaleY = PropertyValuesHolder.ofFloat("scaleY",
                1, 4, 3.5f, 4, 3);
        PropertyValuesHolder pvAlpha = PropertyValuesHolder.ofFloat("alpha", 0,
                1);

        ObjectAnimator bouncesAnimation = ObjectAnimator
                .ofPropertyValuesHolder(orignalView, pvAlpha, pvScaleX,
                        pvScaleY);
        bouncesAnimation.setDuration(1000);
        bouncesAnimation.start();

        Keyframe frameX1 = Keyframe.ofFloat(0.1f, orignalView.getX());

        Keyframe frameX2 = Keyframe.ofFloat(0.2f, destView.getX());
        PropertyValuesHolder pvFrameX = PropertyValuesHolder.ofKeyframe("x",
                frameX1, frameX2);

        Keyframe frameY1 = Keyframe.ofFloat(0.8f, orignalView.getY());
//        Keyframe frameY2 = Keyframe.ofFloat(0.9f, destView.getY());
        Keyframe frameY2 = Keyframe.ofFloat(0.9f, destViewY);
        PropertyValuesHolder pvFrameY = PropertyValuesHolder.ofKeyframe("Y",
                frameY1, frameY2);
        PropertyValuesHolder pvAlphaOut = PropertyValuesHolder.ofFloat("alpha",
                1, 0);
        PropertyValuesHolder pvScaleXOut = PropertyValuesHolder.ofFloat(
                "scaleX", 3, 1);
        PropertyValuesHolder pvScaleYOut = PropertyValuesHolder.ofFloat(
                "scaleY", 3, 1);
        final ObjectAnimator translateAnimation = ObjectAnimator
                .ofPropertyValuesHolder(orignalView, pvScaleXOut, pvScaleYOut,
                        pvAlphaOut, pvFrameX, pvFrameY);
        translateAnimation.setDuration(500);

        translateAnimation.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                isAnimation = false;
                orignalView.setX(orignalX);
                orignalView.setY(orignalY);
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }
        });

        bouncesAnimation.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator arg0) {

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                translateAnimation.start();
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }
        });
    }

    //身份认证的接口
//    public static void checkUserIdentity(final TextView sureBtn, final Context context, int type, final String oid, final Dialog dialog, final String name, final String number) {
//        sureBtn.setEnabled(false);
//        sureBtn.setBackgroundResource(R.drawable.bg_grary_shape_indetify);
//        sureBtn.setTextColor(context.getResources().getColor(R.color.submit_color));
//
//        ArrayList<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("o_id", oid));
//        params.add(new BasicNameValuePair("id_card", number));
//        params.add(new BasicNameValuePair("name", name));
//        if (0 == type) {
//            params.add(new BasicNameValuePair("is_agree", "0"));
//        }
//        else {
//            params.add(new BasicNameValuePair("is_agree", "1"));
//        }
//        NetWorkManager.getInstance().oldPost(Api.API_USER_IDENTIFY,
//                params, new NetWorkManager.OnRequestListener() {
//                    @Override
//                    public void onSuccess(SuccessRespBean resp) {
//                        dialog.dismiss();
//                        ToastUtils.showAtCenter(context, "验证成功");
//                        Intent intent = new Intent(CONSTANTS.TUAN_ORDERDETAIL_UPDATE_DETAILS);
//                        context.sendBroadcast(intent);
//                        sureBtn.setEnabled(true);
//                        sureBtn.setBackgroundResource(R.drawable.bg_grary_shape_indetify_down);
//                        sureBtn.setTextColor(context.getResources().getColor(R.color.white));
//                    }
//
//                    @Override
//                    public void onFailure(ErrorRespBean resp) {
//                        Logger.e("dddd", resp.getCode() + resp.getMsg() + "");
//                        sureBtn.setEnabled(true);
//                        sureBtn.setBackgroundResource(R.drawable.bg_grary_shape_indetify_down);
//                        sureBtn.setTextColor(context.getResources().getColor(R.color.white));
////                        int code = JSONObject.parseObject(resp.data).getInteger("code");
////                        String msg = JSONObject.parseObject(resp.data).getString("msg");
//                        int code = Integer.valueOf(resp.getCode());
//                        String msg = resp.getMsg();
//                        //6015：今日实名认证已用完；
//                        // 6016：身份证格式不正确；
//                        // 6017：当前填写姓名与收件人不符无法正常清关，唇唇将自动使用填写姓名替换订单收货人；
//                        // 6008：错误的身份证号信息
//                        //type 1 两个按钮 2 一个按钮
//                        if (6015 == code) {
//                            createDialog2BtnUserInditify(context, 2, msg, null, null);
//                        }
//                        else if (6016 == code) {
//                            createDialog2BtnUserInditify(context, 2, msg, null, null);
//                        }
//                        else if (6017 == code) {
//                            createDialog2BtnUserInditify(context, 1, msg, new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                }
//                            }, new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    checkUserIdentity(sureBtn, context, 1, oid, dialog, name, number);
//                                }
//                            });
//                        }
//                        else if (6008 == code) {
//                            createDialog2BtnUserInditify(context, 2, msg, null, null);
//                        }
//                        else {
//                            dialog.dismiss();
//                        }
//                    }
//                }
//        );
//    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }


    //获取当前屏幕的亮度
    public static int getScreenBrightness(Activity activity) {
        int value = 0;
        ContentResolver cr = activity.getContentResolver();
        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
        }
        catch (Exception e) {

        }
        return value;
    }

    //设置当前屏幕的亮度
    public static void setScreenBrightness(Activity activity, int value) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.screenBrightness = value / 255f;
        activity.getWindow().setAttributes(params);
    }

//    //下载会员海报图片
//    public static void downloadImage(Context context, String url) {
//
//        final String downloadTarget = com.xiaohongchun.redlips.record.Util.getPosterSavePath(context) + File.separator + "image.jpg";
//
//        NetWorkManager.getInstance().getDonwloadUtils().download(url, downloadTarget, new RequestCallBack<File>() {
//            @Override
//            public void onSuccess(ResponseInfo<File> responseInfo) {
//                Log.e("bilang", "下载图片成功");
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Log.e("bilang", "下载图片失败");
//            }
//        });
//    }
//
//    //下单成功商品海报图片
//    public static void downloadOrderPosterImage(final Context context, String url) {
//        Log.e("download", "下载图片地址==" + url);
//        String url_fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
//        String fileName = Util.get16Md5Value(url_fileName) + ".jpg";
//        Log.e("download", "下载图片fileName==" + fileName);
//        final String downloadTarget = com.xiaohongchun.redlips.record.Util.getAppOrderDownLoadTmpPath(context) + File.separator + fileName;
//        File file = new File(downloadTarget);
//        if (file.exists()) {
//            return;
//        }
//        Log.e("download", "下载图片盛放地址==" + downloadTarget);
//        final String finalFileName = fileName;
//        NetWorkManager.getInstance().getDonwloadUtils().download(url, downloadTarget, new RequestCallBack<File>() {
//            @Override
//            public void onSuccess(ResponseInfo<File> responseInfo) {
//                Log.e("download", "下载图片成功");
//                //下载成功后刷新一下图片地址
//                try {
//                    // 把文件插入到系统图库
//                    try {
//                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                                Uri.parse("file://" + downloadTarget)));
//                    } catch (Exception e) {
//                        MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                                downloadTarget, finalFileName, null);
//                        Log.e("download", e + "插入失败");
//                    }
//                }
//                catch (Exception e) {
//                    Log.e("download", e + "刷新失败");
//                }
//            }
//
//            @Override
//            public void onFailure(HttpException e, String s) {
//                Log.e("bilang", "下载图片失败");
//            }
//        });
//    }
//

    //
    public static byte[] InputStream2Bytes(InputStream is) {
        String str = "";
        byte[] readByte = new byte[1024];
        int readCount = -1;
        try {
            while ((readCount = is.read(readByte, 0, 1024)) != -1) {
                str += new String(readByte).trim();
            }
            return str.getBytes();
        }
        catch (Exception e) {
        }
        return null;
    }

    /**
     * 质量压缩 并返回Bitmap
     *
     * @param image 要压缩的图片
     * @return 压缩后的图片
     */
    public static Bitmap compressImage32(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 20, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 30) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static boolean fileIsEmpty(File dirFile) {
        try {
            if (dirFile != null) {
                File[] files = dirFile.listFiles();
                if (files.length > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }

//    private static void saveImage(final String path) {
//        File file = new File(path);
//        try {
////            file.getParentFile().mkdirs();
//            MediaScannerConnection.scanFile(mContext,
//                    new String[]{file.toString()}, null,
//                    new MediaScannerConnection.OnScanCompletedListener() {
//                        @Override
//                        public void onScanCompleted(final String path,
//                                                    final Uri uri) {
////                            if (mListener != null) {
////                                mHandler.post(new Runnable() {
////
////                                    @Override
////                                    public void run() {
////                                        mListener.onPictureSaved(uri);
////                                    }
////                                });
////                            }
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            return new Point(display.getWidth(), display.getHeight());
        }else{
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

    public static boolean isExsitMianActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }



    public static void setListViewHeightBasedOnChildren(ListView listView) {
        try {
            // 获取ListView对应的Adapter
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }

            int totalHeight = 0;
            for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
                // listAdapter.getCount()返回数据项的数目
                View listItem = listAdapter.getView(i, null, listView);
                // 计算子项View 的宽高
                listItem.measure(0, 0);
                // 统计所有子项的总高度
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            // moreVideoLayout.getDividerHeight()获取子项间分隔符占用的高度
            // params.height最后得到整个ListView完整显示需要的高度
            listView.setLayoutParams(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    /**
     * 版本号比较
     *
     * @param version1
     * @param version2
     * @return  结果说明：0代表相等，1代表version1大于version2，-1代表version1小于version2
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        Log.d("HomePageActivity", "version1Array=="+version1Array.length);
        Log.d("HomePageActivity", "version2Array=="+version2Array.length);
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        Log.d("HomePageActivity", "verTag2=2222="+version1Array[index]);
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    public static boolean isViewCovered(final View view) {
        View currentView = view;

        Rect currentViewRect = new Rect();
        boolean partVisible = currentView.getGlobalVisibleRect(currentViewRect);
        boolean totalHeightVisible = (currentViewRect.bottom - currentViewRect.top) >= view.getMeasuredHeight();
        boolean totalWidthVisible = (currentViewRect.right - currentViewRect.left) >= view.getMeasuredWidth();
        boolean totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible;
        if (!totalViewVisible)//if any part of the view is clipped by any of its parents,return true
            return true;

        while (currentView.getParent() instanceof ViewGroup) {
            ViewGroup currentParent = (ViewGroup) currentView.getParent();
            if (currentParent.getVisibility() != View.VISIBLE)//if the parent of view is not visible,return true
                return true;

            int start = indexOfViewInParent(currentView, currentParent);
            for (int i = start + 1; i < currentParent.getChildCount(); i++) {
                Rect viewRect = new Rect();
                view.getGlobalVisibleRect(viewRect);
                View otherView = currentParent.getChildAt(i);
                Rect otherViewRect = new Rect();
                otherView.getGlobalVisibleRect(otherViewRect);
                if (Rect.intersects(viewRect, otherViewRect))//if view intersects its older brother(covered),return true
                    return true;
            }
            currentView = currentParent;
        }
        return false;
    }


    public static int indexOfViewInParent(View view, ViewGroup parent) {
        int index;
        for (index = 0; index < parent.getChildCount(); index++) {
            if (parent.getChildAt(index) == view)
                break;
        }
        return index;
    }

}
