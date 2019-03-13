package com.example.lib_netword.network;

import com.blankj.utilcode.util.SPUtils;
import com.example.lib_netword.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInteract implements Interceptor {
    /**
     * 把没有切换线程功能的observable对象，转换成切换过的observable对象
     * @return
     */
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        request.newBuilder()
                .addHeader("userId",SpUtils.getInternsp().getSp("userId"))
                .addHeader("sessionId",SpUtils.getInternsp().getSp("sessionId"))
                .build();

        Response proceed = chain.proceed(request);

        return proceed;
    }
}
