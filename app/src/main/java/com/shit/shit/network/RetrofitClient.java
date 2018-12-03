package com.shit.shit.network;

import android.util.Log;

import com.shit.shit.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhimingwu on 2017/12/27.
 */

public class RetrofitClient {
    static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
//                            .addHeader("Authorization", Constants.TOKEN)
                            .build();
                    Log.e("retrofit", "request====" + request.url().toString());
                    Log.e("retrofit", "requestHeader====" + request.headers().toString());
                    Log.e("retrofit", "requestHeader====" + request.body().contentLength());
                    okhttp3.Response proceed = chain.proceed(request);
                    Log.e("retrofit", "responseHeader====" + proceed.headers().toString());
                    return proceed;
                }
            }).build();

    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(NetConstant.getUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())//Gson 适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())// RxJava 适配器
                .build();
    }


}
