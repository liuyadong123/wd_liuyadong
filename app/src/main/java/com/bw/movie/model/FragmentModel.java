package com.bw.movie.model;

import android.annotation.SuppressLint;
import android.view.View;

import com.bw.movie.api.UserApi;
import com.bw.movie.bean.GuanBean;
import com.bw.movie.bean.QuXiaoBean;
import com.bw.movie.bean.ReMenBean;
import com.bw.movie.bean.XiangBean;
import com.bw.movie.contract.FragmentContract;
import com.bw.movie.net.RequestCallback;
import com.bw.movie.net.RequestCallbacks;
import com.bw.movie.net.RxRetrofitView;
import com.example.lib_netword.api.Api;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class FragmentModel implements FragmentContract.FModel {
    @SuppressLint("CheckResult")
    @Override
    public void ReMenModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter()
                .createService(RxRetrofitView.class)
                .doget(UserApi.ReMen_Api,params)
                .compose(RxUtils.<ReMenBean>schdulers())
                .subscribe(new Consumer<ReMenBean>() {
                    @Override
                    public void accept(ReMenBean reMenBean) throws Exception {
                        if (callback!=null){
                            callback.OnSuccess(reMenBean);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("网络开小差了");
                    }
                });
    }

    @Override
    public void ZhengModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .doget(UserApi.ZhengZai_Api,params)
                .compose(RxUtils.<ReMenBean>schdulers())
                .subscribe(new Consumer<ReMenBean>() {
                    @Override
                    public void accept(ReMenBean reMenBean) throws Exception {
                        callback.OnSuccess(reMenBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("网络开小差了");
                    }
                });
    }

    @Override
    public void JiModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .doget(UserApi.JiJiang_Api,params)
                .compose(RxUtils.<ReMenBean>schdulers())
                .subscribe(new Consumer<ReMenBean>() {
                    @Override
                    public void accept(ReMenBean reMenBean) throws Exception {
                        callback.OnSuccess(reMenBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("网络开小差了");
                    }
                });
    }

    @Override
    public void GuanModel(HashMap<String, String> params, final RequestCallbacks callback) {
         RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                 .dogets(UserApi.Guanzhu_Api,params)
                 .compose(RxUtils.<GuanBean>schdulers())
                 .subscribe(new Consumer<GuanBean>() {
                     @Override
                     public void accept(GuanBean guanBean) throws Exception {
                         callback.OnSuccess(guanBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                          callback.Failure("开小差了");
                     }
                 });
    }

    @Override
    public void QuModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .quxiao(UserApi.Quxiao_Api,params)
                .compose(RxUtils.<QuXiaoBean>schdulers())
                .subscribe(new Consumer<QuXiaoBean>() {
                    @Override
                    public void accept(QuXiaoBean quXiaoBean) throws Exception {
                       callback.OnSuccess(quXiaoBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("开小差了");
                    }
                });

    }




}
