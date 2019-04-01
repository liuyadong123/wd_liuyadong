package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.bean.XiangBean;
import com.example.dong.wd_liuyadong.R;

import java.util.List;

public class YuGaoAdapter extends RecyclerView.Adapter<YuGaoAdapter.YuVH> {
    private Context context;
    private List<XiangBean.ResultBean.ShortFilmListBean> list;
    @NonNull
    @Override
    public YuVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_yugao,viewGroup,false);
        YuVH yingVH =new YuVH(view);
        return yingVH;

    }

    @Override
    public void onBindViewHolder(@NonNull YuVH yuVH, int i) {
   //     IjkMediaPlayer.loadLibrariesOnce(null);
     //   IjkMediaPlayer.native_profileBegin("libijkplayer.so");

     //   AndroidMediaController controller=new AndroidMediaController(this,false);
     //    yuVH.video.setMediaController(controller);
     //   yuVH.video.setVideoURI(list.get(i).getVideoUrl());
    //    yuVH.video.start();


    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class YuVH extends RecyclerView.ViewHolder {
      //  private IjkVideoView  video;
        public YuVH(@NonNull View itemView) {
            super(itemView);
            //video=itemView.findViewById(R.id.video);
        }
    }
}
