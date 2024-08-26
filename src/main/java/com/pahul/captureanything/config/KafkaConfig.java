package com.pahul.captureanything.config;

import com.pahul.captureanything.model.WeatherData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaConfig {

    @Value("weather.consumer.groupId")
    private String groupId;

    public KafkaConsumer<String,String> kafkaConsumer(){
        var props = new Properties();
        props.put("bootstrap.servers", "https://epic-mink-5018-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ZXBpYy1taW5rLTUwMTgkmWwXA4TDCl9AukmZYBcZbR9s9ckR3aZAg8R2a6NWD6s\" password=\"YTBhZTliM2ItMWM5MC00ZjlhLWE5NmYtZDc2Y2UwNzU1Yjgw\";");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        return new KafkaConsumer<>(props);
    }

    public KafkaProducer<String,String> kafkaProducer(){
        var props = new Properties();
        props.put("bootstrap.servers", "https://epic-mink-5018-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ZXBpYy1taW5rLTUwMTgkmWwXA4TDCl9AukmZYBcZbR9s9ckR3aZAg8R2a6NWD6s\" password=\"YTBhZTliM2ItMWM5MC00ZjlhLWE5NmYtZDc2Y2UwNzU1Yjgw\";");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return  new KafkaProducer<>(props);
    }

    @Bean
    public KafkaProducer<String, WeatherData> kafkaProducer2(){
        var props = new Properties();
        props.put("bootstrap.servers", "https://epic-mink-5018-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ZXBpYy1taW5rLTUwMTgkmWwXA4TDCl9AukmZYBcZbR9s9ckR3aZAg8R2a6NWD6s\" password=\"YTBhZTliM2ItMWM5MC00ZjlhLWE5NmYtZDc2Y2UwNzU1Yjgw\";");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return  new KafkaProducer<>(props);
    }
}
