package com.amadeus.flightsearchapi.domain.response;

import lombok.Data;

@Data
public class FlightResponse {
    private Integer id;
    private String name;
    private String flightCode;
    private String departureDateStr;
    private String arrivalDateStr;
    private String departureAirport;
    private String arrivalAirport;
}
