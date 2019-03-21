package com.example.dong.wd_liuyadong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.bean.XinxiBean;
import com.example.dong.wd_liuyadong.contract.MyContract;
import com.example.dong.wd_liuyadong.fragment.MyFragment;
import com.example.dong.wd_liuyadong.presenter.MyPresenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;

import java.util.HashMap;

import butterknife.BindView;

public class XingxiActivity extends UActivity<MyContract.MModel,MyContract.MyPresenter> implements MyContract.MView {
     @BindView(R.id.touxing)
    ImageView touxiang;
     @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.photo)
    TextView photo;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.dian)
    ImageView dian;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void initView() {
      presenter.Xing(new HashMap<String, String>());
      back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
       finish();
          }
      });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xingxi;
    }

    @Override
    public void mySuccess(Object o) {
       XinxiBean xinxiBean = (XinxiBean) o;
        if (o instanceof  XinxiBean){
            Glide.with(this).load(xinxiBean.getResult().getHeadPic()).into(touxiang);
            name.setText(xinxiBean.getResult().getNickName());
            sex.setText(xinxiBean.getResult().getSex());
            birthday.setText(xinxiBean.getResult().getBirthday()+"");
            photo.setText(xinxiBean.getResult().getPhone());
            email.setText(xinxiBean.getResult().getEmail());

        }
    }

    @Override
    public UPresenter inUPresenter() {
        return new MyPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
