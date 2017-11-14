package com.lbl.networkframe.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * author：libilang
 * time: 2017/10/125
 * 邮箱：libi_lang@163.com
 */

public class ViewPagerHelperUtils {
    public static final int LOOP_COUNT = 5000;
    public static final int LOOP_TAIL_MODE = 0x1001;

    public static final int LOOP_MODE = 0x1002;
    public static final int GLIDE_MODE = 0x1002;

    public static final int VIEWPAGER_DATA_URL = 0x2002;
    public static final int VIEWPAGER_DATA_RES = 0x2003;
    public static final int VIEWPAGER_DATA_VIEW = 0x2004;



    /**
     * 设置viewpager 之间的切换速度
     */
    public static void initSwitchTime(Context context,ViewPager viewPager, int time){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager,new ViewPagerScroller(context,time));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ViewPagerScroller extends Scroller {
        int time;
        public ViewPagerScroller(Context context, int time) {
            super(context);
            this.time = time;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, time);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, time);
        }
    }

}
