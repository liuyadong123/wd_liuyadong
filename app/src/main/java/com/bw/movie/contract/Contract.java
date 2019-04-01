package com.bw.movie.contract;

import com.bw.movie.bean.LadingBean;
import com.bw.movie.model.Model;
import com.bw.movie.net.RequestCallback;

import com.example.lib_core.mvp.BModel;
import com.example.lib_core.mvp.BPresenter;
import com.example.lib_core.mvp.UActivity;
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
