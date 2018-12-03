package com.shit.shit;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shit.shit.network.ApiService;
import com.shit.shit.network.HttpResultListener;
import com.shit.shit.network.MyData;
import com.shit.shit.network.entity.requestBody.BodyData;
import com.shit.shit.network.entity.requestBody.TouchBodyData;
import com.shit.shit.network.entity.responsBody.LoginResposeData;
import com.shit.shit.network.entity.responsBody.ResponseData;
import com.shit.shit.network.entity.responsBody.TouchResponseData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.JsonUtil;
import util.SharedPreferencesUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ed_user;
    private EditText ed_pass;
    private EditText ed_secret;
    private Button bt_ok;
    private LoginResposeData loginResposeData;
    private String mes = "";
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == 0)
                Toast.makeText(MainActivity.this, mes, Toast.LENGTH_LONG).show();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);
        ed_secret = findViewById(R.id.ed_secret);
        bt_ok = findViewById(R.id.bt_ok);
        bt_ok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_ok:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        fuckIt();
//                    }
//                }).start();

                String user = ed_user.getText().toString();
                String pass = ed_pass.getText().toString();
                String secret = "16bd2f170d174be0aa58919568c113da";
                if (user != null && pass != null & secret != null) {
                    Map<String, String> map = getloginMap(user, pass, secret);
                    ApiService.getInstance().login(new HttpResultListener<ResponseData>() {
                        @Override
                        public void onSuccess(ResponseData responseData) throws IOException {
                            loginResposeData = (LoginResposeData) responseData;
                            touch();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    }, map, LoginResposeData.class);
                }

                break;
            default:
                break;
        }
    }

    public void fuckIt() {
        String token = null;
        try {
            token = getTaken();
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n    \"device_code\": \"0AB9AD19-DCBE-4675-B695-5A7BA8D3EAAF\",\n    \"gcj_coordate\": \"\",\n    \"wgs_coordate\": \"\",\n    \"wifi_macaddress\": \"24:79:2a:75:46:dc\"\n}");
            Request request = new Request.Builder()
                    .url("https://tm.tita.com/api/v1/106454/111648473/Signin/AddV4")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", token)
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "86f81765-525c-e341-6f80-f3f5556d28b4")
                    .build();

            Response response = client.newCall(request).execute();
            mes = response.body().string();
            handler.sendEmptyMessage(0);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public String getTaken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "app_id=100&grant_type=password&is_show_valcode=false&password=ZHANGwei19910702&secret=16bd2f170d174be0aa58919568c113da&username=zhangw02%40keruyun.com");
        Request request = new Request.Builder()
                .url("https://tm.tita.com/OAuth/Token")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "b8781988-ec11-969c-dd3b-d69335abcac6")
                .build();

        Response response = client.newCall(request).execute();
        String json = response.body().string();
        MyData myData = JsonUtil.fromJson(json, MyData.class);
        return myData.getAccess_token();
    }

    private void touch() throws IOException {
        if (loginResposeData != null) {
            String token = loginResposeData.getAccess_token();
            Constants.TOKEN = token;
            HashMap<String, String> header = new HashMap<>();
            header.put("Authorization", token);
            header.put("Cache-Control", "no-cache");
            header.put("Connection", "close");

            ApiService.getInstance().touch(new HttpResultListener<ResponseData>() {
                @Override
                public void onSuccess(ResponseData responseData) throws IOException {
                    TouchResponseData touchResponseData = (TouchResponseData) responseData;
                    int code = touchResponseData.getCode();
                    String mes = touchResponseData.getMessage();
                    if (code == 1) {
                        Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
                    } else {
                        if (mes != null && !mes.isEmpty()) {
                            Toast.makeText(MainActivity.this, mes, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onError(Throwable e) {

                }
            }, header, getBody(), TouchResponseData.class);
        }
    }

    private Map<String, String> getloginMap(String user, String pass, String secret) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("password", pass);
        hashMap.put("secret", secret);
        hashMap.put("username", user);
        hashMap.put("app_id", "100");
        hashMap.put("grant_type", "password");
        hashMap.put("verify_type", "0");
        return hashMap;
    }

    private TouchBodyData getBody() {
        TouchBodyData bodyData = new TouchBodyData();
        bodyData.setDevice_code("866957038276949");
        bodyData.setGcj_coordate("");
        bodyData.setWgs_coordate("");
        bodyData.setSigninType(0);
        bodyData.setWifi_macaddress("24:79:2a:75:46:dc");
        return bodyData;
    }
}
