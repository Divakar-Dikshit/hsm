package com.hotel.UserService;

import com.hotel.Entity.City;
import com.hotel.Repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CityService {

    private CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public String addCity(City city) {
        cityRepository.save(city);
        return "data saved";
    }
}
