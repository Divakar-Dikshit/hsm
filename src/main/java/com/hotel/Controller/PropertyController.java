package com.hotel.Controller;

import com.hotel.Entity.Property;
import com.hotel.UserService.PropertyService;
import com.hotel.payload.PropertyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
    //  private PropertyRequest propertyRequest;


    @PostMapping("/store")
    public ResponseEntity<?> createProperty(@RequestBody PropertyRequest propertyRequest ) {
        Property saved = propertyService.createProperty(propertyRequest);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @GetMapping("/getData")
    public ResponseEntity<List<?>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
    @GetMapping("/search-hotels")
    public List<Property> searchHotels(@RequestParam String name){
        List<Property> properties = PropertyService.searchHotels(name);
        return properties;

    }

    }