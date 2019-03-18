package com.example.dong.wd_xiangyi.contract;

import com.example.dong.wd_xiangyi.model.Model;
import com.example.dong.wd_xiangyi.net.RequestCallback;

import com.example.lib_core.mvp.UModel;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_core.mvp.UView;

import java.util.HashMap;

public interface Contract {
        abstract  class  presenterContract extends UPresenter<IModel,IView>{
            @Override
            public IModel getModel() {
                return new Model();
            }
            public abstract  void LadingPresenter(HashMap<String, String> params);
        }

    interface  IModel extends UModel {
       void  LoadingModel(HashMap<String,String> params , RequestCallback callback);
    }
    interface  IView extends UView {
        void Success(Object o);
    }

}
