package com.bw.movie.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.dong.wd_liuyadong.R;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.adapter.RenAdapter;
import com.bw.movie.bean.GuanBean;
import com.bw.movie.bean.QuXiaoBean;
import com.bw.movie.bean.ReMenBean;
import com.bw.movie.contract.FragmentContract;
import com.bw.movie.presenter.FragmentPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;

public class JiFragment extends BFragment<FragmentContract.FModel,FragmentContract.FragmentPresenter> implements FragmentContract.FView,RenAdapter.XiangCallBack {
  @BindView(R.id.jirv)
    RecyclerView jirv;
    @Override
    public void Success(Object o) {

    }

    @Override
    public void Zhengzai(Object o) {

    }

    @Override
    public void Jijiang(Object o) {
      ReMenBean reMenBean = (ReMenBean) o;
      if (o instanceof  ReMenBean){
        RenAdapter renMenAdapter =new RenAdapter(getActivity(),reMenBean.getResult());
        jirv.setAdapter(renMenAdapter);
        jirv.setLayoutManager(new LinearLayoutManager(getActivity()));
        renMenAdapter.setDianCallBack(this);
      }
    }

  @Override
  public void Guanzhu(Object o) {
    GuanBean guanBean = (GuanBean) o;
    if (o instanceof  GuanBean){
      Toast.makeText(getActivity(),guanBean.message,Toast.LENGTH_SHORT).show();
    }
    QuXiaoBean quXiaoBean = (QuXiaoBean) o;
    if (o instanceof  QuXiaoBean){
      Toast.makeText(getActivity(),quXiaoBean.message,Toast.LENGTH_SHORT).show();
    }
  }

  @Override
    protected void initView(View view) {

      HashMap<String,String> parames =new HashMap<>();
      parames.put("page","1");
      parames.put("count","5");
      persenter.Ji(parames);

    }

    @Override
    protected int getLayoutid() {
        return R.layout.ji_fragment;
    }

    @Override
    public BPresenter inPresenter() {
        return new FragmentPresenter();
    }

    @Override
    public void Failure(String msg) {

    }


  @Override
  public void haha(String id) {
    Intent intent = new Intent(getActivity(), DetailsActivity.class);
    intent.putExtra("id",id);
    startActivity(intent);
  }

  @Override
  public void guanzhu(String id) {
    HashMap<String,String> parames =new HashMap<>();
    parames.put("movieId",id);
    persenter.Guan(parames);
  }

  @Override
  public void weiguanzhu(String id) {
    HashMap<String,String> parame =new HashMap<>();
    parame.put("movieId",id);
    persenter.Qu(parame);
  }
}
