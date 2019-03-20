package com.example.dong.wd_liuyadong.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.bean.LadingBean;
import com.example.dong.wd_liuyadong.bean.XiangBean;
import com.example.dong.wd_liuyadong.contract.LadingContract;
import com.example.dong.wd_liuyadong.presenter.LadingPresenter;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;

import butterknife.BindView;

public class DetailsActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView{
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        HashMap<String,String> paramss =new HashMap<>();
        paramss.put("movieId",id);
        presenter.Xiang(paramss);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this,ButActivity.class));
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
        XiangBean xiangBean= (XiangBean) o;
        if (o instanceof  XiangBean){
         name.setText(xiangBean.getResult().getName());
            Uri uri =Uri.parse(xiangBean.getResult().getImageUrl());
            DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            image.setController(draweeController );
        }
    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
