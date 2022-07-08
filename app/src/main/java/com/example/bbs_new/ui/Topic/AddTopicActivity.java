package com.example.bbs_new.ui.Topic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
            String title = binding.addTopicTitle.getText().toString();
            String content = binding.addTopicContent.getText().toString();
            String account = "aimer"; // TODO wait for impl
            new Thread(() -> {
                try {
                    OkHttpClient client = new OkHttpClient();
                    String json = new JSONObject()
                            .put("title", title)
                            .put("content", content)
                            .put("account", account)
                            .toString();
                    RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
                    Request request = new Request.Builder()
                            .url("http://101.42.226.88:19812/api/topic")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    if (response.code() != 200) {
                        notifyError();
                        Log.d("AddTopicActivity", response.toString());
                    } else {
                        notifySuccess();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    notifyError();
                }
            }).start();
            finish();
        });
    }

    private void notifyError() {
        runOnUiThread(() -> {
            Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
        });
    }

    private void notifySuccess() {
        runOnUiThread(() -> {
            Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
        });
    }
}