package com.example.ParkingSpots.AllDetails;

import com.example.ParkingSpots.Repository.SpotRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LevelDetails {
    private Integer levelID;
    private Integer capacity;
    private Integer occupied;
    private Integer available;
    private VehicleDetails vehicleDetails;
    private SpotDetails spotDetails;
}
