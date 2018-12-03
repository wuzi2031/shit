package com.shit.shit.network;

import android.util.Log;


import com.shit.shit.network.entity.responsBody.ResponseData;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Subscriber;
import util.JsonUtil;

/**
 * Created by zhimingwu on 2018/1/5.
 */

public class ResponseSubscriber {
    public static Subscriber<Response<ResponseBody>> getSubscriber(final HttpResultListener<ResponseData> listener, final Class t) {
        return new Subscriber<Response<ResponseBody>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e);
            }

            @Override
            public void onNext(Response<ResponseBody> responseBodyResponse) {
                int code = responseBodyResponse.code();
                Log.e("retrofit", "code  " + code);
                if (code == 200) {
                    try {
                        String body = responseBodyResponse.body().string();
                        Log.e("retrofit", "responseBody-" + body);
                        ResponseData responseData = (ResponseData) JsonUtil.fromJson(body, t);
                            listener.onSuccess(responseData);

                    } catch (IOException e) {
                        e.printStackTrace();
                        listener.onError(e);
                    }
                }
            }
        };
    }
}
