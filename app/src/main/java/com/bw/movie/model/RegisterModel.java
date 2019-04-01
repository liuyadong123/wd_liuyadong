package com.bw.movie.model;

import android.annotation.SuppressLint;

import com.bw.movie.api.UserApi;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.contract.RegisterContract;
import com.bw.movie.net.RequestCallback;
import com.bw.movie.net.RxRetrofitView;
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
