package com.example.webflux.uniconn.common;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.example.webflux.uniconn")
public class MongoDBConfig {

    // index가 안먹나? unique 테스트가 통과안함
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient) {
        ReactiveMongoTemplate mongoTemplate = new ReactiveMongoTemplate(mongoClient, "test");
        mongoTemplate.indexOps("users").ensureIndex(
                new Index("email", Sort.Direction.ASC).unique().sparse()
        );
        return mongoTemplate;
    }
}
