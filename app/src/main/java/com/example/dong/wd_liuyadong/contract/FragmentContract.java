package com.example.dong.wd_liuyadong.contract;

import com.example.dong.wd_liuyadong.model.FragmentModel;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BModel;
import com.example.lib_core.mvp.BPresenter;
import com.example.lib_core.mvp.BView;

import java.util.HashMap;

public interface FragmentContract {
    abstract  class  FragmentPresenter extends BPresenter<FModel,FView> {
        @Override
        public FModel getModel() {
            return new FragmentModel();
        }
       public abstract void ReMen(HashMap<String,String> params);
        public abstract void Zheng(HashMap<String,String> params);
        public abstract void Ji(HashMap<String,String> params);
        public abstract void Guan(HashMap<String,String> params);
        public abstract void Qu(HashMap<String,String> params);

    }
    interface  FModel extends BModel {
        void ReMenModel(HashMap<String,String> params, RequestCallbacks callback);
        void ZhengModel(HashMap<String,String> params, RequestCallbacks callback);
        void JiModel(HashMap<String,String> params, RequestCallbacks callback);
        void GuanModel(HashMap<String,String> params, RequestCallbacks callback);
        void QuModel(HashMap<String,String> params, RequestCallbacks callback);


    }
    interface  FView extends BView {
        void  Success(Object o);
        void Zhengzai(Object o);
        void Jijiang(Object o);
        void Guanzhu(Object o);
    }
}
