package com.example.lib_core.mvp;

import com.example.lib_core.fragment.BaseFragment;

public abstract class BFragment<M extends BModel,P extends  BPresenter> extends BaseFragment implements BView {
    public  M model;
    public  P persenter;

    @Override
    protected void initData() {
        persenter= (P) inPresenter();
        if (persenter!=null){
            model= (M) persenter.getModel();
            persenter.attach(model,this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        persenter.deattach();
    }
}
