package com.amadeus.flightsearchapi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @OneToMany(mappedBy = "departureAirport")
    private List<Flight> departureFlights;
    @OneToMany(mappedBy = "arrivalAirport")
    private List<Flight> arrivalFlights;
}
