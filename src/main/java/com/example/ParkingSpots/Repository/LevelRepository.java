package com.example.ParkingSpots.Repository;

import com.example.ParkingSpots.Entities.Level;
import com.example.ParkingSpots.Models.ParkingLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
}
