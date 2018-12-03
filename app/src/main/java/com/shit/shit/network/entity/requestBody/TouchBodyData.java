package com.shit.shit.network.entity.requestBody;

import com.google.gson.Gson;

/**
 * Created by zhimingwu on 2018/1/16.
 * {
 "device_code": "866957038276949",
 "gcj_coordate": "",
 "signinType": 0,
 "wgs_coordate": "",
 "wifi_macaddress": "38:ff:36:78:a2:1c"
 }
 */

public class TouchBodyData extends BodyData{
    private String device_code;
    private String gcj_coordate;
    private int signinType;
    private String wgs_coordate;
    private String wifi_macaddress;

    public String getDevice_code() {
        return device_code;
    }

    public void setDevice_code(String device_code) {
        this.device_code = device_code;
    }

    public String getGcj_coordate() {
        return gcj_coordate;
    }

    public void setGcj_coordate(String gcj_coordate) {
        this.gcj_coordate = gcj_coordate;
    }

    public String getWgs_coordate() {
        return wgs_coordate;
    }

    public void setWgs_coordate(String wgs_coordate) {
        this.wgs_coordate = wgs_coordate;
    }

    public String getWifi_macaddress() {
        return wifi_macaddress;
    }

    public void setWifi_macaddress(String wifi_macaddress) {
        this.wifi_macaddress = wifi_macaddress;
    }

    public int getSigninType() {
        return signinType;
    }

    public void setSigninType(int signinType) {
        this.signinType = signinType;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String str = gson.toJson(this);
        return str;
    }
}
