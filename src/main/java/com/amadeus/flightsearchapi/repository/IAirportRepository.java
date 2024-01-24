package com.amadeus.flightsearchapi.repository;

import com.amadeus.flightsearchapi.domain.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAirportRepository extends JpaRepository<Airport,Integer> {
    List<Airport> findAllByCityId(Integer cityId);

    List<Airport> findAllByCityCountryId(Integer countryId);
}
