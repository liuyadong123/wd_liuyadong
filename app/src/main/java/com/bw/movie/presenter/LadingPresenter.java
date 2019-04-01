package com.bw.movie.presenter;

import com.bw.movie.bean.LadingBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.net.RequestCallback;
import com.bw.movie.net.RequestCallbacks;
import com.google.gson.Gson;

import java.util.HashMap;

public class LadingPresenter extends LadingContract.Contract {
    @Override
    public void Lading(HashMap<String, String> params) {
            model.LadingModel(params, new RequestCallback() {
                @Override
                public void Success(String result) {
                    LadingBean ladingBean = new Gson().fromJson(result, LadingBean.class);
                    view.LadingSuccess(ladingBean);

                }

                @Override
                public void Failure(String msg) {
                     view.Failure(msg);
                }
            });
    }

    @Override
    public void huo(String userId, String sessionId) {
        model.huoModel(userId,sessionId);
    }
    @Override
    public void Xiang(HashMap<String, String> params) {
        model.XiangModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void Tui(HashMap<String, String> params) {
        model.TuiModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void Ying(HashMap<String, String> params) {
       model.YingModel(params, new RequestCallbacks() {
           @Override
           public void OnSuccess(Object o) {
               view.XiangSuccess(o);
           }

           @Override
           public void Failure(String msg) {
            view.Failure(msg);
           }
       });
    }

    @Override
    public void Loabby(HashMap<String, String> params) {
        model.LoabbyModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void Xia(HashMap<String, String> params) {
        model.XiaModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
             view.Failure(msg);
            }
        });
    }

    @Override
    public void Zhi(HashMap<String, String> params) {
        model.ZhiModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
              view.Failure(msg);
            }
        });
    }

    @Override
    public void wx(HashMap<String, String> params) {
        model.wxModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
                view.Failure(msg);
            }
        });
    }

    @Override
    public void WeiLogin(HashMap<String, String> params) {
        model.WeiLoginModel(params, new RequestCallbacks() {
            @Override
            public void OnSuccess(Object o) {
                view.XiangSuccess(o);
            }

            @Override
            public void Failure(String msg) {
                    view.Failure(msg);
            }
        });
    }


}
