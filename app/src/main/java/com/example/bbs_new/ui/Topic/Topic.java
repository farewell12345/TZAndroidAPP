package com.example.bbs_new.ui.Topic;

import android.os.Parcel;
import android.os.Parcelable;

public class Topic implements Parcelable {
    private String title;
    private String userName;
    private String content;
    private String time;
    private Long id;

    protected Topic(Parcel in) {
        title = in.readString();
        userName = in.readString();
        content = in.readString();
        time = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
    }

    public static final Creator<Topic> CREATOR = new Creator<Topic>() {
        @Override
        public Topic createFromParcel(Parcel in) {
            return new Topic(in);
        }

        @Override
        public Topic[] newArray(int size) {
            return new Topic[size];
        }
    };

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Topic(String title, String userName, String content, String time) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(userName);
        parcel.writeString(content);
        parcel.writeString(time);
        parcel.writeLong(id);
    }
}
