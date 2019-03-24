package com.example.dong.wd_liuyadong.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.activity.CinemaActivity;
import com.example.dong.wd_liuyadong.activity.DianActivity;
import com.example.dong.wd_liuyadong.activity.MovieActivity;
import com.example.dong.wd_liuyadong.adapter.CinemaAdapter;
import com.example.dong.wd_liuyadong.adapter.YingyuanAdapter;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.contract.CinemaContract;
import com.example.dong.wd_liuyadong.presenter.CinemaPresenter;
import com.example.dong.wd_liuyadong.presenter.FragmentPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;

public class TuiFragment extends BFragment<CinemaContract.CModle,CinemaContract.CinemaPresenter> implements CinemaContract.CView,YingyuanAdapter.MoiveCallBacks {
    @BindView(R.id.rv)
    XRecyclerView rv;
    private  int page=1;
    @Override
    public void CinemaSucceess(Object o) {
        MovieBean movieBean = (MovieBean) o;
        if (o instanceof  MovieBean){
            YingyuanAdapter cinemaAdapter =new YingyuanAdapter(getActivity());
            rv.setAdapter(cinemaAdapter);
            cinemaAdapter.setData(movieBean.result);
            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
            cinemaAdapter.setDianCallBacks(this);
        }
    }

    @Override
    protected void initView(View view) {
        HashMap<String,String> parms =new HashMap<>();
        parms.put("page",page+"");
        parms.put("count","10");
        persenter.TuiJIan(parms);
    }

    @Override
    protected int getLayoutid() {
        return R.layout.tui_fragment;
    }

    @Override
    public BPresenter inPresenter() {
        return new CinemaPresenter();
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void haha(MovieBean.Result movieBean) {
        EventBus.getDefault().postSticky(movieBean);
        startActivity(new Intent(getActivity(),CinemaActivity.class));

    }
}
