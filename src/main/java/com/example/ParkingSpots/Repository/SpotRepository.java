package com.example.ParkingSpots.Repository;

import com.example.ParkingSpots.Entities.Spot;
import com.example.ParkingSpots.Enumerators.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer> {

    @Query("select a from Spot a where a.occupied= false and a.slotType = :vehicleType")
    public List<Spot> findVacant(@Param("vehicleType") VehicleType vehicleType);


}
