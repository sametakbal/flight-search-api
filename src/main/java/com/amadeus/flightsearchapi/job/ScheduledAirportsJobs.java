package com.amadeus.flightsearchapi.job;

import com.amadeus.flightsearchapi.domain.entity.Airport;
import com.amadeus.flightsearchapi.domain.entity.City;
import com.amadeus.flightsearchapi.repository.IAirportRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ScheduledAirportsJobs {

    private IAirportRepository airportRepository;

    @Scheduled(fixedRate = 86400000)
    public void test() throws JsonProcessingException {
        String uri = "https://raw.githubusercontent.com/sametakbal/flight-search-api/master/airports.json";
        RestTemplate restTemplate = new RestTemplate();
        String jsonStr = restTemplate.getForObject(uri, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        AirportCreateDtoList airportCreateDtoList = objectMapper.readValue(jsonStr, AirportCreateDtoList.class);
        if (airportCreateDtoList != null) {
            List<Airport> airports = new ArrayList<>();
            for (AirportCreateDto airportCreateDto : airportCreateDtoList.getAirports()) {
                Airport airport = Airport.builder()
                        .id(airportCreateDto.getId())
                        .name(airportCreateDto.getName())
                        .city(City.builder().id(airportCreateDto.getCityId()).build())
                        .build();
                airports.add(airport);
            }
            airportRepository.saveAll(airports);
        }

    }
}

@Data
class AirportCreateDtoList {
    private List<AirportCreateDto> airports;
}

@Data
class AirportCreateDto {
    private Integer id;
    private String name;
    private Integer cityId;
}
