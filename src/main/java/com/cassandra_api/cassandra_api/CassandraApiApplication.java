package com.cassandra_api.cassandra_api;

import com.cassandra_api.cassandra_api.dto.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class 		CassandraApiApplication {



	public static void main(String[] args) {
		SpringApplication.run(CassandraApiApplication.class, args);
	}

}
