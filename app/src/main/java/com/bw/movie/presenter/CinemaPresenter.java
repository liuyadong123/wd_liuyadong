package com.bw.movie.presenter;

import com.bw.movie.contract.CinemaContract;
import com.bw.movie.fragment.MyFragment;
import com.bw.movie.net.RequestCallbacks;

import java.util.HashMap;

import okhttp3.MultipartBody;

public class CinemaPresenter extends CinemaContract.CinemaPresenter {



    @Override
    public void TuiJIan(HashMap<String, String> params) {
        model.TuiJIanModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.CinemaSucceess(o);
            }

            @Override
            public void Failure(String msg) {
           view.Failure(msg);
            }
        });
    }

    @Override
    public void Fu(HashMap<String, String> params) {
        model.FuModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.CinemaSucceess(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }


}
