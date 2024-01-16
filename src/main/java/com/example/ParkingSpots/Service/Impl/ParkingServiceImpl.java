package com.example.ParkingSpots.Service.Impl;

import com.example.ParkingSpots.Common.Response;
import com.example.ParkingSpots.Entities.Level;
import com.example.ParkingSpots.Entities.Spot;
import com.example.ParkingSpots.Entities.Vehicle;
import com.example.ParkingSpots.Enumerators.VehicleType;
import com.example.ParkingSpots.Models.ParkingLevel;
import com.example.ParkingSpots.Models.VehicleModel;
import com.example.ParkingSpots.Repository.LevelRepository;
import com.example.ParkingSpots.Repository.SpotRepository;
import com.example.ParkingSpots.Repository.VehicleRepository;
import com.example.ParkingSpots.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.ParkingSpots.Enumerators.VehicleType.*;

@Service
public class ParkingServiceImpl implements ParkingService {
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public String increaseLevel(ParkingLevel parkingLevel){
        Level level = new Level();
        level.setTotBikeSpots(parkingLevel.getTotBikeSpots());
        level.setTotCarSpots(parkingLevel.getTotCarSpots());
        level.setTotBusSpots(parkingLevel.getTotBusSpots());

        level.setAvailBikeSpots(parkingLevel.getAvailBikeSpots());
        level.setAvailCarSpots(parkingLevel.getAvailCarSpots());
        level.setAvailBusSpots(parkingLevel.getAvailBusSpots());

        level = levelRepository.save(level);
        List<Spot> spots = new ArrayList<>();

        addingSlots(Bike, parkingLevel.getTotBikeSpots(), spots, level);
        addingSlots(Car, parkingLevel.getTotCarSpots(), spots, level);
        addingSlots(Bus, parkingLevel.getTotBusSpots(), spots, level);

        spots = spotRepository.saveAll(spots);
        return null;
    }
    public void addingSlots(VehicleType vehicleSlot, Integer totalSlots, List<Spot> spots, Level level){
        for(int i =0; i<totalSlots; i++){
            Spot spot = new Spot();
            spot.setLevelId(level.getId());
            spot.setSlotType(vehicleSlot);
            spot.setOccupied(Boolean.FALSE);
            spots.add(spot);
        }
    }
    @Override
    public Response<String> decreaseLevel(Integer id){
        if(!levelRepository.existsById(id)){
            return new Response<>("Level dosen't exist", HttpStatus.NOT_FOUND);
        }
        List<Spot> findAll = spotRepository.findAll();
        List<Spot> selectedEntities = findAll.stream().filter(spot -> spot.getLevelId() == id).collect(Collectors.toList());
        spotRepository.deleteAll(selectedEntities);
        levelRepository.deleteById(id);
        return new Response<>("Level decreased successfully");
    }
    @Override
    public Response park(VehicleModel vehicleModel){
        if(vehicleRepository.existsById(vehicleModel.getVehicleNumber())){
            return new Response("Car is already parked", HttpStatus.NOT_ACCEPTABLE);
        }
        else if(spotRepository.findAll().size()==0){
            return new Response("No Level is created", HttpStatus.BAD_REQUEST);
        }
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNO(vehicleModel.getVehicleNumber());
        vehicle.setVehicleType(vehicleModel.getVehicleType());
        vehicle.setSlotID(changeOccupancyToTrue(vehicleModel.getVehicleType()));
        vehicle = vehicleRepository.save(vehicle);
        return new Response(VehicleModel.builder().vehicleNumber(vehicle.getVehicleNO()).vehicleType(vehicle.getVehicleType()).slotID(vehicle.getSlotID()).build());
    }
    private Integer changeOccupancyToTrue(VehicleType vehicleType){
        List<Spot> spots = spotRepository.findAll();
        for(int i=0; i<spots.size(); i++){
            Spot spot = spots.get(i);
            if(spot.getSlotType().equals(vehicleType) && spot.getOccupied()==Boolean.FALSE){
                spot.setOccupied(Boolean.TRUE);
                changeAvailSpots(spot.getLevelId(),vehicleType);
                spot = spotRepository.save(spot);
                return spot.getId();
            }
        }
        return null;
    }

    private void changeAvailSpots(Integer id, VehicleType vehicleType){
        List<Level> levels = levelRepository.findAll();
        for(int i =0; i<levels.size(); i++){
            Level level = levels.get(i);
            if(level.getId()==id && vehicleType==Bike){
                level.setAvailBikeSpots(level.getAvailBikeSpots()-1);
            }
            else if(level.getId()==id && vehicleType==Car){
                level.setAvailCarSpots(level.getAvailCarSpots()-1);
            }
            else if(level.getId()==id && vehicleType==Bus){
                level.setAvailBusSpots(level.getAvailBusSpots()-1);
            }
            level = levelRepository.save(level);
        }
    }
    @Override
    public Response<String> unpark(String vehicleNO) {
        if(!vehicleRepository.existsById(vehicleNO)){
            return new Response<>("No vehicle exists of such number", HttpStatus.NOT_FOUND);
        }
        Optional<Vehicle> vehicles = vehicleRepository.findById(vehicleNO);
        Vehicle vehicle = vehicles.get();
        Integer slotID = vehicle.getSlotID();
        vehicleRepository.deleteById(vehicleNO);
        Optional<Spot> spots = spotRepository.findById(slotID);
        Spot spot = spots.get();
        spot.setOccupied(Boolean.FALSE);
        Level level = levelRepository.getById(spot.getLevelId());
        if(spot.getSlotType()==Bike){
            level.setAvailBikeSpots(level.getAvailBikeSpots()+1);
        }
        else if(spot.getSlotType()==Car){
            level.setAvailCarSpots(level.getAvailCarSpots()+1);
        }
        else if(spot.getSlotType()==Bus){
            level.setAvailBusSpots(level.getAvailBusSpots()+1);
        }
        spot = spotRepository.save(spot);

        return new Response<>("Vehicle unparked successfully");

    }
    public List<Level> statis(){
        List<Level> stats = levelRepository.findAll();
        return stats;
    }

    @Override
    public Response<String> getVehicle(String id) {
        if(!vehicleRepository.existsById(id)){
            return new Response<>("Vehicle dosen't exist", HttpStatus.NOT_FOUND);
        }
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        Vehicle vehicle = optionalVehicle.get();
        Integer slotId = vehicle.getSlotID();
        Optional<Spot> optionalSpot = spotRepository.findById(slotId);
        Spot spot = optionalSpot.get();
        Integer levelID = spot.getLevelId();
        return new Response<>("Vehicle is parked in spot "+ slotId + " and level is "+ levelID);
    }
}
