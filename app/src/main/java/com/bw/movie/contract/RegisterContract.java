package com.bw.movie.contract;

import com.bw.movie.bean.LadingBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.model.RegisterModel;
import com.bw.movie.net.RequestCallback;
import com.example.lib_core.mvp.UModel;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_core.mvp.UView;

import java.util.HashMap;

public interface RegisterContract {
    abstract  class  Contract extends UPresenter<LModel,LView> {
        @Override
        public LModel getModel() {
            return new RegisterModel();
        }

        public abstract  void Register(HashMap<String,String> params);
    }

    interface  LModel extends UModel {
        void RegisterModel(HashMap<String,String> params, RequestCallback callback);
    }
    interface  LView extends UView {
        void RegisterSuccess(RegisterBean registerBean);
    }
}
