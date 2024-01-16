package com.example.ParkingSpots.Service;

import com.example.ParkingSpots.Common.Response;
import com.example.ParkingSpots.Entities.Level;
import com.example.ParkingSpots.Models.ParkingLevel;
import com.example.ParkingSpots.Models.VehicleModel;
import java.util.*;

public interface ParkingService {
    String increaseLevel(ParkingLevel parkingLevel);
    Response<String> decreaseLevel(Integer id);
    Response park(VehicleModel vehicleModel);
    Response<String> unpark(String vehicleNO);
    List<Level> statis();
    Response<String> getVehicle(String id);
}
