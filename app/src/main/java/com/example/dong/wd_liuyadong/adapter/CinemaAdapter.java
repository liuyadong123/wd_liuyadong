package com.example.mature.wd_movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mature.wd_movie.R;
import com.example.mature.wd_movie.bean.CinemaInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {
    private Context context;
    private List<CinemaInfo.Result> list;
    public CinemaAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.cinema_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(list.get(i).name);
        viewHolder.title.setText(list.get(i).address);
        Uri uri=Uri.parse(list.get(i).logo);
        viewHolder.icon.setImageURI(uri);
    }
    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }
    public void setData(List<CinemaInfo.Result> result) {
        if (result!=null){
            this.list=result;
            notifyDataSetChanged();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView icon;
        TextView name,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            name=itemView.findViewById(R.id.name);
            title=itemView.findViewById(R.id.title);
        }
    }
}
