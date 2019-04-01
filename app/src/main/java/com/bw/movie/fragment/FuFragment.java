package com.bw.movie.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.dong.wd_liuyadong.R;
import com.bw.movie.activity.CinemaActivity;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.adapter.YingyuanAdapter;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.presenter.CinemaPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;

public class FuFragment extends BFragment<CinemaContract.CModle,CinemaContract.CinemaPresenter> implements CinemaContract.CView,YingyuanAdapter.MoiveCallBacks {
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
        persenter.Fu(parms);
    }

    @Override
    protected int getLayoutid() {
        return R.layout.fu_fragment;
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
