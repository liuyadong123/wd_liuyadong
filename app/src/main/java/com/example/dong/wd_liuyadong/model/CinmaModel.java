package com.example.dong.wd_liuyadong.model;

import com.example.dong.wd_liuyadong.api.UserApi;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.bean.ShangBean;
import com.example.dong.wd_liuyadong.contract.CinemaContract;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.example.dong.wd_liuyadong.net.RxRetrofitView;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;

public class CinmaModel implements CinemaContract.CModle {
    @Override
    public void TuiJIanModel(HashMap<String, String> params, final RequestCallbacks callback) {
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
                         callback.Failure("网络开小差了");
                    }
                });
    }

    @Override
    public void FuModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .tui(UserApi.Fu_Api,params)
                .compose(RxUtils.<MovieBean>schdulers())
                .subscribe(new Consumer<MovieBean>() {
                    @Override
                    public void accept(MovieBean movieBean) throws Exception {
                        callback.OnSuccess(movieBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("网络开小差了");
                    }
                });
    }

    @Override
    public void shangModel(MultipartBody.Part part, final RequestCallbacks callback) {
         RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                 .shang(UserApi.Shang_Api,part)
                 .compose(RxUtils.<ShangBean>schdulers())
                 .subscribe(new Consumer<ShangBean>() {
                     @Override
                     public void accept(ShangBean shangBean) throws Exception {
                         callback.OnSuccess(shangBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                         callback.Failure("没网了东哥");
                     }
                 });
    }
}
