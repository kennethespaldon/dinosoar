package com.dinosoar.backend.resource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resources")
@Getter
@Setter
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String storageKey;

    public Resource() {

    }

    public Resource(String storageKey) {
        this.storageKey = storageKey;
    }
}
