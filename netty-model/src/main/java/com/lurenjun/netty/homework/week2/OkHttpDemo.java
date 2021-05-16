package com.lurenjun.netty.homework.week2;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * （必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 GitHub
 */
public class OkHttpDemo {

    public static void main(String[] args) {
        String url = "http://localhost:8088";

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("调用失败!");
            }

            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("okHttpResponse:"+response.body().string());
            }
        });
    }
}
