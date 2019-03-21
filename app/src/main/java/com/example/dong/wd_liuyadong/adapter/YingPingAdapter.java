package com.example.dong.wd_liuyadong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.bean.YingPing;

import java.util.ArrayList;
import java.util.List;

public class YingPingAdapter extends RecyclerView.Adapter<YingPingAdapter.YingVH> {
    private Context context;
    private List<YingPing.ResultBean> list;

    public YingPingAdapter(Context context) {
        this.context = context;

    }

    public void setList(List<YingPing.ResultBean> list) {
        if (list!=null){
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void AddList(List<YingPing.ResultBean> list) {
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public YingVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.detail_yp_item,viewGroup,false);
        YingVH yingVH =new YingVH(view);
        return yingVH;
    }

    @Override
    public void onBindViewHolder(@NonNull YingVH yingVH, int i) {
        Glide.with(context).load(list.get(i).getCommentHeadPic()).into(yingVH.touxing);
        yingVH.name.setText(list.get(i).getCommentUserName());
        yingVH.xiang.setText(list.get(i).getCommentContent());
        yingVH.sj.setText(list.get(i).getCommentTime()+"");
        yingVH.text_zanshu.setText(list.get(i).getGreatNum()+"");
        yingVH.text_pingshu.setText(list.get(i).getReplyNum()+"");
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    public class YingVH extends RecyclerView.ViewHolder {
         private ImageView touxing,ping;
         private TextView name,xiang,sj,text_pingshu,text_zanshu;
         private CheckBox zan;
        public YingVH(@NonNull View itemView) {
            super(itemView);
            touxing=itemView.findViewById(R.id.touxiang);
            ping=itemView.findViewById(R.id.ping);
            name=itemView.findViewById(R.id.name);
            xiang=itemView.findViewById(R.id.xiang);
            sj=itemView.findViewById(R.id.sj);
            text_pingshu=itemView.findViewById(R.id.text_pingshu);
            text_zanshu=itemView.findViewById(R.id.text_zanshu);
            zan=itemView.findViewById(R.id.zan);

        }
    }
}
