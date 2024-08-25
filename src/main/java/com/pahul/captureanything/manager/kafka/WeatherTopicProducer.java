package com.pahul.captureanything.manager.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pahul.captureanything.manager.ExternalCallManager;
import com.pahul.captureanything.model.WeatherData;
import jakarta.annotation.PreDestroy;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class WeatherTopicProducer {
    @Autowired
    ExternalCallManager externalCallManager;

    @Autowired
    private KafkaProducer<String,String> producer;

//    @Autowired
    //private KafkaTemplate<String, WeatherData> kafkaTemplate;

    @Autowired
    @Qualifier("kafkaProducer2")
    private KafkaProducer<String,WeatherData> kafkaTemplate;

    @Value("${weather.producer.topic}")
    private String topic;


    public String currentWeatherProducer(String address) throws Exception {
        try  {
            String weatherJson = (new ObjectMapper()).writeValueAsString(externalCallManager.getWeatherInfo(address));
                // Send the JSON data to Kafka
            if(weatherJson!=null){
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, weatherJson);
                Future<RecordMetadata> future = producer.send(record);
                // Ensure the send operation completes successfully
                RecordMetadata metadata = future.get();
                System.out.println("Weather data sent to Kafka topic: " + topic + " at offset: " + metadata.offset());
                return weatherJson;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            producer.close();
        }
        return null;
    }


    @PreDestroy
    public void closeProducer() {
        if (producer != null) {
            producer.close();
            System.out.println("Kafka producer closed.");
        }
    }

    public void sendWeatherData(String address, WeatherData weatherData) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, weatherData.toString());
        producer.send(record);
    }
}
