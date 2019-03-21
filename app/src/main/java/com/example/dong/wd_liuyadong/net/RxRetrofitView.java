package com.example.dong.wd_liuyadong.net;

import com.example.dong.wd_liuyadong.bean.GuanBean;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.bean.QuXiaoBean;
import com.example.dong.wd_liuyadong.bean.ReMenBean;
import com.example.dong.wd_liuyadong.bean.XiangBean;
import com.example.dong.wd_liuyadong.bean.XinxiBean;
import com.example.dong.wd_liuyadong.bean.YingPing;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RxRetrofitView {
     @POST
    @FormUrlEncoded
    Observable<ResponseBody> dopost(@Url String url, @FieldMap HashMap<String,String> prams);

     @GET
    Observable<ReMenBean> doget(@Url String url, @QueryMap HashMap<String,String> prams);

    @GET
    Observable<GuanBean> dogets(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<QuXiaoBean> quxiao(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<XiangBean> xiang(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<MovieBean> tui(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<XinxiBean> xin(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<YingPing> ying(@Url String url, @QueryMap HashMap<String,String> prams);
}
