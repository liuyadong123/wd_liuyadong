package com.bw.movie.contract;

import com.bw.movie.model.CinmaModel;
import com.bw.movie.net.RequestCallbacks;
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
    }
    interface CModle extends BModel {
        void TuiJIanModel(HashMap<String,String> params, RequestCallbacks callback);
        void FuModel(HashMap<String,String> params, RequestCallbacks callback);

    }
    interface CView extends BView {
        void CinemaSucceess(Object o);
    }

}
