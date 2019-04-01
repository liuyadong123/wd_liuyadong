package com.bw.movie.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.graphics.Color;
import android.support.transition.AutoTransition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.wd_liuyadong.R;
import com.bw.movie.activity.ButActivity;
import com.bw.movie.activity.ButtomActivity;
import com.bw.movie.activity.DetailsActivity;
import com.bw.movie.adapter.JiAdapter;
import com.bw.movie.adapter.RenMenAdapter;
import com.bw.movie.adapter.CinemaFlowAdapter;
import com.bw.movie.adapter.ZhengMenAdapter;
import com.bw.movie.bean.ReMenBean;
import com.bw.movie.contract.FragmentContract;

import com.bw.movie.presenter.FragmentPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;
import com.example.lib_netword.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import recycler.coverflow.RecyclerCoverFlow;

public class FilmFragment extends BFragment<FragmentContract.FModel,FragmentContract.FragmentPresenter> implements FragmentContract.FView,CinemaFlowAdapter.DianCallBack,JiAdapter.DianCallBacks,View.OnClickListener{
     @BindView(R.id.rcf_cinema_flow)
     RecyclerCoverFlow flow;
    @BindView(R.id.rv1)
    RecyclerView rv1;
    @BindView(R.id.rv2)
    RecyclerView rv2;
    @BindView(R.id.rv3)
    RecyclerView rv3;
    @BindView(R.id.remen)
    TextView text1;
    @BindView(R.id.jijiang)
    TextView text2;
    @BindView(R.id.zhengzai)
    TextView text3;
    @BindView(R.id.search_image)
    ImageView search_image;
    @BindView(R.id.search_edname)
    EditText search_edname;
    @BindView(R.id.search_textName)
    TextView search_textName;
    @BindView(R.id.search_linear)
    RelativeLayout search_linear;

    private  CinemaFlowAdapter cinemaFlowAdapter;
    private RenMenAdapter renMenAdapter;
    private ZhengMenAdapter zhengMenAdapter;
    private JiAdapter jiAdapter;


    @Override
    public void Success(Object o) {

        if (o instanceof ReMenBean){
            ReMenBean bean= (ReMenBean) o;
            EventBus.getDefault().postSticky(bean);
            cinemaFlowAdapter=new CinemaFlowAdapter(getActivity(),bean.getResult());
            flow.setAdapter(cinemaFlowAdapter);
            flow.scrollToPosition(2);
            renMenAdapter = new RenMenAdapter(getActivity(),bean.getResult());
            rv1.setAdapter(renMenAdapter);
            cinemaFlowAdapter.setDianCallBack(this);
            renMenAdapter.setDianCallBack(this);
        }


    }

    @Override
    public void Zhengzai(Object o) {

        if (o instanceof ReMenBean) {
            ReMenBean bean= (ReMenBean) o;
            zhengMenAdapter = new ZhengMenAdapter(getActivity(),bean.getResult());
            rv2.setAdapter(zhengMenAdapter);
            zhengMenAdapter.setDianCallBack(this);
        }

    }

    @Override
    public void Jijiang(Object o) {

        if (o instanceof ReMenBean) {
            ReMenBean bean= (ReMenBean) o;
            jiAdapter = new JiAdapter(getActivity(),bean.getResult());
            rv3.setAdapter(jiAdapter);
            jiAdapter.setDianCallBack(this);
        }
    }

    @Override
    public void Guanzhu(Object o) {

    }

    @Override
    protected void initView(View view) {
     HashMap<String,String> params =new HashMap<>();
        params.put("page","1");
        params.put("count","5");
        persenter.ReMen(params);
     HashMap<String,String> paramss =new HashMap<>();
        paramss.put("page","1");
        paramss.put("count","5");
        persenter.Zheng(paramss);
       HashMap<String,String> parames =new HashMap<>();
        parames.put("page","1");
        parames.put("count","5");
        persenter.Ji(parames);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
         rv2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
      text1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getActivity(),ButActivity.class));
          }
      });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ButActivity.class));
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ButActivity.class));
            }
        });
        //点击事件
        search_image.setOnClickListener(this);
        search_textName.setOnClickListener(this);
    }


    @Override
    protected int getLayoutid() {
        return R.layout.file_fragment;
    }

    @Override
    public BPresenter inPresenter() {
        return new FragmentPresenter();
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void haha(ReMenBean.ResultBean resultBean) {
        EventBus.getDefault().postSticky(resultBean);
        startActivity(new Intent(getActivity(),DetailsActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_image:
                //点击搜索 伸展
                initExpand();
                break;
            case R.id.search_textName:
                //点击text收缩
                initReduce();
                break;
        }

    }
    /*设置伸展状态时的布局*/
    @SuppressLint("ClickableViewAccessibility")
    public void initExpand() {
        search_edname.setHint("CGV影城");
        search_edname.requestFocus();
        search_edname.setHintTextColor(Color.WHITE);
        search_textName.setText("搜索");
        search_textName.setVisibility(View.VISIBLE);
        search_edname.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) search_linear.getLayoutParams();
        LayoutParams.width = dip2px(320);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        search_linear.setLayoutParams(LayoutParams);
        search_edname.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                search_edname.setFocusable(true);
                search_edname.setFocusableInTouchMode(true);
                return false;
            }
        });
        //开始动画
        beginDelayedTransition(search_linear);

    }
    /*设置收缩状态时的布局*/
    private void initReduce() {
        search_edname.setCursorVisible(false);
        search_edname.setVisibility(View.GONE);
        search_textName.setVisibility(View.GONE);
        RelativeLayout.LayoutParams LayoutParams = (RelativeLayout.LayoutParams) search_linear.getLayoutParams();
        LayoutParams.width = dip2px(60);
        LayoutParams.setMargins(dip2px(0), dip2px(0), dip2px(0), dip2px(0));
        search_linear.setLayoutParams(LayoutParams);

        //隐藏键盘
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
        search_edname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_edname.setCursorVisible(true);
            }
        });
        //开始动画
        beginDelayedTransition(search_linear);
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void beginDelayedTransition(ViewGroup view) {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(500);
        TransitionManager.beginDelayedTransition(view, transition);
    }

    private int dip2px(float dpVale) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpVale * scale + 0.5f);
    }

}
