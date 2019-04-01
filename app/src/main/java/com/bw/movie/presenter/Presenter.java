package com.bw.movie.presenter;

import com.bw.movie.contract.Contract;
import com.bw.movie.net.RequestCallback;

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
