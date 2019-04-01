package com.bw.movie.net;

import com.bw.movie.bean.GengBean;
import com.bw.movie.bean.GuanBean;
import com.bw.movie.bean.LobbyInfo;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.QianBean;
import com.bw.movie.bean.QuXiaoBean;
import com.bw.movie.bean.ReMenBean;
import com.bw.movie.bean.ShangBean;
import com.bw.movie.bean.WeiLoginInfo;
import com.bw.movie.bean.XiadainBean;
import com.bw.movie.bean.XiangBean;
import com.bw.movie.bean.XinxiBean;
import com.bw.movie.bean.YingPing;
import com.bw.movie.bean.ZfbBean;
import com.bw.movie.bean.ZhiFuBean;

import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RxRetrofitView {
     @POST
    @FormUrlEncoded
    Observable<ResponseBody> dopost(@Url String url, @FieldMap HashMap<String,String> prams);

    @POST
    @FormUrlEncoded
    Observable<XiadainBean> doposts(@Url String url, @FieldMap HashMap<String,String> prams);

    @POST
    @FormUrlEncoded
    Observable<ZhiFuBean> zhifu(@Url String url, @FieldMap HashMap<String,String> prams);
    @POST
    @FormUrlEncoded
    Observable<ZfbBean>wx(@Url String url, @FieldMap HashMap<String,String> prams);

     @GET
    Observable<ReMenBean> doget(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<GengBean> geng(@Url String url, @HeaderMap HashMap<String,String> params);

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
    @GET
    Observable<LobbyInfo> loabby(@Url String url, @QueryMap HashMap<String,String> prams);
    @GET
    Observable<QianBean> qian(@Url String url, @QueryMap HashMap<String,String> prams);

    @POST
    @Multipart
    Observable<ShangBean> ed(@Url String url, @Part MultipartBody.Part part);

    @POST
    @FormUrlEncoded
    Observable<WeiLoginInfo> wxs(@Url String url,@FieldMap HashMap<String,String> paras);
}
