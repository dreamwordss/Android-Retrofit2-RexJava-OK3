package com.lbl.networkframe.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.lbl.networkframe.ConstantS;
import com.lbl.networkframe.MainActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕捉
 * author：libilang
 * time: 16/7/25 16:51
 * 邮箱：libi_lang@163.com
 */
public class MyCrashHandler implements UncaughtExceptionHandler {

    // 整个应用程序 只有一个 MyCrash-Handler
    private static MyCrashHandler myCrashHandler;
    private Context context;
    @SuppressWarnings("unused")
    private SimpleDateFormat dataFormat = new SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss");
    public Map<String, String> messageinfo = new HashMap<String, String>();// 用来存储设备信息和异常信息

    // 1.私有化构造方法
    private MyCrashHandler() {

    }

    // 2. 保证单例模式
    public static synchronized MyCrashHandler getInstance() {
        if (myCrashHandler != null) {
            return myCrashHandler;
        } else {
            myCrashHandler = new MyCrashHandler();
            return myCrashHandler;
        }
    }

    public void init(Context context) {
        this.context = context;
    }

    /*
     * 程序异常的处理方法
     *
     * @see
     * java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang
     * .Thread, java.lang.Throwable)
     */
    public void uncaughtException(Thread arg0, Throwable arg1) {
        // System.out.println("程序挂掉了 ");
        // 1.获取当前程序的版本号. 版本的id
        @SuppressWarnings("unused")
        String versioninfo = getVersionInfo();
        messageinfo.put("versioninfo", versioninfo);
        // 2.获取手机的硬件信息.
        @SuppressWarnings("unused")
        String mobileInfo = "";
//        = getMobileInfo();
//        messageinfo.put("mobileInfo", mobileInfo);
        // 3.把错误的堆栈信息 获取出来
        @SuppressWarnings("unused")
        String errorinfo = "";
//        getErrorInfo(arg1);
        // 4.把所有的信息 还有信息对应的时间 提交到服务器
        try {
            // System.out.println("捕获到全局异常啦");
            arg1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveErrorLog(arg1);
        /**
         * 在用户许可下,将异常信息上传到网络.
         */
        int crashSize = SPUtil.getInt(context, ConstantS.CRASH_SIZE, 0);
        crashSize++;
        SPUtil.putInt(context, ConstantS.CRASH_SIZE + "", crashSize);
        if (crashSize < 3) {
            // 重新启动
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            System.exit(0);
        } else {
            SPUtil.putInt(context, ConstantS.CRASH_SIZE, 0);
            // 0 表示正常退出 1表示强制退出
            System.exit(0);
        }
        // 干掉当前的程序
        // android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取错误的信息
     *
     * @param arg1
     * @return
     */
    private String getErrorInfo(Throwable arg1) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        arg1.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }

    /**
     * 获取手机的硬件信息
     *
     * @return
     */
    public String getMobileInfo() {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    /**
     * 获取手机的版本信息
     *
     * @return
     */
    private String getVersionInfo() {
        String verName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            verName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "版本号未知";
        }
        return verName;
    }

    /**
     * 保存异常日志
     *
     * @param excp
     */
    public void saveErrorLog(Throwable excp) {
        String fileName = "xiaohongchun.log";
        String logFilePath = "";
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            // 判断是否挂载了SD卡
            String storageState = Environment.getExternalStorageState();
            if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                String logPath = Environment.getExternalStorageDirectory()
                        + "/encodeTmp/log";
                File file = new File(logPath);
                if (file.exists() && file.isDirectory()) {
                } else {
                    file.mkdirs();
                }
                logFilePath = logPath + File.separator + fileName;
            }
            // 没有挂载SD卡，无法写文件
            if ("".equals(logFilePath)) {
                return;
            }
            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.println("\r\n\r\n\r\n---------------"
                    + (new Date().toLocaleString()) + "**********Android版本号:"
                    + messageinfo.get("versioninfo") + "-----硬件信息:" +
                    ViewUtil.getMobileInfo(context) + "\n--------网络信息:\n"
                    + ViewUtil.getWifiDNSInfo(context) + "------------------");
            excp.printStackTrace(pw);
            pw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                }
            }
        }
//        ViewUtil.upLoadXHClogFile(false, "");//上传文件
    }
}
