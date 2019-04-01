package com.bw.movie.model;

import com.bw.movie.api.UserApi;
import com.bw.movie.bean.GengBean;
import com.bw.movie.bean.QianBean;
import com.bw.movie.bean.ShangBean;
import com.bw.movie.bean.XinxiBean;
import com.bw.movie.contract.WoContract;
import com.bw.movie.net.RequestCallbacks;
import com.bw.movie.net.RxRetrofitView;
import com.example.lib_netword.network.RetrofitUtils;
import com.example.lib_netword.network.RxUtils;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;

public class WoModel implements WoContract.WoModle {
        @Override
        public void ShangModel(MultipartBody.Part part, final RequestCallbacks callback) {
            RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                    .ed(UserApi.Shang_Api,part)
                    .compose(RxUtils.<ShangBean>schdulers())
                    .subscribe(new Consumer<ShangBean>() {
                        @Override
                        public void accept(ShangBean shangBean) throws Exception {
                            callback.OnSuccess(shangBean);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            callback.Failure("没网了");
                        }
                    });

    }

    @Override
    public void GengModel(HashMap<String, String> params, final RequestCallbacks callback) {
         RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                 .geng(UserApi.GenXing_Api,params)
                 .compose(RxUtils.<GengBean>schdulers())
                 .subscribe(new Consumer<GengBean>() {
                     @Override
                     public void accept(GengBean gengBean) throws Exception {
                         callback.OnSuccess(gengBean);
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {
                         callback.Failure("没网了");
                     }
                 });
    }

    @Override
    public void QianModel(HashMap<String, String> params, RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .qian(UserApi.Qian_Api,params)
                .compose(RxUtils.schdulers())
                .subscribe(new Consumer<QianBean>() {
                    @Override
                    public void accept(QianBean qianBean) throws Exception {
                        callback.OnSuccess(qianBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("请求失败");
                    }
                });
    }

    @Override
    public void XingModel(HashMap<String, String> params, final RequestCallbacks callback) {
        RetrofitUtils.getIntenter().createService(RxRetrofitView.class)
                .xin(UserApi.Xingxi_Api,params)
                .compose(RxUtils.<XinxiBean>schdulers())
                .subscribe(new Consumer<XinxiBean>() {
                    @Override
                    public void accept(XinxiBean xinxiBean) throws Exception {
                        callback.OnSuccess(xinxiBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callback.Failure("没网了");
                    }
                });

    }
}
