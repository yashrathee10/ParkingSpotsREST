package com.example.ParkingSpots.Controller;

import com.example.ParkingSpots.Common.Response;
import com.example.ParkingSpots.Models.ParkingLevel;
import com.example.ParkingSpots.Service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LevelController {
    @Autowired
    private ParkingService parkingService;
    @PostMapping("/increase-level")
    public String increaseLevel(@RequestBody ParkingLevel parkingLevel){
        parkingService.increaseLevel(parkingLevel);
        return "Level Increased Successfully";

    }
    @DeleteMapping("/decrease-level/{id}")
    public ResponseEntity<String> decreaseLevel(@PathVariable("id")Integer id){
        Response<String> response = parkingService.decreaseLevel(id);

        return new ResponseEntity <>(response.getReturnObject(), response.getHttpStatus());
    }
}
