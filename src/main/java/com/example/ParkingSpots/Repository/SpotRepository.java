package com.example.ParkingSpots.Repository;

import com.example.ParkingSpots.Entities.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer> {


}
