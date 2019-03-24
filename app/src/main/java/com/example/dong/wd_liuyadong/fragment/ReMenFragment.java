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
import com.example.dong.wd_liuyadong.bean.XiangBean;
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
    private RenAdapter renMenAdapter;

    @Override
    public void Success(Object o) {

        if (o instanceof  ReMenBean){
            reMenBean = (ReMenBean) o;
            RenAdapter renMenAdapter = new RenAdapter(getActivity(), reMenBean.getResult());
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

        if (o instanceof  GuanBean){
            GuanBean guanBean = (GuanBean) o;
            Toast.makeText(getActivity(),guanBean.message,Toast.LENGTH_SHORT).show();
            if ("关注成功".equals(guanBean.message)){
                reMenBean.getResult().get(0).isChebox();
            }
        }

        if (o instanceof  QuXiaoBean){
            QuXiaoBean quXiaoBean = (QuXiaoBean) o;
            Toast.makeText(getActivity(),quXiaoBean.message,Toast.LENGTH_SHORT).show();
            if ("取消关注成功".equals(quXiaoBean.message)){
                reMenBean.getResult().get(0).isCheboxs();
            }
        }

    }



    @Override
    protected void initView(View view) {
        HashMap<String,String> params =new HashMap<>();
        params.put("page","1");
        params.put("count","5");
        persenter.ReMen(params);

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
