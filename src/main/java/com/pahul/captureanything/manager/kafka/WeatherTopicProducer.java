package com.pahul.captureanything.manager.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pahul.captureanything.manager.ExternalCallManager;
import com.pahul.captureanything.model.WeatherData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.retrytopic.DestinationTopic;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class WeatherTopicProducer {
    @Autowired
    ExternalCallManager externalCallManager;
    private static final String TOPIC = "weather-now";

    public String currentWeatherProducer(String address) throws Exception {
        var props = new Properties();
        props.put("bootstrap.servers", "https://epic-mink-5018-us1-kafka.upstash.io:9092");
        props.put("sasl.mechanism", "SCRAM-SHA-256");
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"********\" password=\"********\";");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (var producer = new KafkaProducer<String, String>(props)) {
            String weatherJson = (new ObjectMapper()).writeValueAsString(externalCallManager.getWeatherInfo(address));
                // Send the JSON data to Kafka
            if(weatherJson!=null){
                ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, weatherJson);
                producer.send(record);
                System.out.println("Weather data sent to Kafka topic: " + TOPIC);
                return weatherJson;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        return null;
    }
}
