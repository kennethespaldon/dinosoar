package com.dinosoar.backend.flight;

import com.dinosoar.backend.aircraft.Aircraft;
import com.dinosoar.backend.flight.actionitem.ActionItem;
import com.dinosoar.backend.flight.dto.FlightCreationRequest;
import com.dinosoar.backend.exception.FlightNotFoundException;
import com.dinosoar.backend.flight.actionitem.note.Note;
import com.dinosoar.backend.resource.ResourceRepository;
import com.dinosoar.backend.resource.Resource;
import com.dinosoar.backend.aircraft.AircraftService;
import com.dinosoar.backend.user.User;
import com.dinosoar.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AircraftService aircraftService;
    private final ResourceRepository resourceRepository; // change this to ResourceService
    private final UserService userService;

    public boolean checkIfFlightExistsById(Long id) {
        return flightRepository.existsById(id);
    }

    public Flight getFlightById(Long id) {
        if (!checkIfFlightExistsById(id)) {
           throw new FlightNotFoundException("Flight with id [" + id + "] not found.");
        }

        return flightRepository.findFlightById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight with id [" + id + "] not found."));
    }

    public List<Flight> getAllFlights(Sort sort) {
        return flightRepository.findAll(sort);
    }

    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }

    public Flight addFlight(FlightCreationRequest flightCreationRequest) {
        Aircraft aircraft = aircraftService.getAircraft(flightCreationRequest.aircraftTailNumber());
        User student = userService.getUser(flightCreationRequest.studentId());
        User instructor = userService.getUser(flightCreationRequest.instructorId());

        Long nextFlightId = flightCreationRequest.nextFlightId();
        Flight nextFlight = flightRepository.findFlightById(nextFlightId)
                .orElse(null);

        List<ActionItem> actionItems = flightCreationRequest
                .actionItems()
                .stream()
                .map(actionItem -> new ActionItem(
                        actionItem.text(),
                        actionItem
                                .notes()
                                .stream()
                                .map(note -> {
                                    List<Resource> resources = note
                                            .resourceIds()
                                            .stream()
                                            .map(resourceRepository::findResourceById)
                                            .filter(resource -> resource != null) // could possibly remove this
                                            .toList();

                                    return new Note(note.text(), resources);
                                })
                                .toList()
                        )
                )
                .toList();

        Flight flight = new Flight(
            flightCreationRequest.date(),
            aircraft,
            flightCreationRequest.duration(),
            flightCreationRequest.crossCountry(),
            flightCreationRequest.nightFlight(),
            flightCreationRequest.objective(),
            actionItems,
            flightCreationRequest.thingsDoneWell(),
            flightCreationRequest.thingsToImprove(),
            student,
            instructor,
            nextFlight
        );

        flight.getActionItems().forEach(actionItem -> {
            actionItem.setFlight(flight);
        });

        return flightRepository.save(flight);
    }
}
