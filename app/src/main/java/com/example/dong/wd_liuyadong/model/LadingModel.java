package com.example.dong.wd_liuyadong.model;

import android.annotation.SuppressLint;

import com.example.dong.wd_liuyadong.api.UserApi;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.dong.wd_liuyadong.net.RxRetrofitView;
import com.example.lib_netword.api.Api;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;
import com.example.lib_netword.utils.SpUtils;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.http.Url;

public class LadingModel implements LadingContract.LModel {
    @SuppressLint("CheckResult")
    @Override
    public void LadingModel(HashMap<String, String> params, final RequestCallback callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .dopost(UserApi.Lading_Api,params)
                .compose(RxUtils.<ResponseBody>schdulers())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                            if (callback!=null){
                                String string = responseBody.string();
                                callback.Success(string);
                            }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                          if (callback!=null){
                              callback.Failure("网络开小差了");
                          }
                    }
                });
    }

    @Override
    public void huoModel(String userId, String sessionId) {
        SpUtils.getInternsp().putSp(userId,sessionId);
    }
}
