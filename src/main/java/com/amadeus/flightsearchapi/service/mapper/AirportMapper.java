package com.amadeus.flightsearchapi.service.mapper;

import com.amadeus.flightsearchapi.domain.entity.Airport;
import com.amadeus.flightsearchapi.domain.entity.City;
import com.amadeus.flightsearchapi.domain.request.AirportCreateRequest;
import com.amadeus.flightsearchapi.domain.response.AirportResponse;
import com.amadeus.flightsearchapi.domain.response.CityResponse;
import java.util.Collections;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportMapper {

    public AirportResponse toResponse(Airport airport) {
        if (airport == null) {
            return null;
        }
        return AirportResponse.builder()
                .id(airport.getId())
                .name(airport.getName())
                .city(CityResponse.builder()
                        .id(airport.getCity().getId())
                        .name(airport.getCity().getName())
                        .build()).build();
    }

    public List<AirportResponse> toResponseList(List<Airport> airports) {
        if (airports == null) {
            return Collections.emptyList();
        }
        return airports.stream().map(this::toResponse).toList();
    }

    public Airport toEntity(AirportCreateRequest request) {
        if (request == null) {
            return null;
        }
        return Airport.builder()
                .id(request.getId())
                .name(request.getName())
                .city(City.builder().id(request.getCityId()).build())
                .build();
    }
}
