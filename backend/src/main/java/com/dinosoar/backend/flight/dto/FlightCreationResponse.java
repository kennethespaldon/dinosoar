package com.dinosoar.backend.flight.dto;

import com.dinosoar.backend.flight.actionitem.dto.ActionItemDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FlightCreationResponse(
    Long id,
    LocalDate date,
    String aircraftTailNumber,
    BigDecimal duration,
    boolean crossCountry,
    boolean nightFlight,
    List<ActionItemDTO> actionItems,
    String thingsDoneWell,
    String thingsToImprove,
    FlightUserDTO student,
    FlightUserDTO instructor,
    Long nextFlightId
    ) {}
