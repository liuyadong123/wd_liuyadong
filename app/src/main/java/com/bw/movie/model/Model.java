package com.bw.movie.model;

import com.bw.movie.bean.LadingBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.net.RequestCallback;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.lang.annotation.Retention;
import java.util.HashMap;

public class Model implements Contract.IModel {
    @Override
    public void LoadingModel(HashMap<String, String> params, RequestCallback callback) {

    }
}
