package com.example.dong.wd_liuyadong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.wd_liuyadong.R;
import com.example.dong.wd_liuyadong.activity.GouPiaoActivity;
import com.example.dong.wd_liuyadong.activity.GuanzhuActivity;
import com.example.dong.wd_liuyadong.activity.LadingActivity;
import com.example.dong.wd_liuyadong.activity.XingxiActivity;
import com.example.dong.wd_liuyadong.activity.YiJianActivity;
import com.example.dong.wd_liuyadong.bean.ShangBean;
import com.example.dong.wd_liuyadong.contract.CinemaContract;
import com.example.dong.wd_liuyadong.presenter.CinemaPresenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyFragment extends BFragment<CinemaContract.CModle,CinemaContract.CinemaPresenter> implements CinemaContract.CView {
    @BindView(R.id.iv_touxiang)
    ImageView touxiang;
    @BindView(R.id.iv_xingxi)
    ImageView xingxi;
    @BindView(R.id.iv_guanzhu)
    ImageView guanzhu;
    @BindView(R.id.iv_jilu)
    ImageView jilu;
    @BindView(R.id.iv_yijian)
    ImageView yijian;
    @BindView(R.id.iv_zuixin)
    ImageView zuixin;
    @BindView(R.id.iv_tuichu)
    ImageView tuichu;
    @BindView(R.id.text_name)
    TextView name;
    @BindView(R.id.text_qian)
    TextView qian;
    private ShangBean shangBean;


    @Override
    protected void initView(View view) {
        //上传头像
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"Pictures/hi.jpg";
                File file =new File(path);
                if (file==null && file.exists()){
                    RequestBody  requestBody =RequestBody.create(MediaType.parse("image/*"),file);
                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                     persenter.shang(image);
                }else {
                    Toast.makeText(getActivity(), "请选择文件",Toast.LENGTH_LONG).show();
                }
              }
            }
        });
        final View views = LayoutInflater.from(getActivity()).inflate(R.layout.pop_xiangji, null, false);
        TextView paizhao = view.findViewById(R.id.text_paizhao);
        TextView xiangji = view.findViewById(R.id.text_xiangji);
        final PopupWindow popupWindow = new PopupWindow(views, CoordinatorLayout.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(v);
            }
        });
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拍照并裁剪

                //仅仅拍照不裁剪


            }
        });
        xiangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //我的信息
        xingxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getActivity(),XingxiActivity.class));
            }
        });
        //我的关注
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),GuanzhuActivity.class));
            }
        });
        //购票记录
        jilu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),GouPiaoActivity.class));
            }
        });
        //意见反馈
        yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),YiJianActivity.class));
            }
        });
        //退出登录
        tuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),LadingActivity.class));
            }
        });

    }

    @Override
    protected int getLayoutid() {
        return R.layout.my_fragment;
    }

    @Override
    public void CinemaSucceess(Object o) {
      if (o instanceof ShangBean){
          shangBean = (ShangBean) o;
          Toast.makeText(getActivity(), shangBean.message,Toast.LENGTH_SHORT)
                  .show();
      }
    }

    @Override
    public BPresenter inPresenter() {
        return new CinemaPresenter();
    }

    @Override
    public void Failure(String msg) {

    }





}
