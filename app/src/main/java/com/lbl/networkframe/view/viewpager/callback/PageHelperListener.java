package com.lbl.networkframe.view.viewpager.callback;

import android.view.View;

/**
 * author：libilang
 * time: 2017/11/9
 * 邮箱：libi_lang@163.com
 */
public interface PageHelperListener<T> {
    void getItemView(View view, T data);
}
