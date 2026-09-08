package com.dinosoar.backend.aircraft;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aircraft")
@Getter
@Setter
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String tailNumber;

    public Aircraft() {}

    public Aircraft(String tailNumber) {
        this.tailNumber = tailNumber;
    }
}
