package com.bw.movie.contract;

import com.bw.movie.net.RequestCallbacks;
import com.example.lib_core.mvp.BModel;
import com.example.lib_core.mvp.BView;

import java.util.HashMap;

import okhttp3.MultipartBody;

public interface WoContract  {
    abstract  class WoPresenter{
        public abstract void Shang(MultipartBody.Part part);
        public abstract void Geng(HashMap<String,String> params);
        public abstract void Xing(HashMap<String,String> params);
        public abstract void Qian(HashMap<String,String> params);
    }
    interface WoModle {
        void ShangModel(MultipartBody.Part part, RequestCallbacks callback);
        void XingModel(HashMap<String,String> params, RequestCallbacks callback);
        void GengModel(HashMap<String,String> params, RequestCallbacks callback);
        void QianModel(HashMap<String,String> params, RequestCallbacks callback);
    }
    interface WoView {
        void CinemaSucceess(Object o);
        void Failure(String msg);
    }
}
