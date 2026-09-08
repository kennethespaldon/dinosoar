package com.dinosoar.backend.flight.dto;

import com.dinosoar.backend.flight.actionitem.dto.ActionItemDTOMapper;
import com.dinosoar.backend.flight.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class FlightCreationResponseMapper implements Function<Flight, FlightCreationResponse> {

    private final ActionItemDTOMapper actionItemDTOMapper;

    @Override
    public FlightCreationResponse apply(Flight flight) {
        Flight nextFlight = flight.getNextFlight();
        return new FlightCreationResponse(
                flight.getId(),
                flight.getDate(),
                flight.getAircraft().getTailNumber(),
                flight.getDuration(),
                flight.isCrossCountry(),
                flight.isNightFlight(),
                flight.getActionItems().stream().map(actionItemDTOMapper).toList(),
                flight.getThingsDoneWell(),
                flight.getThingsToImprove(),
                new FlightUserDTO(
                        flight.getStudent().getId(),
                        flight.getStudent().getFirstName(),
                        flight.getStudent().getLastName()
                ),
                new FlightUserDTO(
                        flight.getInstructor().getId(),
                        flight.getInstructor().getFirstName(),
                        flight.getInstructor().getLastName()
                ),
                nextFlight != null ? nextFlight.getId() : null
                );
    }
}
