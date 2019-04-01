package com.bw.movie.presenter;

import com.bw.movie.contract.MyContract;
import com.bw.movie.net.RequestCallbacks;

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
