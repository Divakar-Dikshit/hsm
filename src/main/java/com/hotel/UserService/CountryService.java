package com.hotel.UserService;

import com.hotel.Entity.Country;
import com.hotel.Repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void saveCountryData(Country country) {
        countryRepository.save(country);
    }
}
