package com.dinosoar.backend.aircraft;

import com.dinosoar.backend.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public Aircraft getAircraft(String tailNumber) {
        return aircraftRepository.findAircraftByTailNumber(tailNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Aircraft not found"));
    }
}
