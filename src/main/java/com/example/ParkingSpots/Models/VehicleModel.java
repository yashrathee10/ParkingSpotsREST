package com.example.ParkingSpots.Models;

import com.example.ParkingSpots.Enumerators.VehicleType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleModel {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Integer slotID;
}
