package com.bw.movie.app;

import android.app.Application;
import android.content.Context;

import com.example.lib_netword.BaseApp;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import com.letv.sarrsdesktop.blockcanaryex.jrt.BlockCanaryEx;
import com.letv.sarrsdesktop.blockcanaryex.jrt.Config;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class App extends BaseApp {
    public static String APP_ID="wxb3852e6a6b7d9516";
    public static IWXAPI api;
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化Fresco
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build();
        Fresco.initialize(this, imagePipelineConfig);
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
        CrashReport.initCrashReport(getApplicationContext(), "337f796a8c", false);
       if (LeakCanary.isInAnalyzerProcess(this)) {
      //      This process is dedicated to LeakCanary for heap analysis.
        //     You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
     boolean isInSamplerProcess = BlockCanaryEx.isInSamplerProcess(this);
        if(!isInSamplerProcess) {
            BlockCanaryEx.install(new Config(this));
        }
        if(!isInSamplerProcess) {
      //    your code start here
        }
    }
}
