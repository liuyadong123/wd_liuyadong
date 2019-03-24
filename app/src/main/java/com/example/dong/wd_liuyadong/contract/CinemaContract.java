package com.example.dong.wd_liuyadong.contract;

import com.example.dong.wd_liuyadong.model.CinmaModel;
import com.example.dong.wd_liuyadong.net.RequestCallbacks;
import com.example.lib_core.mvp.BModel;
import com.example.lib_core.mvp.BPresenter;
import com.example.lib_core.mvp.BView;

import java.util.HashMap;

import okhttp3.MultipartBody;

public interface CinemaContract {
    abstract  class  CinemaPresenter extends BPresenter<CModle,CView>{
        @Override
        public CModle getModel() {
            return new CinmaModel();
        }
        public abstract void TuiJIan(HashMap<String,String> params);
        public abstract void Fu(HashMap<String,String> params);
        public abstract void shang(MultipartBody.Part part);
    }
    interface CModle extends BModel {
        void TuiJIanModel(HashMap<String,String> params, RequestCallbacks callback);
        void FuModel(HashMap<String,String> params, RequestCallbacks callback);
        void shangModel(MultipartBody.Part part, RequestCallbacks callback);
    }
    interface CView extends BView {
        void CinemaSucceess(Object o);
    }

}
