package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonSerialize
@JsonRootName(value="")
@Document(collection = "user")
@RedisHash("user")
public class User implements Serializable {
    @Id
    private String id;

    private String name;

    @Indexed(unique = true)
    private String email;
    private String country;
    private List<String> preference = Lists.newArrayList();
}
