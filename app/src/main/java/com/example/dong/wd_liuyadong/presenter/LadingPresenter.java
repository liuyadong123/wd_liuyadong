package com.example.dong.wd_liuyadong.presenter;

import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.google.gson.Gson;

import java.util.HashMap;

public class LadingPresenter extends LadingContract.Contract {
    @Override
    public void Lading(HashMap<String, String> params) {
            model.LadingModel(params, new RequestCallback() {
                @Override
                public void Success(String result) {
                    LadingBean ladingBean = new Gson().fromJson(result, LadingBean.class);
                    view.LadingSuccess(ladingBean);

                }

                @Override
                public void Failure(String msg) {
                     view.Failure(msg);
                }
            });
    }

    @Override
    public void huo(String userId, String sessionId) {
        model.huoModel(userId,sessionId);
    }
    @Override
    public void Xiang(HashMap<String, String> params) {
        model.XiangModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }


}
