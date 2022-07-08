package com.example.bbs_new;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bbs_new.databinding.ActivityLoginBinding;
import com.example.bbs_new.ui.Register.RegisterActivity;
import com.example.bbs_new.ui.Topic.TopicActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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


public class MainActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            view.getContext().startActivity(intent);
        });


        binding.loginButton.setOnClickListener(view -> {
            String account = binding.account.getText().toString();
            String password = binding.password.getText().toString();
            System.out.println(account);
            System.out.println(password);
            new Thread(() -> {
                OkHttpClient client = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("account",account);
                    jsonObject.put("password",password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody requestBody = RequestBody.create(JSON, String.valueOf(jsonObject));
                Request request = new Request.Builder()
                        .url("http://101.42.226.88:19812/api/login")
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
                            System.out.println(response);
                            Intent intent = new Intent(MainActivity.this, TopicActivity.class);
                            view.getContext().startActivity(intent);
                            finish();
                        }
                    }
                });
            }).start();

        });

    }

}