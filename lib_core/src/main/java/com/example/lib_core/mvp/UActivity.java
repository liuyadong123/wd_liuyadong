package com.example.lib_core.mvp;

import android.graphics.ColorSpace;

import com.example.lib_core.activity.BaseActivity;

public abstract class UActivity<M extends UModel,P extends  UPresenter> extends BaseActivity implements  UView {
    public M modle;
    protected P presenter;

    @Override
    protected void initData() {
        presenter= (P) inUPresenter();
        if (presenter!=null){
            modle= (M) presenter.getModel();
            presenter.attach(modle,this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deattach();
    }
}
