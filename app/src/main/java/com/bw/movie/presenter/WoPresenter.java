package com.bw.movie.presenter;

import com.bw.movie.contract.WoContract;
import com.bw.movie.model.WoModel;
import com.bw.movie.net.RequestCallbacks;

import java.util.HashMap;

import okhttp3.MultipartBody;

public class WoPresenter extends WoContract.WoPresenter {
    private WoModel model;
    private WoContract.WoView view;
    public WoPresenter(WoContract.WoView view){
     model =new WoModel();
     this.view=view;
    }
    @Override
    public void Shang(MultipartBody.Part part) {
        model.ShangModel(part, new RequestCallbacks() {
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
    public void Geng(HashMap<String, String> params) {
        model.GengModel(params, new RequestCallbacks() {
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
    public void Xing(HashMap<String, String> params) {
        model.XingModel(params, new RequestCallbacks() {
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
    public void Qian(HashMap<String, String> params) {
        model.QianModel(params, new RequestCallbacks() {
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
