package com.example.dong.wd_liuyadong.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.adapter.CinemaFlowAdapter;
import com.example.dong.wd_liuyadong.adapter.CinemaesAdapter;
import com.example.dong.wd_liuyadong.adapter.LobbyAdapter;
import com.example.dong.wd_liuyadong.adapter.RenMenAdapter;
import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.bean.LobbyInfo;
import com.example.dong.wd_liuyadong.bean.MovieBean;
import com.example.dong.wd_liuyadong.bean.ReMenBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.presenter.LadingPresenter;
import com.example.dong.wd_liuyadong.presenter.Presenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import recycler.coverflow.RecyclerCoverFlow;

public class CinemaActivity  extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView{
    @BindView(R.id.cinema_icon)
    ImageView icon;
    @BindView(R.id.cinima_name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rcf_cinema_flow)
    RecyclerCoverFlow flow;

    private LobbyAdapter lobbyAdapter;
    private int id1;
    private  String id;
    private  CinemaFlowAdapter cinemaFlowAdapter;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        HashMap<String,String> paramse =new HashMap<>();
        paramse.put("cinemasId",id1+10+"");
        paramse.put("movieId",id);
        presenter.Loabby(paramse);
        lobbyAdapter = new LobbyAdapter(this);
        rv.setAdapter(lobbyAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cinema;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {

    }
    @Subscribe(sticky = true)
    public  void beanleis(MovieBean.Result result){
        Glide.with(this).load(result.logo).into(icon);
        name.setText(result.name);
        address.setText(result.address);
        id1 = result.id;

    }
    @Subscribe(sticky = true)
    public  void beanleissss(ReMenBean reMenBean){
       CinemaesAdapter cinemaFlowAdapter=new CinemaesAdapter(this,reMenBean.getResult());
        flow.setAdapter(cinemaFlowAdapter);
        flow.scrollToPosition(2);

    }
    @Subscribe(sticky = true)
    public  void beanleies(String id){
    this.id = id;
}

    @Override
    public void XiangSuccess(Object o) {
        if (o instanceof LobbyInfo){
            LobbyInfo lobbyInfo = (LobbyInfo) o;
            lobbyAdapter.setData(lobbyInfo.result);
        }


    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
