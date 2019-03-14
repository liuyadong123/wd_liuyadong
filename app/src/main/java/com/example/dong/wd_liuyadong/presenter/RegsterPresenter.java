package com.example.dong.wd_liuyadong.presenter;

import com.example.dong.wd_liuyadong.bean.RegisterBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.contract.RegisterContract;
import com.example.dong.wd_liuyadong.net.RequestCallback;
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
