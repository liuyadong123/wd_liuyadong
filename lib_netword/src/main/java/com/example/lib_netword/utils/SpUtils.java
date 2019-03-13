package com.example.lib_netword.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;

import com.blankj.utilcode.util.SPUtils;
import com.example.lib_netword.BaseApp;

import java.security.Key;

public class SpUtils {

    private  static SpUtils spUtils;


    private SpUtils(){

    }

    public static SpUtils getInternsp(){
        if (spUtils==null){
            synchronized (SpUtils.class){
                if (spUtils==null){
                    spUtils=new SpUtils();
                }
            }
        }
        return  spUtils;
    }
    public SharedPreferences getSp(){
        SharedPreferences wb = BaseApp.getContext().getSharedPreferences("wb", Context.MODE_PRIVATE);

        if (wb!=null){
            return wb;
        }
      return  null;
    }

    public  void putSp(String Key,String Value){
        getSp().edit().putString(Key,Value).commit();
    }
    public String getSp(String key){
            return getSp().getString(key,"");
    }
}
