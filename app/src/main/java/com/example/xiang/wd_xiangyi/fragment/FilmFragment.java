package com.example.dong.wd_xiangyi.fragment;

import android.view.View;

import com.example.dong.wd_xiangyi.R;
import com.example.dong.wd_xiangyi.contract.FragmentContract;

import com.example.dong.wd_xiangyi.presenter.FragmentPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;

public class FilmFragment extends BFragment<FragmentContract.FModel,FragmentContract.FragmentPresenter> implements FragmentContract.FView {

    @Override
    public void Success(Object o) {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayoutid() {
        return R.layout.file_fragment;
    }

    @Override
    public BPresenter inPresenter() {
        return new FragmentPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
