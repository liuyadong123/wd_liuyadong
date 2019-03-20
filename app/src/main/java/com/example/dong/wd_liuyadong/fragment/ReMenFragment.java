package com.example.dong.wd_liuyadong.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.activity.DetailsActivity;
import com.example.dong.wd_liuyadong.adapter.RenAdapter;
import com.example.dong.wd_liuyadong.adapter.RenMenAdapter;
import com.example.dong.wd_liuyadong.bean.GuanBean;
import com.example.dong.wd_liuyadong.bean.QuXiaoBean;
import com.example.dong.wd_liuyadong.bean.ReMenBean;
import com.example.dong.wd_liuyadong.contract.FragmentContract;
import com.example.dong.wd_liuyadong.presenter.FragmentPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;

public class ReMenFragment extends BFragment<FragmentContract.FModel,FragmentContract.FragmentPresenter> implements FragmentContract.FView,RenAdapter.XiangCallBack {
    @BindView(R.id.rerv)
    RecyclerView rerv;
    private ReMenBean reMenBean;

    @Override
    public void Success(Object o) {
        reMenBean = (ReMenBean) o;
        if (o instanceof  ReMenBean){
            RenAdapter renMenAdapter =new RenAdapter(getActivity(), reMenBean.getResult());
            rerv.setAdapter(renMenAdapter);
            rerv.setLayoutManager(new LinearLayoutManager(getActivity()));
            renMenAdapter.setDianCallBack(this);
        }

    }

    @Override
    public void Zhengzai(Object o) {

    }

    @Override
    public void Jijiang(Object o) {

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
        HashMap<String,String> params =new HashMap<>();
        params.put("page","1");
        params.put("count","5");
        persenter.ReMen(params);
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }


    }

    @Override
    protected int getLayoutid() {
        return R.layout.renmen_fragment;
    }

    @Override
    public BPresenter inPresenter() {
        return new FragmentPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
    @Subscribe(sticky = true)
    public  void checkboxs(String id){
        if (reMenBean.getResult().get(0).isChebox()==true){
            HashMap<String,String> paramss =new HashMap<>();
            paramss.put("movieId",id);
            persenter.Guan(paramss);
        }else {
            HashMap<String,String> paramss =new HashMap<>();
            paramss.put("movieId",id);
            persenter.Qu(paramss);
        }



    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void haha(String id) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
