package com.example.dong.wd_liuyadong.model;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.example.dong.wd_liuyadong.api.UserApi;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.bean.XiangBean;
import com.example.dong.wd_liuyadong.bean.YingPing;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
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
        SpUtils.getInternsp().putSp("userId",userId);
        SpUtils.getInternsp().putSp("sessionId",sessionId);

    }
    @Override
    public void XiangModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .xiang(UserApi.Xiang_Api,params)
                .compose(RxUtils.<XiangBean>schdulers())
                .subscribe(new Consumer<XiangBean>() {
                    @Override
                    public void accept(XiangBean xiangBean) throws Exception {
                        callback.OnSuccess(xiangBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("开小差了");
                    }
                });
    }

    @Override
    public void TuiModel(HashMap<String, String> params, final RequestCallbacks callback) {
         RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                 .tui(UserApi.Tui_Api,params)
                 .compose(RxUtils.<MovieBean>schdulers())
                 .subscribe(new Consumer<MovieBean>() {
                     @Override
                     public void accept(MovieBean movieBean) throws Exception {
                             callback.OnSuccess(movieBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                         callback.Failure("开小差了");
                     }
                 });
    }

    @Override
    public void YingModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .ying(UserApi.YingPing_Api,params)
                .compose(RxUtils.<YingPing>schdulers())
                .subscribe(new Consumer<YingPing>() {
                    @Override
                    public void accept(YingPing yingPing) throws Exception {
                        callback.OnSuccess(yingPing);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("没网了啊");
                    }
                });
    }


}
