package com.pahul.captureanything.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@JsonSerialize
@JsonRootName(value="")
@Document("Location")
public class Location implements Serializable {
    @Id
    private String id;
    private String country;
    private String city;

    private Double latitude;
    private Double longitude;
    private String displayName;
}
