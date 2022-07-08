package com.example.bbs_new.ui.Reply;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bbs_new.R;
import com.example.bbs_new.databinding.AddReplyLayoutBinding;
import com.example.bbs_new.ui.Topic.Topic;
import com.example.bbs_new.util.SPUtil;
import com.google.android.material.textfield.TextInputEditText;

public class AddReplyActivity extends AppCompatActivity {

    private AddReplyLayoutBinding  binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AddReplyLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // 获取传递过来的topic对象
        Topic topic = getIntent().getParcelableExtra("topic");

        binding.doneButton.setOnClickListener(view -> {
            String content = binding.addReplyContent.toString();
            addRely(content,String.valueOf(topic.getId()), SPUtil.loadToken(this));
            finish();
        });

    }

    private void addRely(String content, String topicId, String account) {
        OkhttpUntil okhttpUntil = new OkhttpUntil("http://" + getString(R.string.ip) + "/reply");
        okhttpUntil.post(account, topicId, content);
    }

}

