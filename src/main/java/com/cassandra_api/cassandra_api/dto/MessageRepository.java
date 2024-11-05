package com.cassandra_api.cassandra_api.dto;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, String> {


    @Query
    List<Message> findByChannelId(String channelId);
    @Query
    List<Message> findByChannelIdAndAuthorAndTimestampGreaterThanEqual(String channelId, String author, int startAt);
    @Query
    List<Message> findByChannelIdAndAuthor(String channelId, String author);
    //@Query
    //List<Message> findByChannelIdAndTimestampGreaterThanEqual(String channelId, int startAt);
    @Query("SELECT * FROM messages WHERE \"channelId\" = :channelId AND \"timestamp\" >= :timestamp ALLOW FILTERING")
    List<Message> findByChannelIdAndTimestampGreaterThanEqual(@Param("channelId") String channelId, @Param("timestamp") int timestamp);




}
