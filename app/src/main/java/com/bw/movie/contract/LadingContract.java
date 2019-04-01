package com.bw.movie.contract;

import com.bw.movie.bean.LadingBean;
import com.bw.movie.model.LadingModel;
import com.bw.movie.net.RequestCallback;
import com.bw.movie.net.RequestCallbacks;
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
        public abstract void Xiang(HashMap<String,String> params);
        public abstract void Tui(HashMap<String,String> params);
        public abstract void Ying(HashMap<String,String> params);
        public abstract void Loabby(HashMap<String,String> params);
        public abstract void Xia(HashMap<String,String> params);
        public abstract void Zhi(HashMap<String,String> params);
        public abstract void wx(HashMap<String,String> params);
        public abstract void WeiLogin(HashMap<String,String> params);

    }

    interface  LModel extends UModel {
        void LadingModel(HashMap<String,String> params, RequestCallback callback);
        void huoModel(String userId,String sessionId);
        void XiangModel(HashMap<String,String> params, RequestCallbacks callback);
        void TuiModel(HashMap<String,String> params, RequestCallbacks callback);
        void YingModel(HashMap<String,String> params, RequestCallbacks callback);
        void LoabbyModel(HashMap<String,String> params, RequestCallbacks callback);
        void XiaModel(HashMap<String,String> params, RequestCallbacks callback);
        void ZhiModel(HashMap<String,String> params, RequestCallbacks callback);
        void wxModel(HashMap<String,String> params, RequestCallbacks callback);
        void WeiLoginModel(HashMap<String,String> params, RequestCallbacks callback);



    }
    interface  LView extends UView {
          void LadingSuccess(LadingBean ladingBean);
          void XiangSuccess(Object o);
    }
}
