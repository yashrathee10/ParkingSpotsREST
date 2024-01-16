package com.example.ParkingSpots.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLevel {
    private Integer totBikeSpots;
    private Integer totCarSpots;
    private Integer totBusSpots;
    private Integer availBikeSpots;
    private Integer availCarSpots;
    private Integer availBusSpots;

}
