package com.example.lib_netword.network;

import android.app.Activity;
import android.net.ParseException;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.lib_netword.bean.BaseResponeBean;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public  abstract class HttpObserver<T extends BaseResponeBean> implements io.reactivex.Observer<T> {
      private Activity activity;
    private boolean isAddInStop = false;
    public HttpObserver(Activity activity) {
        this.activity = activity;
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
      if (HttpContants.SUCCESS.equals(t.status)){
          onSuccess(t);
      }else {
          onFaile(t);
      }
    }

    public  void onFaile(T t){
        String message = t.message;
        ToastUtils.showShort(message);

    }

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
