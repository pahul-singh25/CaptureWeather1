package com.pahul.captureanything.manager.kafka;

import org.springframework.stereotype.Component;

@Component
public class WeatherTopicConsumer {
//    @Autowired
//    WeatherService weatherService;

//    @Autowired
//    private KafkaConsumer<String,String> consumer;
//
//    @Value("${weather.producer.topic}")
//    private String topic;
//
//    /**
//     * Consumes weather data from a Kafka topic and returns the number of messages consumed.
//     *
//     * @return  the number of messages consumed from the Kafka topic
//     */
//    public Integer currentWeatherConsumer() {
//        consumer.subscribe(Collections.singletonList(topic));
//        int consummedCount = 0;
//
//        try {
//            // Poll for records
//            boolean messageFound = false;
//            while (!messageFound) {
//                Thread.sleep(5000);
//                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
//                for (ConsumerRecord<String, String> record : records) {
//                    System.out.println("Received message: " + record.value());
//                    messageFound = true;
//                    consummedCount++;
////                ObjectMapper objectMapper = new ObjectMapper();
////                WeatherData weatherData = objectMapper.readValue(record.value().getBytes(StandardCharsets.UTF_8), WeatherData.class);
////                weatherService.save(weatherData);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
////            consumer.close();
//        }
//        return  consummedCount;
//    }
//
//    @PreDestroy
//    public void closeProducer() {
//        if (consumer != null) {
//            consumer.close();
//            System.out.println("Kafka consumer closed.");
//        }
//    }
}
