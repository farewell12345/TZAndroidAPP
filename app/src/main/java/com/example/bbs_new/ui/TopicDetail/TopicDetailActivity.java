package com.example.bbs_new.ui.TopicDetail;

import android.content.Intent;
import android.os.Bundle;

import com.example.bbs_new.databinding.ActivityTopicDetailBinding;
import com.example.bbs_new.ui.Reply.AddReplyActivity;
import com.example.bbs_new.ui.Topic.Topic;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class TopicDetailActivity extends AppCompatActivity {

    private ActivityTopicDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTopicDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Topic topic = new Intent(TopicDetailActivity.this, TopicDetailActivity.class).getParcelableExtra("topic");
        RecyclerView recyclerView = binding.replyRecyclerView;
        TopicDetailAdapter topicDetailAdapter = new TopicDetailAdapter();
        topicDetailAdapter.setTopic(topic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(TopicDetailActivity.this, AddReplyActivity.class);
            // 传递topicList
            intent.putExtra("topic", topic);
            view.getContext().startActivity(intent);
        });
    }

}