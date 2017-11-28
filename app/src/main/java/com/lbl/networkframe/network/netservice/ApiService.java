package com.lbl.networkframe.network.netservice;

import com.lbl.networkframe.bean.SearchDataList;
import com.lbl.networkframe.network.bean.CheckUpdateBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * author：libilang
 * time: 17/11/1 10:19
 * 邮箱：libi_lang@163.com
 * 每一个接口都对应着后台的一个微服务  找一些开发的接口耍一耍
 */

public interface ApiService {

    @GET("user/count_info")
    Observable<CheckUpdateBean> checkAppUpdate(@Url String url, @Query("ver") String loaclversion);

    @GET("/v1/resource/search/{searchname}?suggest=true")
    Observable<SearchDataList.DataBean> getSearchVideo(@Path("searchname") String searchname, @Query("rank") String rank, @Query("size") String size);

}
