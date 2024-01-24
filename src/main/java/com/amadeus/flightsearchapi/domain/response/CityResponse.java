package com.amadeus.flightsearchapi.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityResponse {
    private Integer id;
    private String name;
}
