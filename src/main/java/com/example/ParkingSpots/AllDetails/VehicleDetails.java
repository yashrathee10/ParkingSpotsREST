package com.example.ParkingSpots.AllDetails;

import com.example.ParkingSpots.Enumerators.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private Integer slotID;
}
