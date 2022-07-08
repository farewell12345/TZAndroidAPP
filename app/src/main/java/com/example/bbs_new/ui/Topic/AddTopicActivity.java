package com.example.bbs_new.ui.Topic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.bbs_new.MainActivity;
import com.example.bbs_new.R;
import com.example.bbs_new.databinding.ActivityAddTopicBinding;
import com.example.bbs_new.util.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddTopicActivity extends AppCompatActivity {

    private ActivityAddTopicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddTopicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.doneButton.setOnClickListener(view -> {
            String title = binding.addTopicTitle.getContext().toString();
            String content = binding.addTopicContent.getContext().toString();
            new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("title",title);
                    jsonObject.put("content",content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));
                Request request = new Request.Builder()
                        .url("http://101.42.226.88:19812/api/login")
                        .addHeader("Authorization","Bearer "+ SPUtil.loadToken(AddTopicActivity.this))
                        .post(requestBody)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        System.out.println("1");
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if(response.isSuccessful()){
                            finish();
                        }
                    }
                });
            }).start();
        });
    }
}