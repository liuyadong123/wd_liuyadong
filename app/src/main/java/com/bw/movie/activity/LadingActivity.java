package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.bw.movie.wxapi.WeiXinUtil;
import com.example.dong.wd_liuyadong.R;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.presenter.LadingPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_netword.utils.SpUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class LadingActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView {
    @BindView(R.id.mw)
    CheckBox mw;
    @BindView(R.id.ed_pwd)
    EditText pwd;
    @BindView(R.id.ed_phone)
    EditText phone;
    @BindView(R.id.deng)
    Button dengllu;
    @BindView(R.id.zhuce)
    TextView zuce;
    @BindView(R.id.jz)
     CheckBox jz;
    @BindView(R.id.zd)
    CheckBox zd;
    @BindView(R.id.wexin)
    ImageView weixin;
    private SharedPreferences sp;

    private SharedPreferences.Editor edit;
    private String s;
    private String s1;
    private String encrypt;


    @Override
    protected void initView() {
        sp = SpUtils.getInternsp().getSp();
        edit=sp.edit();

        sp.edit().putBoolean("cheapi",false).commit();
        boolean jzs = sp.getBoolean("jz", false);
        if (jzs){
            String phones = sp.getString("phones", null);
            String pwdes = sp.getString("pwdes", null);
            phone.setText(phones);
            pwd.setText(pwdes);
            jz.setChecked(true);
        }
        zd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    jz.setChecked(true);

                }
            }
        });

        boolean zds = sp.getBoolean("zd", false);
        if (zds){
            zd.setChecked(true);
            startActivity(new Intent(LadingActivity.this,ButtomActivity.class));
        }

        //设置密码显示隐藏
      mw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if (isChecked){
                  pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
              }else {
                  pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
              }
          }
      });
      //跳转注册页面
        zuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LadingActivity.this,RegisterActivity.class));
            }
        });
      //登陆
      dengllu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              s = pwd.getText().toString();
              s1 = phone.getText().toString();
              encrypt = EncryptUtil.encrypt(s);
               if (jz.isChecked()){
                 edit.putString("phones",s1);
                 edit.putString("pwdes",s);
                 edit.putBoolean("jz",true);
                 edit.commit();
               }
               if (zd.isChecked()){
                   edit.putBoolean("zd",true);
                   edit.commit();
               }

              HashMap<String,String> param =new HashMap<>();
              param.put("phone", s1);
              param.put("pwd", encrypt);
              presenter.Lading(param);


          }
      });
      //微信
        weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LadingActivity.this,"微信登录...",Toast.LENGTH_SHORT).show();
                if (!WeiXinUtil.success(LadingActivity.this)) {
                    Toast.makeText(LadingActivity.this,"您还未安装微信客户端",Toast.LENGTH_SHORT).show();
                } else {
                    //验证
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    WeiXinUtil.reg(LadingActivity.this).sendReq(req);
                    finish();
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lading;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {
        SharedPreferences sharedPreferences = getSharedPreferences("liuyad", MODE_PRIVATE);
        SharedPreferences.Editor edit1 = sharedPreferences.edit();
        edit1.putString("userId",ladingBean.result.userId+"").commit();
        EventBus.getDefault().postSticky(ladingBean);
        Toast.makeText(LadingActivity.this,ladingBean.message,Toast.LENGTH_SHORT).show();
        if ("登陆成功".equals(ladingBean.message)){
            String sessionId = ladingBean.result.sessionId;
            int userId = ladingBean.result.userId;
            presenter.huo(userId+"",sessionId);
            sp.edit().putBoolean("cheapi",true).commit();
            startActivity(new Intent(LadingActivity.this,ButtomActivity.class));
        }


    }

    @Override
    public void XiangSuccess(Object o) {

    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
