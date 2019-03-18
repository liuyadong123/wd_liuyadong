package com.example.dong.wd_xiangyi.presenter;

import com.example.dong.wd_xiangyi.contract.Contract;
import com.example.dong.wd_xiangyi.net.RequestCallback;

import java.util.HashMap;

public class Presenter extends Contract.presenterContract {

    @Override
    public void LadingPresenter(HashMap<String, String> params) {
        model.LoadingModel(params, new RequestCallback() {
            @Override
            public void Success(String result) {

            }

            @Override
            public void Failure(String msg) {

            }
        });
    }
}
