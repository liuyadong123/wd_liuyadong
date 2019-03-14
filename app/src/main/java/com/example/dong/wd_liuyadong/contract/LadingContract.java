package com.example.dong.wd_liuyadong.contract;

import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.model.LadingModel;
import com.example.dong.wd_liuyadong.net.RequestCallback;
import com.example.lib_core.mvp.UModel;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_core.mvp.UView;

import java.util.HashMap;

public interface LadingContract {
    abstract  class  Contract extends UPresenter<LModel,LView>{
        @Override
        public LModel getModel() {
            return new LadingModel();
        }
        public abstract  void Lading(HashMap<String,String> params);
        public abstract  void huo(String userId,String sessionId);
    }

    interface  LModel extends UModel {
        void LadingModel(HashMap<String,String> params, RequestCallback callback);
        void huoModel(String userId,String sessionId);
    }
    interface  LView extends UView {
          void LadingSuccess(LadingBean ladingBean);
    }
}
