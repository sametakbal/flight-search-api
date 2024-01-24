package com.amadeus.flightsearchapi.domain.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperationResponse {
    private boolean success;
    private String message;
}
