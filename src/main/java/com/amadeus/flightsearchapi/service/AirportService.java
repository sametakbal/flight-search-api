package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.domain.entity.Airport;
import com.amadeus.flightsearchapi.domain.request.AirportCreateRequest;
import com.amadeus.flightsearchapi.domain.response.AirportResponse;
import com.amadeus.flightsearchapi.domain.response.OperationResponse;
import com.amadeus.flightsearchapi.repository.IAirportRepository;
import com.amadeus.flightsearchapi.service.mapper.AirportMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {

    private AirportMapper mapper;
    private IAirportRepository airportRepository;

    public List<AirportResponse> getAirports(Integer cityId, Integer countryId) {
        if (cityId != null) {
            List<Airport> airports = airportRepository.findAllByCityId(cityId);
            return mapper.toResponseList(airports);
        }
        if (countryId != null) {
            List<Airport> airports = airportRepository.findAllByCityCountryId(countryId);
            return mapper.toResponseList(airports);
        }
        List<Airport> airports = airportRepository.findAll();
        return mapper.toResponseList(airports);
    }


    public OperationResponse save(AirportCreateRequest request) {
        Airport airport = mapper.toEntity(request);
        airportRepository.save(airport);
        return OperationResponse.builder()
                .message("Airport saved successfully!")
                .build();
    }

    public OperationResponse delete(Integer id) {
        if (!airportRepository.existsById(id)) {
            return OperationResponse.builder()
                    .message("Airport not found!")
                    .build();
        }
        airportRepository.deleteById(id);
        return OperationResponse.builder()
                .message("Airport deleted successfully!")
                .build();
    }
}
