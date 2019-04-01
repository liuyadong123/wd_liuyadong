package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dong.wd_liuyadong.R;
import com.bw.movie.bean.LobbyInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class LobbyAdapter extends RecyclerView.Adapter<LobbyAdapter.ViewHolder> {
    private Context context;
    private List<LobbyInfo.Result> list;
    public LobbyAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.lobby_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final LobbyInfo.Result result =list.get(i);
        viewHolder.lobby.setText(list.get(i).screeningHall);
        viewHolder.start.setText(list.get(i).beginTime);
        viewHolder.end.setText(list.get(i).endTime);
        viewHolder.price.setText(list.get(i).price+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dianCallBack.haha(result);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size()==0?0:list.size();
    }
    public void setData(List<LobbyInfo.Result> result) {
        if (result!=null){
            this.list=result;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lobby,start,end,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lobby=itemView.findViewById(R.id.lobby);
            start=itemView.findViewById(R.id.start);
            end=itemView.findViewById(R.id.end);
            price=itemView.findViewById(R.id.price);
        }
    }
    private LoabbayCallback dianCallBack;

    public void setLoabbayCallback(LoabbayCallback dianCallBack) {
        this.dianCallBack = dianCallBack;
    }

    public interface LoabbayCallback{


        void haha(LobbyInfo.Result result);
    }
}
