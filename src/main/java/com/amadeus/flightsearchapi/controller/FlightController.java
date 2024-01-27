package com.amadeus.flightsearchapi.controller;

import com.amadeus.flightsearchapi.domain.request.FlightCreateRequest;
import com.amadeus.flightsearchapi.domain.response.FlightResponse;
import com.amadeus.flightsearchapi.domain.response.OperationResponse;
import com.amadeus.flightsearchapi.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
            summary = "Fetch all flights by arrival date and departure date",
            description = "Arrival date and departure date can be used for filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<List<FlightResponse>> fetchFlights(@RequestParam String arrivalDate,
                                                             @RequestParam(required = false) String departureDate,
                                                             @RequestParam Integer departureAirportId,
                                                             @RequestParam Integer arrivalAirportId) {
        return ResponseEntity.ok(flightService.fetchFlights(departureAirportId,
                arrivalAirportId, arrivalDate, departureDate));
    }

    @PostMapping("/create")
    @Operation(
            summary = "Create Flight",
            description = "That endpoint is used for creating flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<OperationResponse> createFlight(@Valid @RequestBody FlightCreateRequest request) {
        return ResponseEntity.ok(flightService.createFlight(request));
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update Flight",
            description = "That endpoint is used for updating flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<OperationResponse> updateFlight(@RequestBody FlightCreateRequest request) {
        return ResponseEntity.ok(flightService.updateFlight(request));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<OperationResponse> deleteFlight(@PathVariable Integer id) {
        return ResponseEntity.ok(flightService.deleteFlight(id));
    }

}
