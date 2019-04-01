package com.bw.movie.wxapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.activity.ButtomActivity;
import com.bw.movie.activity.LadingActivity;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.bean.WeiLoginInfo;
import com.bw.movie.contract.LadingContract;
import com.bw.movie.presenter.LadingPresenter;
import com.example.dong.wd_liuyadong.R;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_netword.utils.SpUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class WXEntryActivity  extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView,IWXAPIEventHandler {
    private String code;
    private  SharedPreferences sp;
    private SharedPreferences.Editor edit;


    @Override
    protected void initView() {
        sp = SpUtils.getInternsp().getSp();
        edit=sp.edit();
        sp.edit().putBoolean("cheapi",false).commit();
        WeiXinUtil.reg(WXEntryActivity.this).handleIntent(getIntent(), this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wxentry;
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }
    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                code = ((SendAuth.Resp) baseResp).code;
                Log.i("qwe", "onResp: code = "+code);
                HashMap<String,String> params=new HashMap<>();
                params.put("code",code);
                presenter.WeiLogin(params);
                break;
            default:
                break;
        }
    }

    /**
     * 清除微信memory leak
     */
    public static void cleanWXLeak() {
        try {
            Class clazz = com.tencent.a .a.a.a.g.class;
            Field field = clazz.getDeclaredField("V");
            field.setAccessible(true);
            Object obj = field.get(clazz);
            if (obj != null) {
                com.tencent.a.a.a.a.g g = (com.tencent.a.a.a.a.g) obj;
                Field mapField = clazz.getDeclaredField("U");
                mapField.setAccessible(true);
                Map map = (Map) mapField.get(g);
                map.clear();
            }
            field.set(clazz, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {

    }

    @Override
    public void XiangSuccess(Object o) {
        if (o instanceof WeiLoginInfo){
            WeiLoginInfo info= (WeiLoginInfo) o;
            SharedPreferences sharedPreferences = getSharedPreferences("liuyad", MODE_PRIVATE);
            SharedPreferences.Editor edit1 = sharedPreferences.edit();
            edit1.putString("userId",info.result.userId+"").commit();
            if ("登陆成功".equals(info.message)){
                String sessionId = info.result.sessionId;
                String userId = info.result.userId;
                presenter.huo(userId,sessionId);
                sp.edit().putBoolean("cheapi",true).commit();
                startActivity(new Intent(WXEntryActivity.this,ButtomActivity.class));
                finish();
            }
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
