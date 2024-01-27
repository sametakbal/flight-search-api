package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.domain.request.FlightCreateRequest;
import com.amadeus.flightsearchapi.domain.response.FlightResponse;
import com.amadeus.flightsearchapi.domain.response.OperationResponse;
import com.amadeus.flightsearchapi.service.FlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> fetchFlights(@RequestParam String arrivalDate,
                                                             @RequestParam(required = false) String departureDate,
                                                             @RequestParam Integer departureAirportId,
                                                             @RequestParam Integer arrivalAirportId) {
        return ResponseEntity.ok(flightService.fetchFlights( departureAirportId,
                arrivalAirportId, arrivalDate, departureDate));
    }

    @PostMapping("/create")
    public ResponseEntity<OperationResponse> createFlight(@Valid @RequestBody FlightCreateRequest request) {
        return ResponseEntity.ok(flightService.createFlight(request));
    }

    @PutMapping("/update")
    public ResponseEntity<OperationResponse> updateFlight(@RequestBody FlightCreateRequest request) {
        return ResponseEntity.ok(flightService.updateFlight(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationResponse> deleteFlight(@PathVariable Integer id) {
        return ResponseEntity.ok(flightService.deleteFlight(id));
    }

}
