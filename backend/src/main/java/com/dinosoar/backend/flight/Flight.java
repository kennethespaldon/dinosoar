package com.dinosoar.backend.flight;

import com.dinosoar.backend.aircraft.Aircraft;
import com.dinosoar.backend.flight.actionitem.ActionItem;
import com.dinosoar.backend.user.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "flights")
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "aircraft_id")
    @Valid
    private Aircraft aircraft;

    @Column(nullable = false, precision = 3, scale = 1)
    private BigDecimal duration;

    @Column(nullable = false)
    private boolean crossCountry;

    @Column(nullable = false)
    private boolean nightFlight;

    private String objective;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<@Valid ActionItem> actionItems;

    private String thingsDoneWell;

    private String thingsToImprove;

    @OneToOne
    @JoinColumn(name = "next_flight_id")
    @Valid
    private Flight nextFlight;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = false)
    @Valid
    private User student;

    @OneToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    @Valid
    private User instructor;

    private String feedback;

    public Flight() {

    }

    public Flight(LocalDate date, Aircraft aircraft, BigDecimal duration, boolean crossCountry, boolean nightFlight, String objective, List<ActionItem> actionItems, String thingsDoneWell, String thingsToImprove, User student, User instructor, Flight nextFlight) {
        this.date = date;
        this.aircraft = aircraft;
        this.duration = duration;
        this.crossCountry = crossCountry;
        this.nightFlight = nightFlight;
        this.objective = objective;
        this.actionItems = actionItems;
        this.thingsDoneWell = thingsDoneWell;
        this.thingsToImprove = thingsToImprove;
        this.student = student;
        this.instructor = instructor;
        this.nextFlight = nextFlight;
    }
}
