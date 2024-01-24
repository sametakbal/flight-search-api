package com.amadeus.flightsearchapi.domain.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportCreateRequest {
    private Integer id;
    @NotNull(message = "Airport name can not be empty!")
    private String name;
    @NotNull(message = "City can not be empty!")
    private Integer cityId;
}
