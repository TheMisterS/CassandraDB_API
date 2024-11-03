package com.cassandra_api.cassandra_api;


import com.cassandra_api.cassandra_api.dto.Channel;
import com.cassandra_api.cassandra_api.dto.ChannelRepository;
import com.cassandra_api.cassandra_api.dto.Message;
import com.cassandra_api.cassandra_api.dto.MessageRepository;
import com.cassandra_api.cassandra_api.utility.IdMap;
import com.cassandra_api.cassandra_api.utility.messageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.util.Optional;

@RestController
public class APIController {


    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private MessageRepository messageRepository;

    @PutMapping("/channels")
    public ResponseEntity<?> registerANewChannel(@RequestBody Channel channel){

        if(channel.getId().isBlank() || channel.getId() == null){
            return  new ResponseEntity("Invalid ID", HttpStatus.BAD_REQUEST);
        }else if(channel.getOwner().isBlank() || channel.getOwner() == null){
            return  new ResponseEntity("Invalid Owner", HttpStatus.BAD_REQUEST);
        }else if(channel.getTopic().isBlank() || channel.getTopic() == null){
            return  new ResponseEntity("Invalid Topic", HttpStatus.BAD_REQUEST);
        }
        Optional<Channel> channelTest = channelRepository.findById(channel.getId());
        if(channelTest.isPresent()) {
            return new ResponseEntity<>("Channel with such Id already exists.", HttpStatus.OK);
        }
        channelRepository.save(channel);

        IdMap indexId = new IdMap(channel.getId());
        return new ResponseEntity(indexId, HttpStatus.CREATED);
    }

    @GetMapping("/channels/{channelId}")
    public ResponseEntity<?> getChannelById (@PathVariable String channelId){
        Optional<Channel> channel = channelRepository.findById(channelId);
        if(channel.isPresent()){
                return  new ResponseEntity<>(channel, HttpStatus.OK);
        }else{
                return  new ResponseEntity<>("Channel with the ID " + channelId + " was not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/channels/{channelId}")
    public ResponseEntity <?> deleteChannelById (@PathVariable String channelId){
        Optional<Channel> channel = channelRepository.findById(channelId);
        if(channel.isPresent()){
            channelRepository.deleteById(channelId);
            return  new ResponseEntity<>(channel, HttpStatus.NO_CONTENT);
        }else{
            return  new ResponseEntity<>("Channel with the ID " + channelId + " was not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("channels/{channelId}/messages")
    public ResponseEntity <?> AddMessageToChannel (@PathVariable String channelId, @RequestBody Message message){
        Optional<Channel> channel = channelRepository.findById(channelId);

        if(channel.isEmpty()){
            return  new ResponseEntity<>("Channel with the ID " + channelId + " was not found", HttpStatus.NOT_FOUND);
        }/*else if(!(channel.get().getMembers().contains(message.getAuthor()))){
            return  new ResponseEntity<>("The author is not a member of the channel", HttpStatus.NOT_FOUND);
        }*/else if(message.getAuthor().isBlank() || message.getAuthor() == null){
            return  new ResponseEntity<>("Invalid author", HttpStatus.BAD_REQUEST);
        } else if (message.getText().isBlank() || message.getText() == null) {
            return  new ResponseEntity<>("Invalid text", HttpStatus.BAD_REQUEST);
        }
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        int timestamp = Integer.parseInt(now.format(formatter));

        message.setChannelId(channelId);
        message.setTimestamp(timestamp);


        messageRepository.save(message);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("channels/{channelId}/messages")
    public ResponseEntity <?> getMessagesFromChannel (@PathVariable String channelId, @RequestBody messageRequest messageRequest){
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isEmpty()) {
            return new ResponseEntity<>("Channel with the ID " + channelId + " was not found", HttpStatus.NOT_FOUND);
        }
    }
