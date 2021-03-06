package com.example.bbs_new.ui.Topic;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbs_new.databinding.ActivityTopicBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TopicActivity extends AppCompatActivity {
    private ActivityTopicBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTopicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recyclerView = binding.boardRecyclerView;
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(TopicActivity.this, AddTopicActivity.class);
            view.getContext().startActivity(intent);
        });

        updateTopicData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTopicData();
    }

    private void updateTopicData() {
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            try {
                Request request = new Request.Builder()
                        .url("http://101.42.226.88:19812/api/getAllTopic")
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                Log.d("TopicActivity", responseData);
                Gson gson = new Gson();
                TopicResponse topicResponse = gson.fromJson(responseData, TopicResponse.class);
                if (topicResponse.status.equals("200")) {
                    updateTopicUI(topicResponse.result);
                } else {
                    notifyError();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void notifyError() {
        runOnUiThread(() -> {
            Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
        });
    }

    private void updateTopicUI(List<TopicResponse._Topic> data) {
        runOnUiThread(() -> {
            List<Topic> topicList = new ArrayList<>();
            for (TopicResponse._Topic item : data) {
                Topic topic = new Topic(item.title, "??????", item.content, item.createTime);
                topicList.add(topic);
            }
            TopicAdapter topicAdapter = new TopicAdapter();
            topicAdapter.setTopicList(topicList);
            recyclerView.setAdapter(topicAdapter);
        });
    }

    public static class TopicResponse {
        public String status;
        public String msg;
        public List<_Topic> result;

        public static class _Topic {
            public Long id;
            public String title;
            public String content;
            public String image;
            public String createTime;
            public String updateTime;
        }
    }
}
