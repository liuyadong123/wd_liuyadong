package com.example.dong.wd_liuyadong.presenter;

import com.example.dong.wd_liuyadong.bean.ReMenBean;
import com.example.dong.wd_liuyadong.contract.FragmentContract;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.google.gson.Gson;

import java.util.HashMap;

public class FragmentPresenter extends FragmentContract.FragmentPresenter {
    @Override
    public void ReMen(HashMap<String, String> params) {
         if (model!=null){
             model.ReMenModel(params, new RequestCallbacks() {
                 @Override
                 public void OnSuccess(Object o) {
                     if (view!=null){
                         view.Success(o);
                     }
                 }

                 @Override
                 public void Failure(String msg) {
                     if (view!=null){
                         view.Failure(msg);
                     }
                 }
             });
         }
    }

    @Override
    public void Zheng(HashMap<String, String> params) {
        if (model!=null){
            model.ZhengModel(params, new RequestCallbacks() {
                @Override
                public void OnSuccess(Object o) {
                    if (view!=null){
                        view.Zhengzai(o);
                    }
                }

                @Override
                public void Failure(String msg) {
                    if (view!=null){
                        view.Failure(msg);
                    }
                }
            });
        }
    }

    @Override
    public void Ji(HashMap<String, String> params) {
        if (model!=null){
            model.JiModel(params, new RequestCallbacks() {
                @Override
                public void OnSuccess(Object o) {
                    if (view!=null){
                        view.Jijiang(o);
                    }
                }

                @Override
                public void Failure(String msg) {
                    if (view!=null){
                        view.Failure(msg);
                    }
                }
            });
        }
    }

    @Override
    public void Guan(HashMap<String, String> params) {
         model.GuanModel(params, new RequestCallbacks() {
             @Override
             public void OnSuccess(Object o) {
                 view.Guanzhu(o);
             }

             @Override
             public void Failure(String msg) {
                  view.Failure(msg);
             }
         });
    }

    @Override
    public void Qu(HashMap<String, String> params) {
        model.QuModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.Guanzhu(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }


}
