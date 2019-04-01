package com.bw.movie.activity;

import android.content.Intent;
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
import com.bw.movie.adapter.CinemaFlowAdapter;
import com.bw.movie.adapter.CinemaesAdapter;
import com.bw.movie.adapter.LobbyAdapter;
import com.bw.movie.adapter.RenMenAdapter;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.bean.LobbyInfo;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.ReMenBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.presenter.LadingPresenter;
import com.bw.movie.presenter.Presenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import recycler.coverflow.RecyclerCoverFlow;

public class CinemaActivity  extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView,LobbyAdapter.LoabbayCallback,CinemaesAdapter.DianIdCallback {
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
    private  CinemaFlowAdapter cinemaFlowAdapter;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        lobbyAdapter = new LobbyAdapter(this);
        lobbyAdapter.setLoabbayCallback(this);
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
    public  void glides(MovieBean.Result result){
        Glide.with(CinemaActivity.this).load(result.logo).into(icon);
        name.setText(result.name);
        address.setText(result.address);
        id1 = result.id;

    }
    @Subscribe(sticky = true)
    public  void linbo(ReMenBean reMenBean){
        CinemaesAdapter cinemaFlowAdapter=new CinemaesAdapter(this,reMenBean.getResult());
        flow.setAdapter(cinemaFlowAdapter);
        cinemaFlowAdapter.setDianIdCallback(this);
        flow.scrollToPosition(2);

    }


    @Override
    public void XiangSuccess(Object o) {
        if (o instanceof LobbyInfo){
            LobbyInfo lobbyInfo = (LobbyInfo) o;
            lobbyAdapter.setData(lobbyInfo.result);

            rv.setAdapter(lobbyAdapter);
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

    @Override
    public void haha(LobbyInfo.Result result) {
        EventBus.getDefault().postSticky(result);
        startActivity(new Intent(CinemaActivity.this,GouZuoActivity.class));
    }

    @Override
    public void dianid(String id) {
        HashMap<String,String> paramse =new HashMap<>();
        paramse.put("cinemasId",id1+"");
        paramse.put("movieId",id);
        presenter.Loabby(paramse);

    }
}
