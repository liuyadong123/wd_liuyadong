package com.example.dong.wd_liuyadong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.activity.GouPiaoActivity;
import com.example.dong.wd_liuyadong.activity.GuanzhuActivity;
import com.example.dong.wd_liuyadong.activity.LadingActivity;
import com.example.dong.wd_liuyadong.activity.XingxiActivity;
import com.example.dong.wd_liuyadong.activity.YiJianActivity;
import com.example.dong.wd_liuyadong.contract.CinemaContract;
import com.example.dong.wd_liuyadong.presenter.CinemaPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;

import butterknife.BindView;

public class MyFragment extends BFragment<CinemaContract.CModle,CinemaContract.CinemaPresenter> implements CinemaContract.CView {
    @BindView(R.id.iv_touxiang)
    ImageView touxiang;
    @BindView(R.id.iv_xingxi)
    ImageView xingxi;
    @BindView(R.id.iv_guanzhu)
    ImageView guanzhu;
    @BindView(R.id.iv_jilu)
    ImageView jilu;
    @BindView(R.id.iv_yijian)
    ImageView yijian;
    @BindView(R.id.iv_zuixin)
    ImageView zuixin;
    @BindView(R.id.iv_tuichu)
    ImageView tuichu;
    @BindView(R.id.text_name)
    TextView name;
    @BindView(R.id.text_qian)
    TextView qian;

    @Override
    protected void initView(View view) {
        //上传头像
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //我的信息
        xingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getActivity(),XingxiActivity.class));
            }
        });
        //我的关注
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),GuanzhuActivity.class));
            }
        });
        //购票记录
        jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),GouPiaoActivity.class));
            }
        });
        //意见反馈
        yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),YiJianActivity.class));
            }
        });
        //退出登录
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LadingActivity.class));
            }
        });

    }

    @Override
    protected int getLayoutid() {
        return R.layout.my_fragment;
    }

    @Override
    public void CinemaSucceess(Object o) {

    }

    @Override
    public BPresenter inPresenter() {
        return new CinemaPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
