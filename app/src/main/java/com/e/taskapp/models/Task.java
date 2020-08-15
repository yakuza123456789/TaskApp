package com.e.taskapp.models;

import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private long createdAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public Task(String title, long createdAt) {
        this.title = title;
        this.createdAt = createdAt;
    }
}
