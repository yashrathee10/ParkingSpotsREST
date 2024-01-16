package com.example.ParkingSpots.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PARKING_LEVEL")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "TOTAL_BIKE")
    private Integer totBikeSpots;
    @Column(name = "TOTAL_CAR")
    private Integer totCarSpots;
    @Column(name = "TOTAL_BUS")
    private Integer totBusSpots;
    @Column(name = "AVAIL_BIKE")
    private Integer availBikeSpots;
    @Column(name = "AVAIL_CAR")
    private Integer availCarSpots;
    @Column(name = "AVAIL_BUS")
    private Integer availBusSpots;
}
