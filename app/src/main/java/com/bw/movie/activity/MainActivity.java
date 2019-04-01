package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dong.wd_liuyadong.R;
import com.bw.movie.adapter.ViewPagerAdapter;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.contract.Contract;
import com.bw.movie.presenter.Presenter;

import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.Unbinder;

public class MainActivity extends UActivity<Contract.IModel,Contract.presenterContract> implements Contract.IView {

    @BindView(R.id.viewpager)
    ViewPager pager;
    @BindView(R.id.yd1)
    RadioButton yd1;
    @BindView(R.id.yd2)
    RadioButton yd2;
    @BindView(R.id.yd3)
    RadioButton yd3;
    @BindView(R.id.yd4)
    RadioButton yd4;
    @BindView(R.id.yd5)
    RadioButton yd5;
    @BindView(R.id.group)
    RadioGroup group;
    private List<View> list;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;


    @Override
    protected void initView() {

        CrashReport.initCrashReport(getApplicationContext());
        sharedPreferences = getSharedPreferences("uerer", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        boolean zd = sharedPreferences.getBoolean("zd", false);

        if (zd){
            startActivity(new Intent(MainActivity.this,LadingActivity.class));
            finish();
        }


        list = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater();
        list.add(layoutInflater.inflate(R.layout.item1,null));
        list.add(layoutInflater.inflate(R.layout.item2,null));
        list.add(layoutInflater.inflate(R.layout.item3,null));
        list.add(layoutInflater.inflate(R.layout.item4,null));
        list.add(layoutInflater.inflate(R.layout.item5,null));

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){

                        case R.id.yd1:
                            pager.setCurrentItem(0);
                            break;
                        case R.id.yd2:
                            pager.setCurrentItem(1);
                            break;
                        case R.id.yd3:
                            pager.setCurrentItem(2);
                            break;
                        case R.id.yd4:
                            pager.setCurrentItem(3);
                            break;
                    case R.id.yd5:
                        pager.setCurrentItem(4);
                        edit.putBoolean("zd",true);
                        edit.commit();
                        startActivity(new Intent(MainActivity.this,LadingActivity.class));
                        finish();
                        break;


                }
            }
        });


        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(list);
        pager.setAdapter(viewPagerAdapter);
         pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

             }

             @Override
             public void onPageSelected(int position) {
              switch (position){
                  case 0:
                      yd1.setChecked(true);
                      break;
                  case 1:
                      yd2.setChecked(true);
                      break;
                  case 2:
                      yd3.setChecked(true);
                      break;
                  case 3:
                      yd4.setChecked(true);
                      break;
                  case 4:
                      yd5.setChecked(true);
                      break;

              }

             }

             @Override
             public void onPageScrollStateChanged(int state) {

             }
         });






    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }



    @Override
    public UPresenter inUPresenter() {
        return new Presenter();
    }

    @Override
    public void Failure(String msg) {

    }


    @Override
    public void Success(Object o) {

    }
}
