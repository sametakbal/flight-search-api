package com.amadeus.flightsearchapi.controller;


import com.amadeus.flightsearchapi.domain.request.AirportCreateRequest;
import com.amadeus.flightsearchapi.domain.response.AirportResponse;
import com.amadeus.flightsearchapi.domain.response.OperationResponse;
import com.amadeus.flightsearchapi.service.AirportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService service;

    @GetMapping
    @Operation(
            summary = "Fetch all airports",
            description = "City id and country id can be used for filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<List<AirportResponse>> fetchAirports(@RequestParam(required = false) Integer cityId,
                                                               @RequestParam(required = false) Integer countryId) {
        return ResponseEntity.ok(service.getAirports(cityId, countryId));
    }

    @PostMapping
    @Operation(
            summary = "Create Airport",
            description = "That endpoint is used for creating and update airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<OperationResponse> save(@Valid @RequestBody AirportCreateRequest request) {
        OperationResponse result = service.save(request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Airport",
            description = "That endpoint is used for deleting airport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    public ResponseEntity<OperationResponse> delete(@PathVariable Integer id) {
        OperationResponse result = service.delete(id);
        return ResponseEntity.ok(result);
    }
}
