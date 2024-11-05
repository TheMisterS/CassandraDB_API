package com.cassandra_api.cassandra_api.dto;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("messages")
public class Message {

    @Column("text")
    String text;

    @PrimaryKeyColumn(name = "author", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    String author;

    @PrimaryKeyColumn(name = "timestamp", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    int timestamp;

    @PrimaryKeyColumn(name = "channelId", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    String channelId;



    public Message() {
    }

    public Message(String text, String author, int timestamp, String channelId) {
        this.text = text;
        this.author = author;
        this.timestamp = timestamp;
        this.channelId = channelId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
