package com.example.dong.wd_liuyadong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.adapter.CinemaAdapter;
import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.bean.XiangBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.presenter.LadingPresenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;

public class MovieActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView,CinemaAdapter.MoiveCallBack {
     @BindView(R.id.rv)
    XRecyclerView rv;
     @BindView(R.id.name)
    TextView name;

    private  int page=1;
    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        HashMap<String,String> parms =new HashMap<>();
        parms.put("page",page+"");
        parms.put("count","10");
        presenter.Tui(parms);
    }
 @Subscribe(sticky = true)
 public  void beanlei(XiangBean xiangBean){
         name.setText(xiangBean.getResult().getName());
 }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {

    }

    @Override
    public void XiangSuccess(Object o) {
        MovieBean movieBean = (MovieBean) o;
        if (o instanceof  MovieBean){
            CinemaAdapter cinemaAdapter =new CinemaAdapter(this);
            rv.setAdapter(cinemaAdapter);
            cinemaAdapter.setData(movieBean.result);
            rv.setLayoutManager(new LinearLayoutManager(MovieActivity.this));
            cinemaAdapter.setDianCallBack(this);

        }
    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void haha(MovieBean.Result movieBean) {
        EventBus.getDefault().postSticky(movieBean);
        startActivity(new Intent(MovieActivity.this,DianActivity.class));
    }
}
