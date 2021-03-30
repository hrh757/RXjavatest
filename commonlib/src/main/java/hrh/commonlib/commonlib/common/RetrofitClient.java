package hrh.commonlib.commonlib.common;

import hrh.commonlib.commonlib.BuildConfig;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit请求客户端类
 */
public class RetrofitClient {
    /**
     * retrofit单例对象
     */
    private static Retrofit mBusinessRetrofit;
    private static Retrofit mCrmRetrofit;

    /**
     * 请求运营平台接口单例对象
     *
     * @return
     */
    public static Retrofit getBusinessInstance() {
        if (mBusinessRetrofit == null) {
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
            OkHttpClient client = builder.build();
            mBusinessRetrofit = new Retrofit.Builder()
                    .baseUrl(Objects.requireNonNull(HttpUrl.parse(CommonConstants.BASE_BUSINESS_URL_PREFIX)))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return mBusinessRetrofit;
    }

    /**
     * 请求CRM接口单例对象
     *
     * @return
     */
    public static Retrofit getCrmInstance() {
        if (mCrmRetrofit == null) {
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
            OkHttpClient client = builder.build();
            mCrmRetrofit = new Retrofit.Builder()
                    .baseUrl(Objects.requireNonNull(HttpUrl.parse(CommonConstants.BASE_CRM_URL_PREFIX)))
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return mCrmRetrofit;
    }
}
