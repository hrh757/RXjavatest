package com.ycsoft.commonlib.common;

import com.ycsoft.commonlib.ApiLog.RequestErrorReportInterceptor;
import com.ycsoft.commonlib.BuildConfig;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    /**
     * 创建一个Retrofit对象
     *
     * @param baseUrl
     * @return
     */
    public static Retrofit create(String baseUrl) {
        return create(baseUrl,false);

    }

    public static Retrofit create(String baseUrl,boolean addApiReport){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        if (addApiReport){
            builder.addInterceptor(new RequestErrorReportInterceptor());
        }
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .baseUrl(Objects.requireNonNull(HttpUrl.parse(baseUrl)))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static Retrofit create(String baseUrl, long readTimeOutSeconds, long connectTimeOutSeconds) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(readTimeOutSeconds, TimeUnit.SECONDS);
        builder.connectTimeout(connectTimeOutSeconds, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .baseUrl(Objects.requireNonNull(HttpUrl.parse(baseUrl)))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }
}
