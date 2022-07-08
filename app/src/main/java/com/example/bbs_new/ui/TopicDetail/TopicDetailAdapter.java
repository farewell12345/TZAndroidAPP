package com.example.bbs_new.ui.TopicDetail;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bbs_new.R;
import com.example.bbs_new.ui.Reply.Reply;
import com.example.bbs_new.ui.Topic.Topic;

import java.util.ArrayList;
import java.util.List;


public class TopicDetailAdapter extends RecyclerView.Adapter<TopicDetailAdapter.ViewHolder> {



    List<Reply> replyList = new ArrayList<>();
    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new ViewHolder(view);
    }

    public void setTopic(Topic topic) {

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reply item = replyList.get(position);

        holder.getTime().setText(item.getTime());
        holder.getUser().setText(item.getUser());
        holder.getContent().setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        TextView user;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.reply_item_content);
            user = itemView.findViewById(R.id.reply_item_user);
            time = itemView.findViewById(R.id.reply_item_time);
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
