package com.pahul.captureanything;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
@EnableRedisRepositories(basePackages = "com.pahul.captureanything.redis.repositories")
public class CaptureAnythingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CaptureAnythingApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CaptureAnythingApplication.class);
    }

}
