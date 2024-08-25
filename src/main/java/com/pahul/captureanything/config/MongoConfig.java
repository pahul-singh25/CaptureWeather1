package com.pahul.captureanything.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.pahul.captureanything.mongo.repositories")
public class MongoConfig {
//    @Value("${spring.data.mongodb.uri}")
//    private String uri;

//    @Bean
//    @Qualifier("${mongoDatabaseFactory}")
//    public MongoDatabaseFactory mongoDbFactory() {
//        return new SimpleMongoClientDatabaseFactory(uri);
//    }
//
//    @Bean
//    @ConditionalOnBean({MongoDatabaseFactory.class})
//    @Primary
//    public MongoTemplate mongoTemplate(@Autowired  MongoDatabaseFactory mongoDatabaseFactory) {
//        return new MongoTemplate(mongoDatabaseFactory);
//    }

}
