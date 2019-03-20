package com.example.dong.wd_liuyadong.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.fragment.CinemaFragment;
import com.example.dong.wd_liuyadong.fragment.FilmFragment;
import com.example.dong.wd_liuyadong.fragment.JiFragment;
import com.example.dong.wd_liuyadong.fragment.MyFragment;
import com.example.dong.wd_liuyadong.fragment.ReMenFragment;
import com.example.dong.wd_liuyadong.fragment.ZhengFragment;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ButActivity extends AppCompatActivity {
    private Unbinder bind;

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.btn3)
    RadioButton btn3;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.back)
    ImageView back;
    private List<Fragment> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_but);
        bind = ButterKnife.bind(this);
        ImmersionBar.with(this).init();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ButActivity.this,ButtomActivity.class));
               finish();
            }
        });
        list=new ArrayList<>();
        list.add(new ReMenFragment());
        list.add(new ZhengFragment());
        list.add(new JiFragment());

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        pager.setCurrentItem(2);
                        break;
                }

            }
        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        btn1.setChecked(true);
                        break;
                    case 1:
                        btn2.setChecked(true);
                        break;
                    case 2:
                        btn3.setChecked(true);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }
}
