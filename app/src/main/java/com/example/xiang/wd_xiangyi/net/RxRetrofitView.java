package com.example.dong.wd_xiangyi.net;

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
    Observable<ResponseBody> doget(@Url String url, @QueryMap HashMap<String,String> prams);
}
