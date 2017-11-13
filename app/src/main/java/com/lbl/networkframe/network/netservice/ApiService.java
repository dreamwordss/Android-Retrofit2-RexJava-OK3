package com.lbl.networkframe.network.netservice;

import com.lbl.networkframe.network.bean.CheckUpdateBean;
import com.lbl.networkframe.network.bean.GankResp;
import com.lbl.networkframe.network.bean.MeiziInfo;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * author：libilang
 * time: 17/11/1 10:19
 * 邮箱：libi_lang@163.com
 * 每一个接口都对应着后台的一个微服务
 */

public interface ApiService {

    @GET("user/count_info")
    Call<CheckUpdateBean> checkAppUpdate(@Url String url, @Query("ver") String loaclversion);

    //    @GET("/api/data/%E7%A6%8F%E5%88%A9/10/{page}")
    @GET("/api/data/{name}/20/{page}")
    Observable<MeiziInfo> getMeizhiData(@Path("name") String name, @Path("page") int page);

    @FormUrlEncoded
    @POST("/x3/weather")
    Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);

    @GET
    Observable<GankResp> getGank(@Url String url/*, @Path("count")int count,@Path("page")int page*/);

    @GET("/v1/resource/search/{searchname}?suggest=true")
    Observable<Object> getSearchVideo(@Path("searchname") String searchname, @Query("rank") String rank, @Query("size") String size);


}
