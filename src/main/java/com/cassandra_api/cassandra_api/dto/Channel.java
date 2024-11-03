package com.cassandra_api.cassandra_api.dto;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.util.Set;


@Table("channels")
public class Channel {

    @PrimaryKey
    String id;

    @Column("owner")
    String owner;

    @Column("topic")
    String topic;

    @Column("members")
    private Set<String> members;


    public Channel() {
    }

    public Channel(String id, String owner, String topic, Set<String> members) {
        this.id = id;
        this.owner = owner;
        this.topic = topic;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Set<String> getMembers() {
        return members;
    }

    public void setMembers(Set<String> members) {
        this.members = members;
    }
}
