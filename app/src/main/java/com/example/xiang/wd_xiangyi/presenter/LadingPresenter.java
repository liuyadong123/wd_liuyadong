package com.example.dong.wd_xiangyi.presenter;

import com.example.dong.wd_xiangyi.bean.LadingBean;
import com.example.dong.wd_xiangyi.contract.LadingContract;
import com.example.dong.wd_xiangyi.net.RequestCallback;
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


}
