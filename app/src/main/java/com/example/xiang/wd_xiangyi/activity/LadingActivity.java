package com.example.dong.wd_xiangyi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dong.wd_xiangyi.R;
import com.example.dong.wd_xiangyi.bean.LadingBean;
import com.example.dong.wd_xiangyi.contract.LadingContract;
import com.example.dong.wd_xiangyi.presenter.LadingPresenter;
import com.example.dong.wd_xiangyi.utils.EncryptUtil;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_netword.utils.SpUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;

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

        sp.edit().putBoolean("cheapi",true).commit();
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
            finish();
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
                UMShareConfig config = new UMShareConfig();
                config.isNeedAuthOnGetUserInfo(true);
                UMShareAPI.get(LadingActivity.this).setShareConfig(config);
               UMShareAPI.get(LadingActivity.this).getPlatformInfo(LadingActivity.this, SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                   @Override
                   public void onStart(SHARE_MEDIA share_media) {

                   }

                   @Override
                   public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                       startActivity(new Intent(LadingActivity.this,ButtomActivity.class));
                         finish();
                   }

                   @Override
                   public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                   }

                   @Override
                   public void onCancel(SHARE_MEDIA share_media, int i) {

                   }
               });
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_lading;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {
        Toast.makeText(LadingActivity.this,ladingBean.message,Toast.LENGTH_SHORT).show();
        if ("登陆成功".equals(ladingBean.message)){

            String sessionId = ladingBean.result.sessionId;
            int userId = ladingBean.result.userId;
            presenter.huo(userId+"",sessionId);

            sp.edit().putBoolean("cheapi",false).commit();

            startActivity(new Intent(LadingActivity.this,ButtomActivity.class));
            finish();
        }


    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }
}
