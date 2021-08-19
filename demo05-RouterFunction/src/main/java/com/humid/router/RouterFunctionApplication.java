package com.humid.router;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class RouterFunctionApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(RouterFunctionApplication.class, args);
    }
}
