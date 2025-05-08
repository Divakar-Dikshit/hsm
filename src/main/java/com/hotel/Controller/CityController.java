package com.hotel.Controller;

import com.hotel.Entity.City;
import com.hotel.UserService.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @PostMapping("/addCity")
    public ResponseEntity<String> addCity(@RequestBody City city){
       cityService.addCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body("City added successfully");
    }

}
