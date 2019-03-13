package com.example.dong.wd_liuyadong.app;

import android.app.Application;
import android.content.Context;

import com.letv.sarrsdesktop.blockcanaryex.jrt.BlockCanaryEx;
import com.letv.sarrsdesktop.blockcanaryex.jrt.Config;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "337f796a8c", false);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
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
            //your code start here
        }
    }
}
