package com.shit.shit.network.entity.requestBody;


import com.google.gson.Gson;

/**
 * Created by zhimingwu on 2017/12/28.
 */

public class BodyData {
    @Override
    public String toString() {
        Gson gson = new Gson();
        String str = gson.toJson(this);
        return str;
    }
}
