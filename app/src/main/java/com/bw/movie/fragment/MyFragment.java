package com.bw.movie.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.movie.bean.GengBean;
import com.bw.movie.bean.LadingBean;
import com.bw.movie.bean.QianBean;
import com.bw.movie.bean.XinxiBean;
import com.bw.movie.contract.WoContract;
import com.bw.movie.presenter.WoPresenter;
import com.example.dong.wd_liuyadong.R;
import com.bw.movie.activity.GouPiaoActivity;
import com.bw.movie.activity.GuanzhuActivity;
import com.bw.movie.activity.LadingActivity;
import com.bw.movie.activity.XingxiActivity;
import com.bw.movie.activity.YiJianActivity;
import com.bw.movie.bean.ShangBean;
import com.bw.movie.contract.CinemaContract;
import com.bw.movie.presenter.CinemaPresenter;
import com.bw.movie.presenter.Presenter;
import com.example.lib_core.mvp.BFragment;
import com.example.lib_core.mvp.BPresenter;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;



public class MyFragment extends TakePhotoFragment implements WoContract.WoView,TakePhoto.TakeResultListener, InvokeListener {
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

    private TakePhoto takePhoto;
    private CropOptions cropOptions;  //裁剪参数
    private CompressConfig compressConfig;  //压缩参数
    private Uri imageUri;  //图片保存路径
    private InvokeParam invokeParam;
    private Uri imageCropUri;

    private String pat1;
    private Unbinder bind;
    private WoPresenter woPresenter;
    private String strings;
    private String downloadUrl;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);



    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_fragment,container,false);
        bind = ButterKnife.bind(this, view);

        return view;
    }

    protected void initView(View view) {
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),android.R.style.Theme_Holo_Light_Dialog);
                builder.setIcon(R.drawable.ic_choice_pic);
                builder.setTitle("选择");
                String[] choices = {"拍照","从相机里选择"};
                builder.setItems(choices, new DialogInterface.OnClickListener() {



                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                imageCropUri = getImageCropUri();
                                takePhoto.onPickFromCaptureWithCrop(imageCropUri, cropOptions);
                                break;
                            case 1:
                                //从照片选择并裁剪
                                imageCropUri = getImageCropUri();
                                takePhoto.onPickFromGalleryWithCrop(imageCropUri, cropOptions);
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.show();
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
              getActivity().finish();
            }
        });
        zuixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> paramw =new HashMap<>();
                paramw.put("ak","0110010010000");
                woPresenter.Geng(paramw);
                getVersion();
                showUpdataDialog();
            }
        });
        qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         woPresenter.Qian(new HashMap<>());
            }
        });

    }

    private String getVersion() {

        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection httpURLConnection = null;
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setReadTimeout(8 * 1000);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String string;
            string = bufferedReader.readLine();
            //对json数据进行解析
            JSONObject jsonObject = new JSONObject(string);
            strings = jsonObject.getString("code");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }
    protected void showUpdataDialog() {
        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity()) ;
        builer.setTitle("版本升级");
        builer.setMessage("软件更新");
        //当点确定按钮时从服务器上下载 新的apk 然后安装
        builer.setPositiveButton("确定", (dialog, which) -> downLoadApk());
        //当点取消按钮时不做任何举动
        builer.setNegativeButton("取消", (dialogInterface, i) -> {}
        );
        AlertDialog dialog = builer.create();
        dialog.show();
    }

    protected void downLoadApk() {
        //进度条
        final ProgressDialog pd;
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(downloadUrl, pd);
                    //安装APK
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                }
            }}.start();
    }

    public static File getFileFromServer(String path, ProgressDialog pd) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            File file = new File(Environment.getExternalStorageDirectory(), "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        }
        else{
            return null;
        }
    }

    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }





    @Override
    public void CinemaSucceess(Object o) {
      if (o instanceof ShangBean){
          shangBean = (ShangBean) o;
          Glide.with(this).load(shangBean.headPath).into(touxiang);
      }
      if (o instanceof GengBean){
          GengBean gengBean = (GengBean) o;
          downloadUrl = gengBean.downloadUrl;
      }
        if (o instanceof  XinxiBean){
            XinxiBean xinxiBean = (XinxiBean) o;
            Glide.with(this).load(xinxiBean.getResult().getHeadPic()).into(touxiang);
            name.setText(xinxiBean.getResult().getNickName());
        }
        if (o instanceof QianBean){
          QianBean qianBean = (QianBean) o;
          Toast.makeText(getActivity(),qianBean.message,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Failure(String msg) {

    }


    public void initData(){


        woPresenter = new WoPresenter(this);
        woPresenter.Xing(new HashMap<String, String>());
        ////获取TakePhoto实例
        takePhoto = getTakePhoto();
        cropOptions = new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create();
        compressConfig=new CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create();
        takePhoto.onEnableCompress(compressConfig,true);  //设置为需要压缩
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }


    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }
    @Override
    public void takeSuccess(final TResult result) {
        pat1 = result.getImage().getOriginalPath();
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File file =new File(pat1);
            if (file !=null && file.exists()){
                RequestBody  requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

                woPresenter.Shang(image);
            }else {
                Toast.makeText(getActivity(), "请选择文件",Toast.LENGTH_LONG).show();
            }
        }
    }
    private Uri getImageCropUri(){
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }

    @Override
    public void takeFail(TResult result, String msg) {
        //取得失败
        Toast.makeText(getActivity(),"设置失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        //取消
    }


}
