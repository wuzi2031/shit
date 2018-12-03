package util;

import com.google.gson.Gson;

/**
 * Created by zhimingwu on 2018/1/5.
 */

public class JsonUtil {
    public static <T> T fromJson(String str, Class<T> t) {
        Gson gson = new Gson();
        T target = gson.fromJson(str, t);
        return target;
    }
}
