package com.shit.shit.network;


import android.util.Log;

import com.shit.shit.network.entity.requestBody.BodyData;
import com.shit.shit.network.entity.requestBody.TouchBodyData;
import com.shit.shit.network.entity.responsBody.ResponseData;

import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhimingwu on 2017/12/27.
 */

public class ApiService {
    private Api mApi;
    private static ApiService mInstance;


    private ApiService() {
        mApi = RetrofitClient.getClient().create(Api.class);
    }

    public static ApiService getInstance() {
        if (mInstance == null) {
            mInstance = new ApiService();
        }
        return mInstance;
    }

    /**
     * 登录
     */
    public void login(HttpResultListener<ResponseData> listener, Map<String,String> map, Class t) {
        mApi.login(map)
                .subscribeOn(Schedulers.io())//在工作线程请求网络
                .observeOn(AndroidSchedulers.mainThread())//在主线程处理结果
                .subscribe(ResponseSubscriber.getSubscriber(listener, t));
    }

    /**
     * touch
     */
    public void touch(HttpResultListener<ResponseData> listener, Map<String, String> map,TouchBodyData bodyData, Class t) {
        Log.e("retrofit",bodyData.toString());
        mApi.touch(map,bodyData)
                .subscribeOn(Schedulers.io())//在工作线程请求网络
                .observeOn(AndroidSchedulers.mainThread())//在主线程处理结果
                .subscribe(ResponseSubscriber.getSubscriber(listener, t));
    }



}
