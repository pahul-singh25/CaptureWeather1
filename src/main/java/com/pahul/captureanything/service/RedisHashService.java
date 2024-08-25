package com.pahul.captureanything.service;

import com.pahul.captureanything.model.LocationToWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RedisHashService {


    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, String> hashOperations;

    @Autowired
    public RedisHashService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    // Store a key-value map in Redis
    public void saveMap(String redisKey, Map<String, String> map) {
        hashOperations.putAll(redisKey, map);
    }

    // Retrieve a key-value map from Redis
    public Map<String, String> getMap(String redisKey) {
        return hashOperations.entries(redisKey);
    }

    public void saveListString(String redisKey, List<LocationToWeather> locationToWeather) {
//        hashOperations.putAll(redisKey, map);
//        locationToWeather.forEach(locationToWeather1 -> {
//            hashOperations.put(redisKey,"email:"+locationToWeather1.getEmail(), locationToWeather1.getPreferecesTolocationIdrMap());
//;        })

    }

}
