package com.bw.movie.model;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.view.View;

import com.blankj.utilcode.util.SPUtils;
import com.bw.movie.api.UserApi;
import com.bw.movie.bean.LobbyInfo;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.ReMenBean;
import com.bw.movie.bean.WeiLoginInfo;
import com.bw.movie.bean.XiadainBean;
import com.bw.movie.bean.XiangBean;
import com.bw.movie.bean.YingPing;
import com.bw.movie.bean.ZfbBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.net.RequestCallback;
import com.bw.movie.net.RequestCallbacks;
import com.bw.movie.net.RxRetrofitView;
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

    @Override
    public void LoabbyModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .loabby(UserApi.Loabb_Api,params)
                .compose(RxUtils.<LobbyInfo>schdulers())
                .subscribe(new Consumer<LobbyInfo>() {
                    @Override
                    public void accept(LobbyInfo lobbyInfo) throws Exception {
                       callback.OnSuccess(lobbyInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (callback!=null){
                            callback.Failure("没网了aaaaaa");
                        }

                    }
                });
    }

    @Override
    public void XiaModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .doposts(UserApi.XiaDan_Api,params)
                .compose(RxUtils.<XiadainBean>schdulers())
                .subscribe(new Consumer<XiadainBean>() {
                    @Override
                    public void accept(XiadainBean xiadainBean) throws Exception {
                        callback.OnSuccess(xiadainBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("哈哈a");
                    }
                });
    }

    @Override
    public void ZhiModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .zhifu(UserApi.Zhifu_Api,params)
                .compose(RxUtils.<ZhiFuBean>schdulers())
                .subscribe(new Consumer<ZhiFuBean>() {
                    @Override
                    public void accept(ZhiFuBean zhiFuBean) throws Exception {
                         callback.OnSuccess(zhiFuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                         callback.Failure("hahah");
                    }
                });
    }

    @Override
    public void wxModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .wx(UserApi.Zhifu_Api,params)
                .compose(RxUtils.<ZfbBean>schdulers())
                .subscribe(new Consumer<ZfbBean>() {
                    @Override
                    public void accept(ZfbBean zhiFuBean) throws Exception {
                        callback.OnSuccess(zhiFuBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("hahah");
                    }
                });
    }

    @Override
    public void WeiLoginModel(HashMap<String, String> params, RequestCallbacks callback) {
         RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                 .wxs(UserApi.WWXX_Api,params)
                 .compose(RxUtils.<WeiLoginInfo>schdulers())
                 .subscribe(new Consumer<WeiLoginInfo>() {
                     @Override
                     public void accept(WeiLoginInfo weiLoginInfo) throws Exception {
                         callback.OnSuccess(weiLoginInfo);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                         callback.Failure("请求失败");
                     }
                 });
    }


}
