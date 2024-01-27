package com.amadeus.flightsearchapi.repository;

import com.amadeus.flightsearchapi.domain.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Flight, Integer>, JpaSpecificationExecutor<Flight> {
    Page<Flight> findAllByDepartureAirportIdAndArrivalAirportId(PageRequest pageRequest, Long departureAirportId, Long arrivalAirportId);

    boolean existsByFlightCode(String newCode);
}
