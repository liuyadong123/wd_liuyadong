package com.example.dong.wd_liuyadong.contract;

import com.example.dong.wd_liuyadong.model.MyModel;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.example.lib_core.mvp.UModel;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_core.mvp.UView;

import java.util.HashMap;

public interface MyContract {
    abstract  class  MyPresenter extends UPresenter<MModel,MView> {
        @Override
        public MModel getModel() {
            return new MyModel();
        }
        public abstract void Xing(HashMap<String,String> params);
    }
    interface  MModel extends UModel {
        void XingModel(HashMap<String,String> params, RequestCallbacks callback);

    }
    interface  MView extends UView {
        void mySuccess(Object o);
    }
}
