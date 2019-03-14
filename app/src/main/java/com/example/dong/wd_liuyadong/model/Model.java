package com.example.dong.wd_liuyadong.model;

import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.contract.Contract;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.lang.annotation.Retention;
import java.util.HashMap;

public class Model implements Contract.IModel {
    @Override
    public void LoadingModel(HashMap<String, String> params, RequestCallback callback) {

    }
}
