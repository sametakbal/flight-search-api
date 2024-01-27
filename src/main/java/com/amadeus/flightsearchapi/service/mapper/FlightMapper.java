package com.amadeus.flightsearchapi.service.mapper;

import com.amadeus.flightsearchapi.domain.entity.Airport;
import com.amadeus.flightsearchapi.domain.entity.Flight;
import com.amadeus.flightsearchapi.domain.request.FlightCreateRequest;
import com.amadeus.flightsearchapi.domain.response.FlightResponse;
import com.amadeus.flightsearchapi.util.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FlightMapper implements IMapper<Flight, FlightCreateRequest, FlightResponse> {
    @Override
    public Flight toEntity(FlightCreateRequest request) {
        Flight flight = new Flight();
        flight.setId(request.getId());
        flight.setName(request.getName());
        flight.setDepartureDate(Converter.dateFromString(request.getDepartureDateStr()));
        flight.setArrivalDate(Converter.dateFromString(request.getArrivalDateStr()));
        flight.setArrivalAirport(Airport.builder().id(request.getArrivalAirportId()).build());
        flight.setDepartureAirport(Airport.builder().id(request.getDepartureAirportId()).build());
        return flight;
    }

    @Override
    public FlightResponse toResponse(Flight flight) {
        if (flight == null) {
            return null;
        }
        FlightResponse response = new FlightResponse();
        response.setId(flight.getId());
        response.setName(flight.getName());
        response.setFlightCode(flight.getFlightCode());
        response.setDepartureDateStr(Converter.dateToString(flight.getDepartureDate()));
        response.setArrivalDateStr(Converter.dateToString(flight.getArrivalDate()));
        response.setDepartureAirport(flight.getDepartureAirport().getName());
        response.setArrivalAirport(flight.getArrivalAirport().getName());
        return response;
    }

    @Override
    public List<FlightResponse> toResponseList(List<Flight> flights) {
        if (flights == null) {
            return Collections.emptyList();
        }
        return flights.stream().map(this::toResponse).toList();
    }

}
