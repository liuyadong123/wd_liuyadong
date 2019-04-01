package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.bw.movie.bean.ReMenBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class CinemaFlowAdapter extends RecyclerView.Adapter<CinemaFlowAdapter.CineVH> {
     private Context context;
     private List<ReMenBean.ResultBean> list;

    public CinemaFlowAdapter(Context context, List<ReMenBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CineVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.cinema_flow_item,viewGroup,false);
        CineVH cineVH =new CineVH(view);
        return cineVH;
    }

    @Override
    public void onBindViewHolder(@NonNull CineVH cineVH, int i) {
        final ReMenBean.ResultBean resultBean =list.get(i);
        Uri uri =Uri.parse(list.get(i).getImageUrl());
         cineVH.imageView.setImageURI(uri);
        cineVH.flow1.setText(list.get(i).getName());
        cineVH.flow2.setText(list.get(i).getRank()+"");
        cineVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianCallBack.haha(resultBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class CineVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView imageView;
        private TextView  flow1,flow2;
        public CineVH(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.simp_cinema_flow);
            flow1=itemView.findViewById(R.id.text_cinema_flow1);
            flow2=itemView.findViewById(R.id.text_cinema_flow2);
        }
    }
    private DianCallBack dianCallBack;

    public void setDianCallBack(DianCallBack dianCallBack) {
        this.dianCallBack = dianCallBack;
    }

    public interface DianCallBack{


        void haha(ReMenBean.ResultBean resultBean);
    }
}
