package com.amadeus.flightsearchapi.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OperationResponse {
    private boolean success;
    private String message;
}
