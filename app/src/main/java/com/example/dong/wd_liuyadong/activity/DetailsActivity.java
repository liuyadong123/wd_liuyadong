package com.example.dong.wd_liuyadong.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.adapter.MyDetailJzAdapter;
import com.example.dong.wd_liuyadong.adapter.YingPingAdapter;
import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.bean.XiangBean;
import com.example.dong.wd_liuyadong.bean.YingPing;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.presenter.LadingPresenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;

public class DetailsActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView,XRecyclerView.LoadingListener{
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.bg_mh)
    SimpleDraweeView bg_mh;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.gou)
    TextView gou;
    @BindView(R.id.xiang)
    TextView xiang;
    @BindView(R.id.yugao)
    TextView yugao;
    @BindView(R.id.juzhao)
    TextView juzhao;
    @BindView(R.id.yingping)
    TextView ying;
    private int id;
    private  int page=1;
    private YingPingAdapter yingPingAdapter;

    @Override
    protected void initView() {

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        HashMap<String,String> paramss =new HashMap<>();
        paramss.put("movieId",id);
        presenter.Xiang(paramss);
        initDatass();
       //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this,ButActivity.class));
            }
        });
        //购票
        gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         startActivity(new Intent(DetailsActivity.this,MovieActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {

    }

    @Override
    public void XiangSuccess(Object o) {


        if (o instanceof  XiangBean){
            final XiangBean xiangBean= (XiangBean) o;
            EventBus.getDefault().postSticky(xiangBean);
            id = xiangBean.getResult().getId();

            name.setText(xiangBean.getResult().getName());
            Uri uri =Uri.parse(xiangBean.getResult().getImageUrl());
            DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            image.setController(draweeController );
            Uri uris = Uri.parse(xiangBean.getResult().getImageUrl());
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uris)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(6, 4))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(bg_mh.getController())
                    .setImageRequest(request)
                    .build();
            bg_mh.setController(controller);
            bg_mh.setAlpha(0.4f);
            xiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTvMovie(xiangBean);
                }
            });
            yugao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setYuGao(xiangBean);
                }
            });
            juzhao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setJuZhao(xiangBean);
                }
            });
        }

        if (o instanceof  YingPing){
            final YingPing yingping = (YingPing) o;
            ying.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setYing(yingping);
                }
            });
        }
    }

    private void setYing(YingPing yingping) {
        page=1;
        View view =LayoutInflater.from(this).inflate(R.layout.detail_pop_yp,null,false);
        XRecyclerView rv =view.findViewById(R.id.recys);
        rv.setLoadingListener(this);
        rv.setLoadingMoreEnabled(true);
        yingPingAdapter = new YingPingAdapter(this);
        rv.setAdapter(yingPingAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        ImageView back=view.findViewById(R.id.back);
        final PopupWindow popupWindow =new PopupWindow(view,CoordinatorLayout.LayoutParams.MATCH_PARENT,1000,true);
        popupWindow.showAtLocation(findViewById(R.id.activity_arself_center),Gravity.BOTTOM |Gravity
                .CENTER_HORIZONTAL,0,0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        if (page==1){
            rv.refreshComplete();
            yingPingAdapter.setList(yingping.getResult());
        }else {
            if (yingPingAdapter==null){
                yingPingAdapter.setList(yingping.getResult());
            }else {
                yingPingAdapter.AddList(yingping.getResult());
            }
         rv.loadMoreComplete();
        }


    }

    private void initDatass() {
        HashMap<String,String> parames =new HashMap<>();
        parames.put("movieId",id+"");
        parames.put("page",page+"");
        parames.put("count","5");
        presenter.Ying(parames);
    }
    //剧照

    private void setJuZhao(XiangBean xiangBean) {
        View view =LayoutInflater.from(this).inflate(R.layout.detail_pop_jz,null,false);
        RecyclerView recy = view.findViewById(R.id.recy);
        ImageView back = view.findViewById(R.id.back);
        recy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
       final PopupWindow popupWindow =new PopupWindow(view,CoordinatorLayout.LayoutParams.MATCH_PARENT,1000,true);
        popupWindow.showAtLocation(findViewById(R.id.activity_arself_center),Gravity.BOTTOM |Gravity
        .CENTER_HORIZONTAL,0,0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        MyDetailJzAdapter myDetailJzAdapter = new MyDetailJzAdapter(this, xiangBean);
        recy.setAdapter(myDetailJzAdapter);
    }
   //预告
    private void setYuGao(XiangBean xiangBean) {


    }
    //详情
    private void setTvMovie(XiangBean xiangBean) {
        View view = LayoutInflater.from(this).inflate(R.layout.detail_pop_movie, null, false);
        ImageView down = view.findViewById(R.id.down);
        ImageView img = view.findViewById(R.id.img);
        TextView type = view.findViewById(R.id.type);
        TextView director = view.findViewById(R.id.director);
        TextView duration = view.findViewById(R.id.duration);
        TextView placeOrigin = view.findViewById(R.id.placeOrigin);
        TextView summary = view.findViewById(R.id.summary);
        TextView name1 = view.findViewById(R.id.name1);
        TextView name2 = view.findViewById(R.id.name2);
        TextView name3 = view.findViewById(R.id.name3);
        TextView name4 = view.findViewById(R.id.name4);
        final PopupWindow popupWindow = new PopupWindow(view, CoordinatorLayout.LayoutParams.MATCH_PARENT,1000, true);
        popupWindow.showAtLocation(this.findViewById(R.id.activity_arself_center), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        Glide.with(this).load(xiangBean.getResult().getImageUrl()).into(img);
        type.setText("类型:"+xiangBean.getResult().getMovieTypes());
        director.setText("导演:"+xiangBean.getResult().getDirector());
        duration.setText("时长:"+xiangBean.getResult().getDuration());
        placeOrigin.setText("产地:"+xiangBean.getResult().getPlaceOrigin());
        summary.setText(xiangBean.getResult().getSummary());
        String[] split = xiangBean.getResult().getStarring().split(",");
        name1.setText(split[0]);
        name2.setText(split[1]);
        name3.setText(split[2]);
        name4.setText(split[3]);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }

    @Override
    public void onRefresh() {
        page=1;
        initDatass();
    }

    @Override
    public void onLoadMore() {
       page++;
        initDatass();
    }
}
