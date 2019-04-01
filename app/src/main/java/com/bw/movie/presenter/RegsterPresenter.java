package com.bw.movie.presenter;

import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class RegsterPresenter extends RegisterContract.Contract {


    @Override
    public void Register(HashMap<String, String> params) {
           model.RegisterModel(params, new RequestCallback() {
               @Override
               public void Success(String result) {
                   RegisterBean registerBean = new Gson().fromJson(result, RegisterBean.class);
                   view.RegisterSuccess(registerBean);

               }

               @Override
               public void Failure(String msg) {
                  view.Failure(msg);
               }
           });
    }
}
