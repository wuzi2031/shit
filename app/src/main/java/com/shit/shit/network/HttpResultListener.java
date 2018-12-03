package com.shit.shit.network;

import java.io.IOException;

/**
 * Created by zhimingwu on 2017/12/27.
 */

public interface HttpResultListener<T> {
    void onSuccess(T t) throws IOException;

    void onError(Throwable e);
}
