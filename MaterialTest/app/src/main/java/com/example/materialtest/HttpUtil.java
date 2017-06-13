package com.example.materialtest;

import android.text.TextUtils;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jarvis on 2017/2/27.
 */

public class HttpUtil {
    private CookieHandler cookieHandler;
    private static OkHttpClient client;
    private HttpUtil() {
        initOkHttpFunction();
    }
    private static class OkHttpSingle{
        private static HttpUtil sCommonOkhttpUtils = new HttpUtil();
    }
    public static HttpUtil getUtilsInstance(){
        return  OkHttpSingle.sCommonOkhttpUtils;
    }
    private void initOkHttpFunction() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //设置缓存 以及超时间 以及获取OkHttpClient对象
        client = builder.writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        }

    public OkHttpClient getInstance() {
        if (client == null) {
            synchronized (HttpUtil.class) {
                if (client == null)

                    client = new OkHttpClient.Builder()
                            .cookieJar(new CookieJar() {
                                private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
                                @Override
                                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                                    cookieStore.put(url, cookies);
                                }

                                @Override
                                public List<Cookie> loadForRequest(HttpUrl url) {
                                    List<Cookie> cookies = cookieStore.get(url);
                                    return cookies != null ? cookies : new ArrayList<Cookie>();
                                }
                            })
                            .build();
            }
        }
        return client;
    }
    private  static String cookieHeader(List<Cookie> cookies) {
        StringBuilder cookieHeader = new StringBuilder();
        for (int i = 0, size = cookies.size(); i < size; i++) {
            if (i > 0) {
                cookieHeader.append("; ");
            }
            Cookie cookie = cookies.get(i);
            cookieHeader.append(cookie.name()).append('=').append(cookie.value());
        }
        return cookieHeader.toString();
    }

    public  static void sendOkHttpRequest(String address, String cookie, Callback callback){
        Request request;
        if(!TextUtils.isEmpty(cookie)) {
            request = new Request.Builder().addHeader("cookie", cookie).url(address).build();
        }
        else {
            request = new Request.Builder().url(address).build();
        }

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    /**
     * Post请求发送JSON数据
     *
     * @param url
     * @param jsonParams
     * @param callback
     */
    public static void doPost(String url, String cookie, String jsonParams, Callback callback) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonParams);
        Request request;
        if(!TextUtils.isEmpty(cookie)) {
            request = new Request.Builder().addHeader("cookie", cookie)
                    .url(url)
                    .post(body)
                    .build();
        }
        else {
            request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        }
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
