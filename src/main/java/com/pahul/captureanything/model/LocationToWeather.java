package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonSerialize
@JsonRootName(value="")
@Document(collection = "user")
@RedisHash("user")
public class LocationToWeather implements Serializable {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private Map<String, List<String>> preferecesTolocationIdrMap;

    private Map<String,Map<LocalDateTime,Current>> localtionIdToWeatherDataIdMap;

}
