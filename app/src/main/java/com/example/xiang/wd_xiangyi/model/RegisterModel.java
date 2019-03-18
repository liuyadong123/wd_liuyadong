package com.example.dong.wd_xiangyi.model;

import android.annotation.SuppressLint;

import com.example.dong.wd_xiangyi.api.UserApi;
import com.example.dong.wd_xiangyi.contract.RegisterContract;
import com.example.dong.wd_xiangyi.net.RequestCallback;
import com.example.dong.wd_xiangyi.net.RxRetrofitView;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class RegisterModel implements RegisterContract.LModel {

    @SuppressLint("CheckResult")
    @Override
    public void RegisterModel(HashMap<String, String> params, final RequestCallback callback) {
              RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                      .dopost(UserApi.Register_Api,params)
                      .compose(RxUtils.<ResponseBody>schdulers())
                      .subscribe(new Consumer<ResponseBody>() {
                          @Override
                          public void accept(ResponseBody responseBody) throws Exception {
                              if (callback!=null){
                                  String string = responseBody.string();
                                  callback.Success(string);
                              }

                          }
                      }, new Consumer<Throwable>() {
                          @Override
                          public void accept(Throwable throwable) throws Exception {
                              if (callback!=null){
                                  callback.Failure("网络开小差了");
                              }
                          }
                      });


    }
}
