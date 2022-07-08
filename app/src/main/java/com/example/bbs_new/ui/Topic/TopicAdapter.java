package com.example.bbs_new.ui.Topic;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbs_new.R;
import com.example.bbs_new.ui.TopicDetail.TopicDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.ViewHolder> {

    List<Topic> topicList = new ArrayList<>();

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(
                view1 -> {
                    int position = viewHolder.getAdapterPosition();
                    Topic topic = topicList.get(position);
                    // 创建Intent对象,传递topic
                    Intent intent = new Intent(view1.getContext(), TopicDetailActivity.class);
                    intent.putExtra("topic", topic);
                    view1.getContext().startActivity(intent);
                }
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Topic item = topicList.get(position);

        holder.getTime().setText(item.getTime());
        holder.getTitle().setText(item.getTitle());
        holder.getUser().setText(item.getUserName());
        holder.getContent().setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private TextView user;
        private TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.front_page_item_title);
            content = itemView.findViewById(R.id.front_page_item_content);
            user = itemView.findViewById(R.id.front_page_item_user);
            time = itemView.findViewById(R.id.front_page_item_time);
        }

        public TextView getTitle() {
            return title;
        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public TextView getContent() {
            return content;
        }

        public void setContent(TextView content) {
            this.content = content;
        }

        public TextView getUser() {
            return user;
        }

        public void setUser(TextView user) {
            this.user = user;
        }

        public TextView getTime() {
            return time;
        }

        public void setTime(TextView time) {
            this.time = time;
        }
    }


}
