package com.example.bbs_new.ui.Reply;

public class Reply {
    String content;
    String user;
    String time;

    public Reply(String content, String user, String time) {
        this.content = content;
        this.user = user;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
