package com.dc.kwjsons.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dc.kwjsons.R;
import com.dc.kwjsons.bean.DateilBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDetailJzAdapter extends RecyclerView.Adapter<MyDetailJzAdapter.MyViewHolder> {
    Context context;
    DateilBean list;

    public MyDetailJzAdapter(Context context, DateilBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.detail_jz_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        List<String> posterList = list.getPosterList();
        Glide.with(context).load(posterList.get(i)).into(myViewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.getPosterList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img)
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
