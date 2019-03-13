package com.example.dong.wd_liuyadong.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.dong.wd_liuyadong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Main2Activity extends AppCompatActivity {

    private Unbinder bind;
    @BindView(R.id.image)
    ImageView image;
    private  int time =3;

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (time==0){
                startActivity(new Intent(Main2Activity.this,MainActivity.class));
                finish();
            }else {
                time--;
                handler.sendEmptyMessageDelayed(0,1000);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bind = ButterKnife.bind(this);
        handler.sendEmptyMessageDelayed(0,1000);


    }
}
