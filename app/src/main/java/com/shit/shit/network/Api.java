package com.shit.shit.network;

import com.shit.shit.Constants;
import com.shit.shit.network.entity.requestBody.BodyData;
import com.shit.shit.network.entity.requestBody.TouchBodyData;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by zhimingwu on 2017/12/27.
 */

public interface Api {
    /**
     * 登录
     *
     * @param map
     * @return
     */
    @Headers({"Content-Type:application/x-www-form-urlencoded","Connection:close"})
    @POST("/OAuth/Token")
    @FormUrlEncoded
    Observable<Response<ResponseBody>> login(@FieldMap Map<String, String> map);

    /**
     * touch
     *
     * @param bodyData
     * @return
     */

    @POST("/api/v1/106454/113590608/Signin/AddV4")
    Observable<Response<ResponseBody>> touch(@HeaderMap Map<String, String> map,@Body TouchBodyData bodyData);


}
