package com.example.dong.wd_liuyadong.model;

import com.example.dong.wd_liuyadong.api.UserApi;
import com.example.dong.wd_liuyadong.bean.XinxiBean;
import com.example.dong.wd_liuyadong.contract.MyContract;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.example.dong.wd_liuyadong.net.RxRetrofitView;
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
