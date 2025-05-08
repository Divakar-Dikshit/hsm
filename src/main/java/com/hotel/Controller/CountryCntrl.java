package com.hotel.Controller;


import com.hotel.Entity.Country;
import com.hotel.UserService.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/countrysave")
public class CountryCntrl{
   private CountryService countryService;

    public CountryCntrl(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add")
    public ResponseEntity<String>addCountryData(@RequestBody Country country){
        countryService.saveCountryData(country);
        return ResponseEntity.status(HttpStatus.CREATED).body("Country added successfully");
    }
}




