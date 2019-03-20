package com.example.dong.wd_liuyadong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.bean.ReMenBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RenAdapter extends RecyclerView.Adapter<RenAdapter.CineVH> {
     private Context context;
     private List<ReMenBean.ResultBean> list;

    public RenAdapter(Context context, List<ReMenBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CineVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.ritem,viewGroup,false);
        CineVH cineVH =new CineVH(view);
        return cineVH;
    }

    @Override
    public void onBindViewHolder(@NonNull final CineVH cineVH, final int i) {
        Uri uri =Uri.parse(list.get(i).getImageUrl());
        DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        cineVH.imageView.setController(draweeController);
        cineVH.name.setText(list.get(i).getName());
        cineVH.jan.setText(list.get(i).getSummary());
        cineVH.hong.setChecked(list.get(i).isChebox());
        cineVH.hong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    EventBus.getDefault().postSticky(list.get(i).getId()+"");

            }
        });
        cineVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dianCallBack.haha(list.get(i).getId()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class CineVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView imageView;
        private TextView  name,jan;
        private CheckBox hong;
        public CineVH(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            jan=itemView.findViewById(R.id.jian_text);
            hong=itemView.findViewById(R.id.hong);
        }
    }
    private XiangCallBack dianCallBack;

    public void setDianCallBack(XiangCallBack dianCallBack) {
        this.dianCallBack = dianCallBack;
    }

    public interface XiangCallBack{
        void haha(String id);
    }
}
