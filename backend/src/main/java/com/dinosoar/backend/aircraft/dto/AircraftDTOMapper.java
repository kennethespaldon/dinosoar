package com.dinosoar.backend.aircraft.dto;

import com.dinosoar.backend.aircraft.Aircraft;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AircraftDTOMapper implements Function<Aircraft, AircraftDTO> {

    @Override
    public AircraftDTO apply(Aircraft aircraft) {
        return new AircraftDTO(
            aircraft.getId(),
            aircraft.getTailNumber()
        );
    }
}
