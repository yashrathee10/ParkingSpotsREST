package com.example.ParkingSpots.Repository;

import com.example.ParkingSpots.Entities.Level;
import com.example.ParkingSpots.Models.ParkingLevel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    @Transactional
    @Modifying
    @Query("update Level l set l.availBikeSpots = l.availBikeSpots-1 where l.id = :id")
    public void updateLevelBike(@Param("id") Integer ids);
    @Transactional
    @Modifying
    @Query("update Level l set l.availCarSpots = l.availCarSpots-1 where l.id = :id")
    public void updateLevelCar(@Param("id") Integer id);
    @Transactional
    @Modifying
    @Query("update Level l set l.availBusSpots = l.availBusSpots-1 where l.id = :id")
    public void updateLevelBus(@Param("id") Integer id);
//    @EntityGraph("level.slots")
    @Query("select l from Level l left join fetch l.spotsList")
    List<Level> findAllLevels();
}
