package com.khodadadzade.contact.config;


import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.khodadadzade.contact.repository")
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    String connectionString;
    @Value("${spring.data.mongodb.database}")
    String databaseName;


    @Bean
    public MongoOperations mongoTemplate(){
        MongoOperations mongoOps = new MongoTemplate(MongoClients.create(connectionString), databaseName);
        return mongoOps;
    }
}
