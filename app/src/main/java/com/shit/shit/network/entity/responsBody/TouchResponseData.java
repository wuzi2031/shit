package com.shit.shit.network.entity.responsBody;

/**
 * Created by zhimingwu on 2018/1/16.
 * {
 "Code": 1,
 "Data": {
 "currentIP": "101.207.139.23",
 "errorMsg": null,
 "signInfo": {
 "address": null,
 "attachments": null,
 "device_code": "866957038276949",
 "gcj_coordate": "0.0,0.0",
 "is_incompany": false,
 "is_usewifi": true,
 "remark": null,
 "sign_ip": "101.207.139.23",
 "sign_time": "2018-01-16 18:55:57",
 "site_desc": "成都研发43",
 "site_region": 0.0,
 "wgs_coordate": "0.0,0.0",
 "wifi_name": "成都研发43"
 },
 "status": 1
 },
 "Message": null
 }

 */

public class TouchResponseData extends ResponseData{
    private int Code;
    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }
}
