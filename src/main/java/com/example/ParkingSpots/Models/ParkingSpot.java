package com.example.ParkingSpots.Models;

import com.example.ParkingSpots.Enumerators.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpot {
    private Integer levelID;
    private VehicleType slotType;
    private Boolean occupied = Boolean.FALSE;
}
