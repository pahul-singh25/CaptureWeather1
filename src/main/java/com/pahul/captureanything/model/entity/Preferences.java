package com.pahul.captureanything.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "preferences")
@Data
public class Preferences {
    @Id
    @Column(insertable=false, updatable=false)
    private Long id;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    private User user;
}
