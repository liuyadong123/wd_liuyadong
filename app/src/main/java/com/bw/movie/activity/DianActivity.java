
package com.bw.movie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.bw.movie.adapter.LobbyAdapter;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.bean.LobbyInfo;
import com.bw.movie.bean.MovieBean;
import com.bw.movie.bean.XiangBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.presenter.LadingPresenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;

public class DianActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView,LobbyAdapter.LoabbayCallback{
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
     @BindView(R.id.dianrv)
    RecyclerView rv;
    private LobbyAdapter lobbyAdapter;
    private int id;
    private int id1;

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        HashMap<String,String> paramse =new HashMap<>();
        paramse.put("cinemasId",id1+10+"");
        paramse.put("movieId",id+"");
        presenter.Loabby(paramse);
        lobbyAdapter = new LobbyAdapter(this);
        rv.setAdapter(lobbyAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

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
        LobbyInfo lobbyInfo = (LobbyInfo) o;
        if (o instanceof LobbyInfo){
            lobbyAdapter.setData(lobbyInfo.result);
            lobbyAdapter.setLoabbayCallback(this);
        }

    }
    @Subscribe(sticky = true)
    public  void xiangw(XiangBean xiangBean){
        Glide.with(this).load(xiangBean.getResult().getImageUrl()).into(icon);
        name.setText(xiangBean.getResult().getName());
        style.setText("导演:"+xiangBean.getResult().getDirector());
        direct.setText("类型"+xiangBean.getResult().getMovieTypes());
        time.setText("时长"+xiangBean.getResult().getDuration());
        brith.setText("产地"+xiangBean.getResult().getPlaceOrigin());
        id = xiangBean.getResult().getId();

    }
    @Subscribe(sticky = true)
    public  void hee(MovieBean.Result result){
        cinema_name.setText(result.name);
        address.setText(result.address);
        id1 = result.id;

    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void haha(LobbyInfo.Result result) {
        EventBus.getDefault().postSticky(result);
        startActivity(new Intent(DianActivity.this,GouZuoActivity.class));
    }
}