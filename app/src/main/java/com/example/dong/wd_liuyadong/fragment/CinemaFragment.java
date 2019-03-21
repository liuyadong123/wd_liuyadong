package com.example.dong.wd_liuyadong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.contract.CinemaContract;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CinemaFragment extends BFragment<CinemaContract.CModle,CinemaContract.CinemaPresenter> implements CinemaContract.CView {
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.btn11)
    RadioButton btn1;
    @BindView(R.id.btn12)
    RadioButton btn2;
    @BindView(R.id.group)
    RadioGroup group;
    private List<Fragment> list;
    @Override
    protected void initView(View view) {
        list=new ArrayList<>();
        list.add(new TuiFragment());
        list.add(new FuFragment());
        pager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
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
                    case R.id.btn11:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.btn12:
                        pager.setCurrentItem(1);
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

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }


    @Override
    protected int getLayoutid() {
        return R.layout.cinema_fragment;
    }

    @Override
    public void CinemaSucceess(Object o) {

    }

    @Override
    public BPresenter inPresenter() {
        return null;
    }

    @Override
    public void Failure(String msg) {

    }
}
