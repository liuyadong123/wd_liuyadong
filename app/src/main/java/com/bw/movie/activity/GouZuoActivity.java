package com.bw.movie.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.movie.wxapi.WXPayEntryActivity;
import com.example.dong.wd_liuyadong.R;
import com.bw.movie.adapter.MyDetailJzAdapter;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.bean.LobbyInfo;
import com.bw.movie.bean.XiadainBean;
import com.bw.movie.bean.ZfbBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.LadingContract;

import com.bw.movie.presenter.LadingPresenter;
import com.bw.movie.utils.Md5;
import com.bw.movie.view.SeatTable;
import com.example.lib_core.mvp.UActivity;
import com.example.lib_core.mvp.UPresenter;
import com.example.lib_netword.utils.SpUtils;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


public class GouZuoActivity extends UActivity<LadingContract.LModel,LadingContract.Contract> implements LadingContract.LView {
    private static final int SDK_PAY_FLAG = 9000;
    private SeatTable seatTableView;
  @BindView(R.id.item_cinema_seat_text_price)
    TextView num;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.end)
    TextView end;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.item_cinema_detail_img_v)
    ImageView dui;

    @BindView(R.id.item_cinema_detail_img_x)
    ImageView cuo;
    private LobbyInfo.Result result1;
    private int i ;
    private double nums;
    private String orderId;
    private int ss;
    private ZfbBean zfbBean;
    private ZhiFuBean zhiFuBean;
    private String orderInfo;
    private String format;
    private int sss;
    private ZfbBean wxBean;
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wxb3852e6a6b7d9516";

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            PayResult result1 = new PayResult((Map<String, String>) msg.obj);
            String result = result1.getResult();
            String resultStatus = result1.getResultStatus();
             if(TextUtils.equals(resultStatus,"9000")){
                 Toast.makeText(GouZuoActivity.this, "支付成功",
                         Toast.LENGTH_LONG).show();
             }else {
                 Toast.makeText(GouZuoActivity.this, "支付失败",
                         Toast.LENGTH_LONG).show();
             }



        };
    };
    private String userId;
    private PopupWindow popupWindow;


    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        SharedPreferences sp=getSharedPreferences("liuyad",MODE_PRIVATE);
        userId =  sp.getString("userId", "");
        dui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = result1.id;
                String he = userId +id+i+"movie";
                String s = Md5.MD5(he);
                HashMap<String,String> paramew =new HashMap<>();
                paramew.put("scheduleId",id);
                paramew.put("amount",i+"");
                paramew.put("sign",s);
                presenter.Xia(paramew);

            }
        });

         cuo.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
        seatTableView = findViewById(R.id.seatTableView);
        seatTableView.setScreenName("8号厅荧幕");//设置屏幕名称
        seatTableView.setMaxSelected(3);//设置最多选中
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if(row==6&&column==6){
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {
               i++;
                nums = result1.price * i;
               DecimalFormat  df   =new DecimalFormat("#.00");
                format = df.format(nums);
                num.setText(format +"");

            }

            @Override
            public void unCheck(int row, int column) {
                i--;
                nums = result1.price * i;
               DecimalFormat  df  =new DecimalFormat("#.00");
                format = df.format(nums);
                num.setText(format+"");
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(7,8);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_gou_zuo;
    }

    @Override
    public void LadingSuccess(LadingBean ladingBean) {

    }

    @Override
    public void XiangSuccess(Object o) {
     if (o instanceof XiadainBean){
         XiadainBean xiadainBean = (XiadainBean) o;
         orderId = xiadainBean.orderId;
         Toast.makeText(GouZuoActivity.this,xiadainBean.message,Toast.LENGTH_SHORT).show();
         if ("下单成功".equals(xiadainBean.message)){
             setDui();
         }
     }else
        if (o instanceof ZhiFuBean){
            zhiFuBean = (ZhiFuBean) o;
            orderInfo = zhiFuBean.result;
            zf(orderInfo);

        }
     else if (o instanceof  ZfbBean){
            wxBean = (ZfbBean) o;
            // 通过WXAPIFactory工厂，获取IWXAPI的实例
            api = WXAPIFactory.createWXAPI(this, APP_ID, true);
            // 将应用的appId注册到微信
            api.registerApp(APP_ID);
            PayReq request =new PayReq();
            request.appId=wxBean.appId;
            request.partnerId=wxBean.partnerId;
            request.prepayId=wxBean.prepayId;
            request.packageValue=wxBean.packageValue;
            request.nonceStr=wxBean.nonceStr;
            request.timeStamp=wxBean.timeStamp;
            request.sign=wxBean.sign;
            api.sendReq(request);
            popupWindow.dismiss();

        }
    }



    private void zf(final String orderInfo) {

        // final String sorderInfo = orderInfo;// 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
               PayTask alipay = new PayTask(GouZuoActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void setDui() {
        View view =LayoutInflater.from(GouZuoActivity.this).inflate(R.layout.seat_pop,null,false);
        ImageView back = view.findViewById(R.id.down);
       final TextView zhifu= view.findViewById(R.id.zhifu);
       final RadioButton wx= view.findViewById(R.id.wx);
       final RadioButton zfb= view.findViewById(R.id.zfb);
        popupWindow = new PopupWindow(view,CoordinatorLayout.LayoutParams.MATCH_PARENT,1000,true);
        popupWindow.showAtLocation(findViewById(R.id.ee),Gravity.BOTTOM |Gravity
                .CENTER_HORIZONTAL,0,0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        wx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                 if (isChecked){
                     zhifu.setText("微信支付￥:"+format);
                 }

            }
        });
        zfb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    zhifu.setText("支付宝支付￥:"+format);
                }


            }
        });
        zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wx.isChecked()==true){
                    ss = 1;
                    startActivity(new Intent(GouZuoActivity.this,WXPayEntryActivity.class));
                }
                if (zfb.isChecked()==true){
                    ss=2;
                }
                 HashMap<String,String> parames =new HashMap<>();
                 parames.put("payType",ss+"");
                 parames.put("orderId",orderId);
                presenter.Zhi(parames);

                HashMap<String,String> paramess =new HashMap<>();
                paramess.put("payType",ss+"");
                paramess.put("orderId",orderId);
                presenter.wx(paramess);

            }
        });

    }




    @Subscribe(sticky = true)
    public  void hhee(LobbyInfo.Result result){
        result1 = result;
        time.setText(result.duration);
        start.setText(result.beginTime);
        end.setText(result.endTime);
        title.setText(result.screeningHall);

    }

    @Override
    public UPresenter inUPresenter() {
        return new LadingPresenter();
    }

    @Override
    public void Failure(String msg) {

    }

}
