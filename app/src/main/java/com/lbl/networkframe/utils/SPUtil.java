package com.lbl.networkframe.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lbl.networkframe.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * author：libilang
 * time: 16/7/12 18:37
 * 邮箱：libi_lang@163.com
 * sp工具类
 */
public class SPUtil {
    public final static String spName = "Xhc_SpUtil";

    public static SharedPreferences sp = null;

    /**
     * 第一次运行程序, 进入引导页
     */
    public static final String ISFIRSTIN = "isFirstIn";

    /**
     * 记录历史的app版本号
     */
    public static final String HISTORYVERSION = "historyVersion";

    /**
     * 标记用户的直播权限
     */
    public static final String USERLIVING_STATE = "userliving_state";

    /**
     * @param c
     * @param name 标记用户第一次打开
     */
    public static final String FITST_CLICK_VIDEO = "400-176-0066";

    public static final String MEMBER_S = "";

    /**
     * 标记客服联系电话
     */
    public static final String XHC_CUSTOMER_PHOTONUM = "";
    /**
     * 标记会员
     */
    public static final String XHC_MEMBER_STATE = "0";
    /**
     * 会员个人地址
     */
    public static final String XHC_PERSON_MEMBER = "xhc_person_member";
    /**
     * 会员个人收益地址
     */
    public static final String XHC_PERSON_MEMBER_INCOME = "xhc_person_member_income";
    /**
     * 会员邀请好友TITLE
     */
    public static final String XHC_PERSON_MEMBER_INVITATION_TITLE = "xhc_person_member_income_title";
    /**
     * 会员邀请好友
     */
    public static final String XHC_PERSON_MEMBER_INVITATION = "xhc_person_member_invitation";

    /**
     * 部分达人视频是否可以直传视频
     */
    public static final String XHC_MASTER_CANPUSH_VIDEO = "xhc_master_canpush_video";

    /**
     * 部分达人视频可以直传视频的长度
     */
    public static final String XHC_MASTER_CANPUSH_VIDEOLENTH = "xhc_master_canpush_videolenth";

    private static void init(Context c, String name) {
        sp = c.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static boolean putBoolean(Context c, String key, boolean value) {
        init(c, spName);
        boolean commit = sp.edit().putBoolean(key, value).commit();
        return commit;
    }

    public static boolean getBoolean(Context c, String key, boolean defValue) {
        init(c, spName);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public static boolean putInt(Context c, String key, int value) {
        init(c, spName);
        boolean commit = sp.edit().putInt(key, value).commit();
        return commit;
    }

    public static int getInt(Context c, String key, int defValue) {
        init(c, spName);
        return sp.getInt(key, defValue);
    }

    public static boolean putLong(Context c, String key, long value) {
        init(c, spName);
        boolean commit = sp.edit().putLong(key, value).commit();
        return commit;
    }

    public static long getLong(Context c, String key, long defValue) {
        init(c, spName);
        return sp.getLong(key, defValue);
    }

    public static boolean putString(Context c, String key, String value) {
        init(c, spName);
        boolean commit = sp.edit().putString(key, value).commit();
        return commit;
    }

    public static String getString(Context c, String key, String defValue) {
        init(c, spName);
        String value = sp.getString(key, defValue);
        return value;
    }

    //sp存储listMap
    public static void putListMap(Context c, String name, String key, List<Map<String, String>> datas) {
        sp = c.getSharedPreferences(name, Context.MODE_PRIVATE);
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();

            JSONObject object = new JSONObject();

            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mJsonArray.put(object);
        }

        SharedPreferences sp = c.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    //获取sp中的数据
    public static List<Map<String, String>> getListMap(Context c, String Name, String key) {
        List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
        sp = c.getSharedPreferences(Name, Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<String, String>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return datas;
    }

    //sp存储listString数据
    public static void putList(Context c, String name, String key, List<String> list) {
        sp = c.getSharedPreferences(name, Context.MODE_PRIVATE);
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            mJsonArray.put(item);
        }
        SharedPreferences sp = c.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    //sp获取listString数据
    public static List<String> getList(Context c, String Name, String key) {
        List<String> list = new ArrayList<String>();
        sp = c.getSharedPreferences(Name, Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                String item = (String) array.get(i);
                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //清除单一对象的sp
    public static boolean clearAll(Context c) {
        init(c, spName);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return editor.commit();
    }

    //清除全部的sp
    public static boolean clearAllSP(Context c, String spname) {
        init(c, spname);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        return editor.commit();
    }


    /**
     * baseapplication里边的sharedprefence
     */
    public static SharedPreferences getApplicationSharedPreferences(Context context) {
        return context.getSharedPreferences(MyApplication.CONFIG_INF, Context.MODE_PRIVATE);
    }

}
