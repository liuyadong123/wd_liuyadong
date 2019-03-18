package com.example.dong.wd_xiangyi.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.dong.wd_xiangyi.R;
import com.example.dong.wd_xiangyi.fragment.CinemaFragment;
import com.example.dong.wd_xiangyi.fragment.FilmFragment;
import com.example.dong.wd_xiangyi.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ButtomActivity extends AppCompatActivity {

    private Unbinder bind;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.sy1)
    RadioButton sy1;
    @BindView(R.id.sy2)
    RadioButton sy2;
    @BindView(R.id.sy3)
    RadioButton sy3;
    @BindView(R.id.groups)
    RadioGroup group;
    private List<Fragment> list;
    public static final float  SCALE_MAX     = 1.2F;
    public static final float  SCALE_NOMAL   = 1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttom);
        bind = ButterKnife.bind(this);
        list=new ArrayList<>();
        list.add(new FilmFragment());
        list.add(new CinemaFragment());
        list.add(new MyFragment());

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
                    case R.id.sy1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.sy2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.sy3:
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
                        setButtonScale(sy1,SCALE_MAX);
                        sy1.setChecked(true);

                        break;
                    case 1:
                        setButtonScale(sy2,SCALE_MAX);
                        sy2.setChecked(true);
                        break;
                    case 2:
                        setButtonScale(sy3,SCALE_MAX);

                        sy3.setChecked(true);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });




    }
    private void setButtonScale(RadioButton controlRadioButton, float scaleType) {
        controlRadioButton.setScaleX(scaleType);
        controlRadioButton.setScaleY(scaleType);
    }
    public void resetScale() {
        sy1.setScaleX(SCALE_NOMAL);
        sy1.setScaleY(SCALE_NOMAL);
        sy2.setScaleX(SCALE_NOMAL);
        sy2.setScaleY(SCALE_NOMAL);
        sy3.setScaleX(SCALE_NOMAL);
        sy3.setScaleY(SCALE_NOMAL);
    }
}
