package com.example.ParkingSpots.Entities;

import com.example.ParkingSpots.Enumerators.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "VEHICLE")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @Column(name = "VEHICLE_NO")
    private String vehicleNO;
    @Column(name = "VEHICLE_TYPE")
    @Enumerated(value = EnumType.STRING)
    private VehicleType vehicleType;
    @Column(name = "SLOT_ID")
    private Integer slotID;
}
