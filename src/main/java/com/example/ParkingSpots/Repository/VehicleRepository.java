package com.example.ParkingSpots.Repository;

import com.example.ParkingSpots.Entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, String>{
}
