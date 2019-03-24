package com.example.dong.wd_liuyadong.presenter;

import com.example.dong.wd_liuyadong.contract.CinemaContract;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;

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

    @Override
    public void shang(MultipartBody.Part part) {
         model.shangModel(part, new RequestCallbacks() {
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
