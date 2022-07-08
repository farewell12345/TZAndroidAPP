package com.example.bbs_new.ui.Reply;

import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**网络请求工具类*/
public class OkhttpUntil {
    public String url = "";
    public Reply result = null;
    public OkHttpClient client = new OkHttpClient();
    /**使用okhttp
     * @return*/
    public String post(String account, String topicId, String content) {
        Request request = new Request.Builder()
                .url(url)
                .post(
                        okhttp3.RequestBody.create(
                                okhttp3.MediaType.parse("application/json; charset=utf-8"),
                                "{\"topicId\":\""+topicId+"\",\"content\":\""+content+"\",\"account\":\""+account+"\"}"
                        )
                )
                .build();
        try {
            okhttp3.Response response = client.newCall(request).execute();
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    OkhttpUntil(String url){
        this.url = url;
    }
}
