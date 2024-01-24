package com.amadeus.flightsearchapi.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportResponse {
    private Integer id;
    private String name;
    private CityResponse city;
}
