package com.pahul.captureanything.manager.kafka;

import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;

@Component
public class WeatherTopicConsumer {
//    @Autowired
//    WeatherService weatherService;

    @Autowired
    private KafkaConsumer<String,String> consumer;

    @Value("${weather.producer.topic}")
    private String topic;

    public Integer currentWeatherConsumer() {
//        var props = new Properties();
//        props.put("bootstrap.servers", "https://epic-mink-5018-us1-kafka.upstash.io:9092");
//        props.put("sasl.mechanism", "SCRAM-SHA-256");
//        props.put("security.protocol", "SASL_SSL");
//        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ZXBpYy1taW5rLTUwMTgkmWwXA4TDCl9AukmZYBcZbR9s9ckR3aZAg8R2a6NWD6s\" password=\"********\");");
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
//
//        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        int consummedCount = 0;

        try {
            // Poll for records
            boolean messageFound = false;
            while (!messageFound) {
                Thread.sleep(5000);
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Received message: " + record.value());
                    messageFound = true;
                    consummedCount++;
//                ObjectMapper objectMapper = new ObjectMapper();
//                WeatherData weatherData = objectMapper.readValue(record.value().getBytes(StandardCharsets.UTF_8), WeatherData.class);
//                weatherService.save(weatherData);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            consumer.close();
        }
        return  consummedCount;
    }

    @PreDestroy
    public void closeProducer() {
        if (consumer != null) {
            consumer.close();
            System.out.println("Kafka consumer closed.");
        }
    }
}
