package com.example.dong.wd_liuyadong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.presenter.LadingPresenter;
import com.example.dong.wd_liuyadong.presenter.Presenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.gyf.barlibrary.ImmersionBar;

public class FragmentActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ImmersionBar.with(this).init();
    }

}