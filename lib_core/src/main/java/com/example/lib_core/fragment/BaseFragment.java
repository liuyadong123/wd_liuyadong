package com.example.lib_core.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutid(), container, false);
         initView(view);
         initData();
        ImmersionBar.with(this).init();
        bind = ButterKnife.bind(getActivity());
        return view;
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getLayoutid();
}
