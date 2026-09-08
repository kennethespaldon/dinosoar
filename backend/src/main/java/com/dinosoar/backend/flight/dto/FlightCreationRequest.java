package com.dinosoar.backend.flight.dto;

import com.dinosoar.backend.flight.actionitem.dto.ActionItemCreationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FlightCreationRequest(

        @NotNull
        LocalDate date,

        @NotNull
        String aircraftTailNumber,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "100.0", inclusive = false)
        BigDecimal duration,

        boolean crossCountry,
        boolean nightFlight,

        @NotBlank
        String objective,

        List<@Valid ActionItemCreationRequest> actionItems,

        String thingsDoneWell,
        String thingsToImprove,

        @NotNull
        Long studentId,

        @NotNull
        Long instructorId,

        Long nextFlightId
        ) {}
