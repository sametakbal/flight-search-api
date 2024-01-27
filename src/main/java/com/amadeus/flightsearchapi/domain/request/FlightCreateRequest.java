package com.amadeus.flightsearchapi.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightCreateRequest {
    private Integer id;
    @NotNull(message = "Name name can not be empty!")
    private String name;
    @NotNull(message = "Departure date can not be empty!")
    private String departureDateStr;
    @NotNull(message = "Arrival date can not be empty!")
    private String arrivalDateStr;
    @NotNull(message = "Departure airport id not be empty!")
    private Integer departureAirportId;
    @NotNull(message = "Arrival airport id can not be empty!")
    private Integer arrivalAirportId;
}
