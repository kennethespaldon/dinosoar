package com.dinosoar.backend.flight;

import com.dinosoar.backend.flight.dto.*;
import com.dinosoar.backend.exception.FlightNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {

    // TODO [done]: create: POST /
    // TODO [done]: get one: GET /{id}
    // TODO [done]: get all flights: GET /
    // TODO: update: PATCH /{id}
    // TODO [done]: delete: DELETE /{id}
    private final FlightService flightService;
    private final FlightCreationResponseMapper flightCreationResponseMapper;
    private final FlightDTOMapper flightDTOMapper;

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getFlight(@PathVariable Long id) {
        try {
            Flight flight = flightService.getFlightById(id);
            FlightDTO flightDTO = flightDTOMapper.apply(flight);
            return ResponseEntity.ok(flightDTO);
        } catch (FlightNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<FlightDTO> getAllFlights(Sort sort) {
        return flightService
                .getAllFlights(sort)
                .stream()
                .map(flightDTOMapper)
                .toList();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateFlight() {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlightById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping
    public ResponseEntity<FlightCreationResponse> createFlight(
            @Valid @RequestBody FlightCreationRequest flightCreationRequest
    ) {
        Flight addedFlight = flightService.addFlight(flightCreationRequest);
        FlightCreationResponse flightCreationResponse = flightCreationResponseMapper.apply(addedFlight);

        return ResponseEntity
                .created(URI.create("/api/flights/" + flightCreationResponse.id()))
                .body(flightCreationResponse);
    }
}