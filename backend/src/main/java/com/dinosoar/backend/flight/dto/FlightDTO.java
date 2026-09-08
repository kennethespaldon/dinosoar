package com.dinosoar.backend.flight.dto;

import com.dinosoar.backend.aircraft.dto.AircraftDTO;
import com.dinosoar.backend.flight.actionitem.dto.ActionItemDTO;
import com.dinosoar.backend.user.dto.UserDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FlightDTO (
        LocalDate date,
        AircraftDTO aircraft,
        BigDecimal duration,
        boolean crossCountry,
        boolean nightFlight,
        String objective,
        List<ActionItemDTO> actionItems,
        String thingsDoneWell,
        String thingsToImprove,
        UserDTO student,
        UserDTO instructor,
        Long nextFlightId,
        String feedback
) {}
