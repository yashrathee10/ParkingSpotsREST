package com.example.ParkingSpots.Controller;

import com.example.ParkingSpots.Common.Response;
import com.example.ParkingSpots.Entities.Level;
import com.example.ParkingSpots.Models.ParkingLevel;
import com.example.ParkingSpots.Models.VehicleModel;
import com.example.ParkingSpots.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class ParkingController {
    @Autowired
    private ParkingService parkingService;
    @PostMapping("/park")
    public ResponseEntity park(@RequestBody VehicleModel vehicleModel){
        Response response = parkingService.park(vehicleModel);
        return new ResponseEntity (response.getReturnObject(), response.getHttpStatus());
    }
    @DeleteMapping("/unpark/{No}")
    public ResponseEntity<String> unpark(@PathVariable("No") String No){
        Response<String> response = parkingService.unpark(No);
        return new ResponseEntity <>(response.getReturnObject(), response.getHttpStatus());
    }
    @GetMapping("/stats")
    public List<Level> stats(){
        List<Level> statis = parkingService.statis();
        return statis;
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getVehicle(@PathVariable("id")String id){
        Response<String> response = parkingService.getVehicle(id);
        return new ResponseEntity<>(response.getReturnObject(),response.getHttpStatus());
    }
}
