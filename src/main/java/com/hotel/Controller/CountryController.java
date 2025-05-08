package com.hotel.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {

    //http://localhost:8080/api/v1/country
//    @PostMapping
//    public ResponseEntity<String> addCountry(){
//        return new ResponseEntity<>("added", HttpStatus.CREATED);
//    }
    @PostMapping("/addCountry")
    public String addCountry(){
        System.out.println("added");
        return "added successfully";
    }

}
