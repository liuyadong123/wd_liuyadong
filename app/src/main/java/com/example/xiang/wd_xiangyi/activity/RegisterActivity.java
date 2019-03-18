package com.example.dong.wd_xiangyi.activity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dong.wd_xiangyi.R;
import com.example.dong.wd_xiangyi.bean.RegisterBean;
import com.example.dong.wd_xiangyi.contract.RegisterContract;
import com.example.dong.wd_xiangyi.presenter.RegsterPresenter;
import com.example.dong.wd_xiangyi.utils.EncryptUtil;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_netword.utils.SpUtils;

import java.util.HashMap;

import butterknife.BindView;

public class RegisterActivity extends UActivity<RegisterContract.LModel,RegisterContract.Contract> implements RegisterContract.LView {

   private static final String KEY = "12baweiyidong345";

   private static final String IV = "67baweiyidong899";
   @BindView(R.id.ed_name)
    EditText name;
    @BindView(R.id.ed_sex)
    EditText sex;
    @BindView(R.id.ed_borday)
    EditText borday;
    @BindView(R.id.ed_phone)
    EditText phone;
    @BindView(R.id.ed_email)
    EditText email;
    @BindView(R.id.ed_pwd)
    EditText pwd;
    @BindView(R.id.ed_pwds)
    EditText pwds;
    @BindView(R.id.zhu)
    Button zhuce;
    private SharedPreferences sp;

    @Override
   protected void initData() {
      super.initData();

   }

   @Override
    protected void initView() {
        sp = SpUtils.getInternsp().getSp();
        sp.edit().putBoolean("cheapi",true).commit();
     zhuce.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String names = name.getText().toString();
             String sexs = sex.getText().toString();
             String bordays = borday.getText().toString();
             String phones = phone.getText().toString();
             String emails = email.getText().toString();
             String s = pwd.getText().toString();
             String s1 = pwds.getText().toString();
             String encrypt = EncryptUtil.encrypt(s);
             String encrypt1 = EncryptUtil.encrypt(s1);
             HashMap<String,String> params =new HashMap<>();
             params.put("nickName",names);
             params.put("sex",sexs);
             params.put("birthday",bordays);
             params.put("phone",phones);
             params.put("email",emails);
             params.put("pwd",encrypt);
             params.put("pwd2",encrypt1);
             presenter.Register(params);
         }
     });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }



    @Override
    public UPresenter inUPresenter() {
        return new RegsterPresenter();
    }

    @Override
    public void Failure(String msg) {


    }

    @Override
    public void RegisterSuccess(RegisterBean registerBean) {
        Toast.makeText(RegisterActivity.this,registerBean.message,Toast.LENGTH_SHORT).show();

        if ("注册成功".equals(registerBean.message))
        sp.edit().putBoolean("cheapi",false).commit();
    }
}
