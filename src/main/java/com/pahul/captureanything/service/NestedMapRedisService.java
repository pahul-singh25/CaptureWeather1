package com.pahul.captureanything.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NestedMapRedisService {

    private final HashOperations<String, String, String> hashOperations;

    @Autowired
    public NestedMapRedisService(RedisTemplate<String, String> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    // Store nested map in Redis
    public void storeNestedMap(String redisKey, Map<String, Map<String, String>> nestedMap) {
        Map<String, String> flatMap = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : nestedMap.entrySet()) {
            String outerKey = entry.getKey();
            for (Map.Entry<String, String> innerEntry : entry.getValue().entrySet()) {
                flatMap.put(outerKey + "." + innerEntry.getKey(), innerEntry.getValue());
            }
        }
        hashOperations.putAll(redisKey, flatMap);
    }

    // Retrieve nested map from Redis
    public Map<String, Map<String, String>> retrieveNestedMap(String redisKey) {
        Map<String, String> flatMap = hashOperations.entries(redisKey);
        Map<String, Map<String, String>> nestedMap = new HashMap<>();

        for (Map.Entry<String, String> entry : flatMap.entrySet()) {
            String[] keys = entry.getKey().split("\\.");
            String outerKey = keys[0];
            String innerKey = keys[1];
            nestedMap.putIfAbsent(outerKey, new HashMap<>());
            nestedMap.get(outerKey).put(innerKey, entry.getValue());
        }

        return nestedMap;
    }

    public Map<String, Map<String, String>> retrieveNestedMap(String redisKey, String locationId) {
        Map<String, String> flatMap = hashOperations.entries(redisKey);
        Map<String, Map<String, String>> nestedMap = new HashMap<>();
        flatMap.get(locationId);
        for (Map.Entry<String, String> entry : flatMap.entrySet()) {
            String[] keys = entry.getKey().split("\\.");
            String outerKey = keys[0];
            String innerKey = keys[1];
            nestedMap.putIfAbsent(outerKey, new HashMap<>());
            nestedMap.get(outerKey).put(innerKey, entry.getValue());
        }

        return nestedMap;
    }

    public String retrieveValue(String redisKey, String outerKey, String innerKey) {
        // Combine outer and inner keys using a delimiter
        String combinedKey = outerKey + "." + innerKey;

        // Fetch the value from Redis
        return hashOperations.get(redisKey, combinedKey);
    }

}
