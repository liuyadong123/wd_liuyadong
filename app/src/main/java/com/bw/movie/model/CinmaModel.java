package com.bw.movie.model;

import com.bw.movie.api.UserApi;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.ShangBean;
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.net.RequestCallbacks;
import com.bw.movie.net.RxRetrofitView;
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


}
