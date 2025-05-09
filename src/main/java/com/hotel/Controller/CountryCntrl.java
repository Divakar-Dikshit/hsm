package com.hotel.Controller;


import com.hotel.Entity.AppUser;
import com.hotel.Entity.Country;
import com.hotel.UserService.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/country-save")
public class CountryCntrl{

   private CountryService countryService;

    public CountryCntrl(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/add")
    public AppUser addCountryData(@AuthenticationPrincipal AppUser appUser){
       // countryService.saveCountryData(appUser);
        return  appUser;
        //if you want ot know who is current user details  just add below code that's it

        //return ResponseEntity.status(HttpStatus.CREATED).body("Country added successfully");
    }
}




