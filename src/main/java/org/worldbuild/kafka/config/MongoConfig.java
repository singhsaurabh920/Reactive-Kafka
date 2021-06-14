package org.worldbuild.kafka.config;


import com.mongodb.ConnectionString;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Log4j2
@Profile("ck")
@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Autowired
    private MongoProperties mongoProperties;

    @Bean
    @Override
    public MongoClient reactiveMongoClient() {
        final String uri=mongoProperties.getUri();
        log.info(uri);
        ConnectionString connectionString=new ConnectionString(uri);
        return MongoClients.create(connectionString);
    }

    @Override
    protected String getDatabaseName() {
        return mongoProperties.getDatabase();
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
    }
}

