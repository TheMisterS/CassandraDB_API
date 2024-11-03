package com.cassandra_api.cassandra_api.utility;

public class messageRequest {

    String author;
    int startAt;

    public messageRequest(int startAt, String author) {
        this.startAt = startAt;
        this.author = author;
    }

    public messageRequest() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }
}
