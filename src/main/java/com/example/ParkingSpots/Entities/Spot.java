package com.example.ParkingSpots.Entities;

import com.example.ParkingSpots.Enumerators.VehicleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PARKING_SLOT")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "LEVEL_ID")
    private Integer levelId;
    @Column(name = "SLOT_TYPE")
    @Enumerated(value = EnumType.STRING)
    private VehicleType slotType;
    @Column(name = "OCCUPIED")
    private Boolean occupied;
}
