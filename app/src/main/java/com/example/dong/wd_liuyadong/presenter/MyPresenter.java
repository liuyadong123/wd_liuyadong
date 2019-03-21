package com.example.dong.wd_liuyadong.presenter;

import com.example.dong.wd_liuyadong.contract.MyContract;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;

import java.util.HashMap;

public class MyPresenter extends MyContract.MyPresenter {
    @Override
    public void Xing(HashMap<String, String> params) {
        model.XingModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.mySuccess(o);
            }

            @Override
            public void Failure(String msg) {
              view.Failure(msg);
            }
        });
    }
}
