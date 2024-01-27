package com.amadeus.flightsearchapi.service;

import com.amadeus.flightsearchapi.domain.entity.Airport;
import com.amadeus.flightsearchapi.domain.entity.Flight;
import com.amadeus.flightsearchapi.domain.request.FlightCreateRequest;
import com.amadeus.flightsearchapi.domain.response.FlightResponse;
import com.amadeus.flightsearchapi.domain.response.OperationResponse;
import com.amadeus.flightsearchapi.handler.exceptions.GeneralException;
import com.amadeus.flightsearchapi.repository.IFlightRepository;
import com.amadeus.flightsearchapi.service.mapper.FlightMapper;
import com.amadeus.flightsearchapi.util.Converter;
import jakarta.persistence.criteria.Join;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class FlightService {

    private final FlightMapper flightMapper;
    private final IFlightRepository flightRepository;

    public OperationResponse createFlight(FlightCreateRequest request) {
        Flight entity = flightMapper.toEntity(request);
        entity.setFlightCode(getNewFlightCode());
        entity.setCreatedAt(LocalDateTime.now());
        flightRepository.save(entity);
        return new OperationResponse(true, "Flight created successfully");
    }

    public OperationResponse updateFlight(FlightCreateRequest request) {
        Flight existingFlight = flightRepository.findById(request.getId())
                .orElseThrow(() -> new GeneralException("Flight not found"));
        Flight entity = flightMapper.toEntity(request);
        entity.setFlightCode(existingFlight.getFlightCode());
        entity.setCreatedAt(existingFlight.getCreatedAt());
        entity.setUpdatedAt(LocalDateTime.now());
        flightRepository.save(entity);
        return new OperationResponse(true, "Flight updated successfully");
    }

    public List<FlightResponse> fetchFlights(Integer departureAirportId,
                                             Integer arrivalAirportId,
                                             String arrivalDateStr,
                                             String departureDateStr) {
        LocalDateTime arrivalDate = Converter.dateFromString(arrivalDateStr);
        Specification<Flight> spec = getSpec(departureAirportId, arrivalAirportId,
                arrivalDate);

        List<Flight> flights = flightRepository.findAll(spec);
        if (!StringUtils.isEmpty(departureDateStr)) {
            LocalDateTime departureDate = Converter.dateFromString(departureDateStr);
            List<Flight> returnFlights = flightRepository.findAll(getSpec(arrivalAirportId, departureAirportId, arrivalDate, departureDate));
            flights.addAll(returnFlights);
        }
        return flightMapper.toResponseList(flights);
    }

    private Specification<Flight> getSpec(Integer departureAirportId, Integer arrivalAirportId, LocalDateTime arrivalDate) {
        return Specification.where(hasArrivalAirportId(arrivalAirportId))
                .and(hasDepartureAirportId(departureAirportId))
                .and(hasArrivalDate(arrivalDate));
    }

    private Specification<Flight> getSpec(Integer departureAirportId, Integer arrivalAirportId, LocalDateTime arrivalDate, LocalDateTime departureDate) {
        return Specification.where(hasArrivalAirportId(arrivalAirportId))
                .and(hasDepartureAirportId(departureAirportId))
                .and(hasArrivalDate(arrivalDate))
                .and(hasDepartureDate(departureDate));
    }

    private Specification<Flight> hasArrivalDate(LocalDateTime arrivalDate) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("arrivalDate"), arrivalDate);
    }

    private Specification<Flight> hasDepartureDate(LocalDateTime departureDate) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("departureDate"), departureDate);
    }

    private Specification<Flight> hasDepartureAirportId(Integer departureAirportId) {
        return (root, query, cb) -> {
            Join<Airport, Flight> airportFlights = root.join("departureAirport");
            return cb.equal(airportFlights.get("id"), departureAirportId);
        };
    }

    private Specification<Flight> hasArrivalAirportId(Integer arrivalAirportId) {
        return (root, query, cb) -> {
            Join<Airport, Flight> airportFlights = root.join("arrivalAirport");
            return cb.equal(airportFlights.get("id"), arrivalAirportId);
        };
    }
    public String getNewFlightCode() {
        String newCode = "FL" + String.join("",
                ThreadLocalRandom.current().ints(1, 10)
                        .limit(6)
                        .mapToObj(String::valueOf)
                        .toArray(String[]::new));
        if (flightRepository.existsByFlightCode(newCode)) {
            return getNewFlightCode();
        }
        return newCode;
    }

    public OperationResponse deleteFlight(Integer id) {
        flightRepository.deleteById(id);
        return new OperationResponse(true, "Flight deleted successfully");
    }
}
