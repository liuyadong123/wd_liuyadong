
package com.example.dong.wd_liuyadong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.bean.XiangBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.presenter.LadingPresenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class DianActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView {
    @BindView(R.id.cinima_name)
    TextView cinema_name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.icon)
    ImageView icon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.style)
    TextView style;
    @BindView(R.id.direct)
    TextView direct;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.brith)
    TextView brith;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dian;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {

    }

    @Override
    public void XiangSuccess(Object o) {

    }
    @Subscribe(sticky = true)
    public  void beanlei(XiangBean xiangBean){
        Glide.with(this).load(xiangBean.getResult().getImageUrl()).into(icon);
        name.setText(xiangBean.getResult().getName());
        style.setText("导演:"+xiangBean.getResult().getDirector());
        direct.setText("类型"+xiangBean.getResult().getMovieTypes());
        time.setText("时长"+xiangBean.getResult().getDuration());
        brith.setText("产地"+xiangBean.getResult().getPlaceOrigin());
    }
    @Subscribe(sticky = true)
    public  void beanleis(MovieBean.Result result){
        cinema_name.setText(result.name);
        address.setText(result.address);
    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
