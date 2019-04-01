package com.bw.movie.model;

import com.bw.movie.api.UserApi;
import com.bw.movie.bean.XinxiBean;
import com.bw.movie.contract.MyContract;
import com.bw.movie.net.RequestCallbacks;
import com.bw.movie.net.RxRetrofitView;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

public class MyModel implements MyContract.MModel {
    @Override
    public void XingModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .xin(UserApi.Xingxi_Api,params)
                .compose(RxUtils.<XinxiBean>schdulers())
                .subscribe(new Consumer<XinxiBean>() {
                    @Override
                    public void accept(XinxiBean xinxiBean) throws Exception {
                        callback.OnSuccess(xinxiBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("没网了");
                    }
                });

    }
}
