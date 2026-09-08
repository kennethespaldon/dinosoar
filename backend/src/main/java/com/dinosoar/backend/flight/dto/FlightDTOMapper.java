package com.dinosoar.backend.flight.dto;

import com.dinosoar.backend.aircraft.dto.AircraftDTOMapper;
import com.dinosoar.backend.flight.Flight;
import com.dinosoar.backend.flight.actionitem.dto.ActionItemDTOMapper;
import com.dinosoar.backend.user.dto.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FlightDTOMapper implements Function<Flight, FlightDTO> {

    private final UserDTOMapper userDTOMapper;
    private final AircraftDTOMapper aircraftDTOMapper;
    private final ActionItemDTOMapper actionItemDTOMapper;

    @Override
    public FlightDTO apply(Flight flight) {
        Flight nextFlight = flight.getNextFlight();
        return new FlightDTO(
                flight.getDate(),
                aircraftDTOMapper.apply(flight.getAircraft()),
                flight.getDuration(),
                flight.isCrossCountry(),
                flight.isNightFlight(),
                flight.getObjective(),
                flight.getActionItems().stream().map(actionItemDTOMapper).toList(),
                flight.getThingsDoneWell(),
                flight.getThingsToImprove(),
                userDTOMapper.apply(flight.getStudent()),
                userDTOMapper.apply(flight.getInstructor()),
                nextFlight != null ? nextFlight.getId() : null,
                flight.getFeedback()
        );
    }
}