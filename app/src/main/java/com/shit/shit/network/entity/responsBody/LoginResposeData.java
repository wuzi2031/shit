package com.shit.shit.network.entity.responsBody;


import com.google.gson.Gson;

/**
 * Created by zhimingwu on 2018/1/16.
 */

public class LoginResposeData extends ResponseData{
    private String access_token;
    private String expires_in;
    private String user_id;
    private String tenant_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }
    @Override
    public String toString() {
        Gson gson = new Gson();
        String str = gson.toJson(this);
        return str;
    }
}
